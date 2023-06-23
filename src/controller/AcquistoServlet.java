package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Carrello;
import model.bean.Ordine;
import model.bean.Richiesta;
import model.bean.Utente;
import model.dao.CarrelloDAO;
import model.dao.CarrelloDB;
import model.dao.OrdineDAO;
import model.dao.OrdineDB;
import model.dao.RichiestaDAO;
import model.dao.RichiestaDB;



/**
 *AcquistoServlet permette la gestione dell'acquisto dei prodotti inseriti nel carrello
 */
@WebServlet("/AcquistoServlet")
public class AcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdineDAO oDAO = new OrdineDB();
	private CarrelloDAO cDAO = new CarrelloDB();
	private RichiestaDAO rDAO = new RichiestaDB(); 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcquistoServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operazione = request.getParameter("operazione");
		//Si prendono dalla Session Utente e Ordine 
		Utente u  = (Utente) request.getSession().getAttribute("utente");
		Ordine o = (Ordine) request.getSession().getAttribute("ordine");
		if(operazione.equalsIgnoreCase("annullamento")) {
			request.removeAttribute("ordine");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/Carrello.jsp");
			requestDispatcher.forward(request, response);
		}else {//è una conferma
			//creo il bean ordine in DB
			oDAO.createOrdine(o);

			// si procede alla compilazione automatica di una mail di conferma ordine
			//Si settano i dati relativi alla Richiesta
			Richiesta mail = new Richiesta("generAutoMail@live.com","botGlitch",u.getEmail(),"L'ordine da lei effettuato in data :" + 
					o.getDataOrdinazione() + "è andato a buon fine."
					+ " Verrà avvisato della spedizione il prima possibile."
					+ "/n Grazie di aver scelto GLITCH!");



			//Si rende il bean persistente
			rDAO.createRichiesta(mail);
			List<Richiesta> list = rDAO.retriveEmailNonLette(u.getEmail());
			//Si setta un attributo "email"con il numero di email non lette
			request.setAttribute("email", list.size());
			
			//Si svuota il carrello
			cDAO.removeCarrello(u.getUsername());
			Carrello c = new Carrello(u.getUsername(), u.getEmail());
			request.getSession().setAttribute("carrello", c);
			//Si esegue la forward alla pagina Home del sito
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("BaseServlet");
			requestDispatcher.forward(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
