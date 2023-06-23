package controller;

import java.io.IOException;
import java.util.List;

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

/**
 * Servlet implementation class PaginazioneServlet
 */
@WebServlet("/PaginazioneServlet")
public class PaginazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideogiocoDAO videoDAO = new VideogiocoDB();
    private ConsoleDAO consoleDAO = new ConsoleDB();
    private static int counter = 0;
    private static int secondCounter = 3;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Si fa restituire dal database i primi 20 videogiochi e console e 
				//vengono inseriti nella request
				
				String page = request.getParameter("page");
				if(page.equals("prec")) {
					if(counter >= 3 ) {
						counter = counter - 3;
						secondCounter = secondCounter -3;
					}
				}else if(page.equals("one")) {
					counter = 0;
					secondCounter = 3;
				}else if (page.equals("two")) {
					counter = 3;
					secondCounter = 6;
				}else if (page.equals("three")) {
					counter = 6;
					secondCounter = 9;
				}else if(page.equals("next")) {
					if(counter <= 15 ) {
						counter = counter + 3;
						secondCounter = secondCounter + 3;
					}
				}
				List<Videogioco> videogiochi = videoDAO.doRetriveVideogiocoAllRange(counter, secondCounter);
				request.setAttribute("videogiochi", videogiochi);
				
				List<Console> console = consoleDAO.doRetriveConsoleAllRange(counter, secondCounter);
				request.setAttribute("console", console);
				
				//Si esegue la forward  con i dati precedentemente settati nella request 
				//alla Homepage.jsp che provvedera a mostrare i prodotti
				//System.out.println(videogiochi.get(0).getImmagine());
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/Homepage.jsp");
				requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
