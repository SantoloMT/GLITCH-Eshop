package controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CartaDiCredito;
import model.bean.Utente;
import model.bean.ValidazioneCartaDiCredito;
import model.dao.CartaDiCreditoDAO;
import model.dao.CartaDiCreditoDB;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;


/**
 * InserimentoCartaServlet permette di gestire l'inserimento 
 * di una carta di credito da parte dell'utente
 */
@WebServlet("/InserimentoCartaServlet")
public class InserimentoCartaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartaDiCreditoDAO cartaDAO = new CartaDiCreditoDB();
    private UtenteDAO uDAO = new UtenteDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoCartaServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Si prende l'Utente dalla Sessione
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		//Si prendono dalla request tutti i dati della Carta di Credito
		String numero = request.getParameter("numero");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String scadenzaMese = request.getParameter("scadenzaMese");
		String scadenzaAnno = request.getParameter("scadenzaAnno");
		String CVV = request.getParameter("cvv");
		
		//Si verifica la loro correttezza del formato, 
		//in caso di errore si lancia un'eccezione con relativo messaggio di errore 
		if ( !ValidazioneCartaDiCredito.checkNumeroIdentificativo(numero)) {
			throw new MyServletException("Formato Numero Identificativo Carta Errato.");
		}
		if ( !ValidazioneCartaDiCredito.checkNome(nome)) {
			throw new MyServletException("Formato Nome Intestatario Carta Errato.");
		}
		if ( !ValidazioneCartaDiCredito.checkCognome(cognome)) {
			throw new MyServletException("FormatoCognome Intestatario Carta Errato.");
		}
		if ( !ValidazioneCartaDiCredito.checkMeseScadenza(scadenzaMese)) {
			throw new MyServletException("Formato Scadenza Carta Errato.");
		}
		if ( !ValidazioneCartaDiCredito.checkAnnoScadenza(scadenzaAnno)) {
			throw new MyServletException("Formato Scadenza Carta Errato.");
		}
		if ( !ValidazioneCartaDiCredito.checkCVV(CVV)) {
			throw new MyServletException("Formato CVV Carta Errato.");
		}
		
		//Si crea il bean CartaDiCredito e si settano i dati
		CartaDiCredito carta = new CartaDiCredito(); 
		
		carta.setUtenteUsername(u.getUsername());
		carta.setUtenteEmail(u.getEmail());
		carta.setNome(nome);
		carta.setCognome(cognome);
		carta.setNumeroCarta(numero);
		
		GregorianCalendar scadenza = new GregorianCalendar(Integer.parseInt(scadenzaAnno), Integer.parseInt(scadenzaMese), 1);
		
		carta.setScadenza(scadenza);
		
		carta.setCvv(Integer.parseInt(CVV));
		
		//Si rende persistente il bean CartaDiCredito
		cartaDAO.createCartaDiCredito(carta);
		
		//Si setta la Carta nel bean Utente e si aggiorna quest'ultimo in DB
		u.setCartaDiCredito(carta);
		uDAO.updateCartaDiCreditoUtente(u);
		
		//Si inserisce l'utente aggiornato nella Sessione
		request.getSession().setAttribute("utente", u);
		
		//Si inserisce la carta nella request
		request.setAttribute("carta", u.getCartaDiCredito());
		
		//Si esegue il forward alla pagina RiepilogoOrdine
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/PaginaPagamento.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
