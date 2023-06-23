package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Richiesta;
import model.bean.Utente;
import model.bean.ValidazioneRichiesta;
import model.dao.RichiestaDAO;
import model.dao.RichiestaDB;


/**
 * RispostaAssistenzaServlet permette di gestire l'invio di una mail di risposta assistenza
 */
@WebServlet("/RispostaAssistenzaServlet")
public class RispostaAssistenzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RichiestaDAO rDAO = new RichiestaDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RispostaAssistenzaServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Tramite l'id passato nella request si preleva 
		//da DB la richiesta a cui il gestore vuole risopondere
		Richiesta rc = rDAO.retriveById(Integer.parseInt(request.getParameter("richiestaId")));
		
		rc.setStato(true);// il suo stato ora e' true perche' e' stata letta
		
		rDAO.updateRichiesta(rc);
		//si preleva dalla richiesta la mail dell'utente a cui il gestore vuole risopondere
		String mail = rc.getUtenteEmail();
				
		//Si prende dalla request la descrizione della mail di risposta
		String descrizione = request.getParameter("descrizione");
		System.out.println(descrizione);
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		//Si verifica la correttezza del formato della descrizione
		if(!ValidazioneRichiesta.checkDescrizione(descrizione)) {
			
			//Nel caso di incorrettezza si lanci un'eccezione
			throw new MyServletException("Formato Descrizione non corretto.");
		}
		
		//Si crea il bean Richiesta e si settano i dati
		Richiesta rs = new Richiesta(u.getEmail(),u.getUsername(), mail ,descrizione);
		
		rs.setStato(false);// lo stato resta false finche' il destinatario non la leggera'/visualizzera'
		
		//Si rende persistente la Richiesta
		rDAO.createRichiesta(rs);
		//Si setta quest'attributo "successo" per far apparire un pop-up
		//di successo nella view corrispondente
		request.setAttribute("successo", true);
		
		//aggiorno la lista delle mail non lette dal gestore
		List<Richiesta> list = rDAO.retriveByDestinatario(u.getEmail());
		request.setAttribute("richiesta", list);
		//Si esegue il forward alla pagina GestioneAssistenza.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/GestioneAssistenza.jsp");
		requestDispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
