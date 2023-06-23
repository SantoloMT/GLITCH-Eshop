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
import model.dao.RichiestaDAO;
import model.dao.RichiestaDB; 

/**
 *  VisualizzazioneEmailServlet restituisce alla pagina EmailRicevute
 *  la lista di Email che l'utente ha ricevuto ( sia per utente che per gestore)
 */
@WebServlet("/VisualizzazioneEmailServlet")
public class VisualizzazioneEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RichiestaDAO rDAO = new RichiestaDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzazioneEmailServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Si prende dalla Sessione l'utente	
		Utente u = (Utente) request.getSession().getAttribute("utente");	
		
		//Si prendono da DB tutte le email avente come destinatario 
		//l'email dell'utente preso dalla Sessione 
		List<Richiesta> listaEmail = rDAO.retriveByDestinatario(u.getEmail());
		
		//Si setta la listaEmail nella request
		request.setAttribute("listaEmail", listaEmail);
		
		//Si esegue il forward alla pagina EmailRicevute
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/EmailRicevute.jsp");
		requestDispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
