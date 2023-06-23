package controller;


import java.util.List;
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Console;
import model.bean.Videogioco;
import model.dao.ConsoleDAO;
import model.dao.ConsoleDB;
import model.dao.VideogiocoDAO;
import model.dao.VideogiocoDB;


/*BaseServlet permette di inizializzare il catalogo nella home del sito 
* in modo da mostrare i prodotti offerti
* */

@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private VideogiocoDAO videoDAO = new VideogiocoDB();
    private ConsoleDAO consoleDAO = new ConsoleDB();

    public BaseServlet() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Si fa restituire dal database i primi 20 videogiochi e console e 
		//vengono inseriti nella request
		
		
		List<Videogioco> videogiochi = videoDAO.doRetriveVideogiocoAllRange(0, 3);
		request.setAttribute("videogiochi", videogiochi);
		
		List<Console> console = consoleDAO.doRetriveConsoleAllRange(0, 3);
		request.setAttribute("console", console);
		
		//Si esegue la forward  con i dati precedentemente settati nella request 
		//alla Homepage.jsp che provvedera a mostrare i prodotti
		//System.out.println(videogiochi.get(0).getImmagine());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/Homepage.jsp");
		requestDispatcher.forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
