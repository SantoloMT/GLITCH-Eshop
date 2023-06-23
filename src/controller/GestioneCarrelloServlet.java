package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Carrello;
import model.bean.Prodotto;
import model.dao.CarrelloDAO;
import model.dao.CarrelloDB;
import model.dao.ProdottoDAO;
import model.dao.ProdottoDB;

/**
 * GestioneCarrelloServlet permette di gestire le opeerazioni connesse al carrello, 
 * ovvero rimozione prodotto dal carrello, modifica quantit� prodotto nel carrello 
 * e inserimento dei prodotti nel carrello
 */
@WebServlet("/GestioneCarrelloServlet")
public class GestioneCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarrelloDAO cDAO = new CarrelloDB();
	private ProdottoDAO pDAO = new ProdottoDB() ;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestioneCarrelloServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//prendo il parametro "operazione" dalla rquest e controllo se corrisponde ad una rimozione,
		//una modifica o inserimento
		String operazione = request.getParameter("operazione");
		
		Carrello car = (Carrello) request.getSession().getAttribute("carrello");
		if(operazione.equalsIgnoreCase("inserimento")) {

			//se e' un inserimetno si procede all'aggiunto di un prodotto nel carrello
			//si prende l'id del prodotto e la quantita' da request 
			//e si prende da DB il prodotto con l'id corrispondente
			Prodotto p = pDAO.findProdottoById(Integer.parseInt(request.getParameter("prodId")));
			int quantita = Integer.parseInt(request.getParameter("number"));
			
			//controllo se il prodotto da inserire e' gia' presente nel carrello
			if(car.get(p.getId()) != null) {
				// se e' presente, aggiorno la quantita'
				car.updateQuantita(p.getId(), car.get(p.getId()).getQuantita() + quantita);
				
			}else {
			//si inserisce il prodotto nel carrello
			car.put(p, quantita);
			
			}
			//si aggiorna il carrello dell'utente
			cDAO.createCarrello(car);

			//si settano nella sessione il Carrello si torna al catalogo
			request.getSession().setAttribute("carrello", car );
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/Carrello.jsp");
			requestDispatcher.forward(request, response);

		}else if(operazione.equalsIgnoreCase("rimozione")) {

			// se e' una rimozione si procede a rimuovere il prodotto indicato nel carrello

			//si rimuove da carrello il prodotto con l'Id preso dalla request
			car.remove( Integer.parseInt(request.getParameter("prodId") ));

			//si aggiorna il carrello in DB
			cDAO.doDelete(Integer.parseInt(request.getParameter("prodId")), car.getUsername());

			//si salva il carrello modificato nella sessione e si ritorna alla pagina carrello
			request.getSession().setAttribute("carrello", car );
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/Carrello.jsp");
			requestDispatcher.forward(request, response);

		}else {

			//se e' una modifica della quantita' di un prodotto

			//si procede ad aggiornare la quantita' del prodotto indicato dall'id preso dalla request
			car.updateQuantita(Integer.parseInt(request.getParameter("prodId")), 
					Integer.parseInt( request.getParameter("quantita")));

			//si aggiorna il carrello in DB
			cDAO.doUpdate(car.get(Integer.parseInt(request.getParameter("prodId"))));

			//si setta il carrello modificato nella sessione 
			request.getSession().setAttribute("carrello", car );
			
			//Si esegue la forward alla pagina Carrello del sito
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/Carrello.jsp");
			requestDispatcher.forward(request, response);

		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
