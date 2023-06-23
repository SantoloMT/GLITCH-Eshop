package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Utente;
import model.bean.ValidazioneUtente;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

/**
 * GestioneRuoloServlet permette di gestire l'inserimento di un ruolo 
 * o la sua eliminazione d aparte del gestore Account
 */
@WebServlet("/GestioneRuoloServlet")
public class GestioneRuoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO uDAO = new UtenteDB();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestioneRuoloServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Si prende dalla request il parametro "operazione", l'utente dalla Sessione e
		// il ruolo dalla request
		String operazione = request.getParameter("operazione");
		Utente u = uDAO.retriveByUsername(request.getParameter("userUtente"));
		

		//si verifica che "operazione" sia un "aggiunta" o "rimozione"
		if(operazione.equalsIgnoreCase("aggiunta")) {

			String ruolo = request.getParameter("ruolo");
			//Si verifica che il formato di ruolo sia corretto altrimenti si lancia un'eccezione
			if(!ValidazioneUtente.checkRuolo(ruolo)) {
				throw new MyServletException("Formato Ruolo non corretto");
			}
			
			//Se e' un'aggiunta, si assegna il ruolo all'utente selezionato
			u.setRuolo(ruolo);

			//Se l'assegnamento avviene con successo si aggiorna l'utente nel DB
			uDAO.updateRuoloUtente(u);
			//Si ritornano da DB tutti gli utenti presenti
			List<Utente> utenti = uDAO.retriveAllUtenti();
			//Si settano nella request in modo da poterli mostrare nella view
			request.getSession().setAttribute("Utente", utenti);


		}else {
			// Se invece e' una rimozione, si procede a rimuovere il ruolo dall'utente
			u.removeRuolo();
			//Si aggiorna l'utente
			uDAO.updateRuoloUtente(u);
			//Si ritornano da DB tutti gli utenti presenti
			List<Utente> utenti = uDAO.retriveAllUtenti();
			//Si settano nella request in modo da poterli mostrare nella view
			request.getSession().setAttribute("Utente", utenti);

		}

		//Si esegue la forward alla pagina GestioneRuolo
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/GestioneRuoli.jsp");
		requestDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
