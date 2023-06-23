package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Offerta;
import model.dao.OffertaDAO;
import model.dao.OffertaDB;


/**
 * Servlet implementation class RedirectGestoreOfferte
 */
@WebServlet("/RedirectGestoreOfferte")
public class RedirectGestoreOfferte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OffertaDAO oDAO = new OffertaDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectGestoreOfferte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Si prendono da DB tutte le offerte e si setta la lista nella request
		List<Offerta> o = oDAO.findAllOfferta();

		request.getSession().setAttribute("offerta", o);
		//Si effettua la forward alla pagina GestioniOfferte.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/GestioniOfferte.jsp");
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
