package controller;

import java.io.IOException;
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
 * RichiestaAssistenzaServlet permette di gestire l'invio di una mail di richiesta di assistenza
 */
@WebServlet("/RichiestaAssistenzaServlet")
public class RichiestaAssistenzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RichiestaDAO rDAO = new RichiestaDB();
		
		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiestaAssistenzaServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Si prendono dalla request l'email del destinatario e la descrizione della mail di richiesta
		String destinatario = request.getParameter("destinatario");
		String descrizione = request.getParameter("descrizione");
		Utente u = (Utente) request.getSession().getAttribute("utente");
		//Si verifica la correttezza del formato della descrizione
		if(!ValidazioneRichiesta.checkDescrizione(descrizione)) {
			
			//Nel caso di incorrettezza si lanci un'eccezione
			throw new MyServletException("Formato Descrizione non corretto.");
		}
		
		//Si crea il bean Richiesta e si settano i dati
		Richiesta r = new Richiesta(u.getEmail(),u.getUsername(),destinatario,descrizione);
		
		r.setStato(false);// lo stato � folse finch� il destinatario non la legger�/visualizzer�
		
		//Si rende persistente la Richiesta
		rDAO.createRichiesta(r);
		//Si setta quest'attributo "successo" per far apparire un pop-up
		//di successo nella view corrispondente
		request.setAttribute("successo", true);
		
		//Si esegue il forward alla pagina RichiestaAssistenza
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/RichiestaAssistenza.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
