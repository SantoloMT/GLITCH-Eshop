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
 * Servlet implementation class RedirectGestoreCatalogo
 */
@WebServlet("/RedirectGestoreProdotti")
public class RedirectGestoreProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConsoleDAO cDAO = new ConsoleDB();
	private VideogiocoDAO vDAO = new VideogiocoDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectGestoreProdotti() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Si prelevano da DB tutti i prodotti e si settano nella request
		List<Console> consoles = cDAO.findAllConsole();
		List<Videogioco> videogiochi = vDAO.findAllVideogioco();
		
		request.getSession().setAttribute("Consoles", consoles);
		request.getSession().setAttribute("Videogiochi", videogiochi);
		//Si effettua la forward alla pagina GestioneProdotti.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/GestioneProdotti.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
