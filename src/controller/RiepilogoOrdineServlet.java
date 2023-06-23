package controller;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Carrello;
import model.bean.Ordine;
import model.bean.Utente;
import model.dao.CarrelloDAO;
import model.dao.CarrelloDB;
import model.dao.OrdineDAO;
import model.dao.OrdineDB;


/**
 * RiepilogoOrdineServlet gestisce l'operazione di creazione ordine dei prodotti presenti nel carrello
 */
@WebServlet("/RiepilogoOrdineServlet")
public class RiepilogoOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdineDAO oDAO = new OrdineDB();
	private CarrelloDAO cDAO = new CarrelloDB();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RiepilogoOrdineServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Carrello car = (Carrello) request.getSession().getAttribute("carrello");
		Utente u = (Utente) request.getSession().getAttribute("utente");		
		
		// si procede alla creazione del bean Ordine
		Ordine ordine = new Ordine(u.getUsername(),new GregorianCalendar(new Locale("it", "IT")));
		ordine.setProdottiOrdine(car);

		//si rende persistente
		oDAO.createOrdine(ordine);
		//si rimuove il carrello dell'utente da DB perch� ora vuoto
		cDAO.removeCarrello(u.getUsername());
		car = new Carrello(u.getUsername(), u.getEmail());
		//e si rende persistente il nuovo carrello ora vuoto
		cDAO.createCarrello(car);

		//si settano nella sessione il Carrello e l'Ordine e si passa alla pagina di riepilogo
		request.getSession().setAttribute("ordine", ordine);
		request.getSession().setAttribute("carrello", car );

		//Si esegue la forward alla pagina RiepilogoOrdine del sito
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/RiepilogoOrdine.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
