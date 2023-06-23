package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Ordine;
import model.bean.Utente;
import model.dao.OrdineDAO;
import model.dao.OrdineDB;

/**
 * Servlet implementation class RedirectPaginaOrdini
 */
@WebServlet("/RedirectPaginaOrdini")
public class RedirectPaginaOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrdineDAO oDAO = new OrdineDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectPaginaOrdini() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setto la lista di ordini dell'utente nella request
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		List<Ordine> o = oDAO.retriveByUtente(u.getUsername());
		
		request.setAttribute("ordini", o);
		//Si esegue la forward alla pagina PaginaOrdini
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/PaginaOrdini.jsp");
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
