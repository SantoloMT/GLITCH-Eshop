package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Carrello;
import model.bean.Utente;
import model.bean.ValidazioneUtente;
import model.dao.UtenteDAO;
import model.dao.UtenteDB; 



/* RegistrazioneServlet permette di gestire i dati riguardanti la registrazione di un utente. 
 * Verifica la loro correttezza ed esegue l'accesso all'interfaccia utente
 * */

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDB();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrazioneServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//verifico il formato dei dati utente, in caso di errore, lancio un'eccezione

		String username = request.getParameter("username");
		if ( !ValidazioneUtente.checkUsername(username)) {
			throw new MyServletException("Formato Username Errato.");
		}

		String password = request.getParameter("password");
		if ( !ValidazioneUtente.checkPassword(password)) {
			throw new MyServletException("Formato Password Errato.");
		}

		String nome = request.getParameter("nome");
		if ( !ValidazioneUtente.checkNome(nome)) {
			throw new MyServletException("Formato Nome Errato.");
		}

		String cognome = request.getParameter("cognome");
		if ( !ValidazioneUtente.checkCognome(cognome)) {
			throw new MyServletException("Formato Cognome Errato.");
		}

		String email = request.getParameter("email");
		if ( !ValidazioneUtente.checkEmail(email)) {
			throw new MyServletException("Formato E-Mail Errato.");
		}

		String provincia = request.getParameter("provincia");
		if ( !ValidazioneUtente.checkProvincia(provincia)) {
			throw new MyServletException("Formato Provincia Errato.");
		}

		String CAP = request.getParameter("CAP");
		if ( !ValidazioneUtente.checkCap(CAP)) {
			throw new MyServletException("Formato CAP Errato.");
		}

		String citta = request.getParameter("citta");
		if ( !ValidazioneUtente.checkCitta(citta)) {
			throw new MyServletException("Formato Citta Errato.");
		}

		String strada = request.getParameter("via");
		if ( !ValidazioneUtente.checkVia(strada)) {
			throw new MyServletException("Formato Via Errato.");
		}

		String numero = request.getParameter("numero");
		if ( !ValidazioneUtente.checkNumero(numero)) {
			throw new MyServletException("Formato Numero Errato.");
		}

		int cap = Integer.parseInt(CAP);
		int num = Integer.parseInt(numero);
				//creo il bean Utente e verifico se ï¿½ giï¿½ presente in DB

		Utente u = new Utente( username, email, password, nome, cognome, provincia, cap, citta, strada, num);
		
		if(utenteDAO.retriveByUsername(username) == null &&
				utenteDAO.retriveByEmail(email) == null) {
			
			//Poichï¿½ non ï¿½ presente, lo aggiungo in DB e alla sessione
			
			utenteDAO.createUtente(u);
			request.getSession().setAttribute("utente", u);
			request.getSession().setMaxInactiveInterval(600000);
			//Creo il bean Carrello per il nuovo utente e lo aggiungo alla sessione
			
			Carrello carrello = new Carrello();

			carrello.setUsername(u.getUsername());
			carrello.setUtenteEmail(u.getEmail());
			
			request.getSession().setAttribute("carrello", carrello);
			
			//Conclusa la registrazione, si fa la forward alla pagina Registrazionedel sito
			//mostrando un messaggio di avvenuto successo
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("BaseServlet");
			requestDispatcher.forward(request, response);

		}
		else {

			throw new MyServletException("Username o email già presenti.");

		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
