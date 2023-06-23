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

@WebServlet("/RedirectRispostaUtente")
public class RedirectRispostaUtente extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RichiestaDAO rDAO = new RichiestaDB();
    
	public RedirectRispostaUtente() {
        super();
       
    }
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Si prende dalla sessione l'utente e da Db si fanno ritornare tutte le email in arrivo all'utente
		
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		List<Richiesta> list = rDAO.retriveByDestinatario(u.getEmail());
		
		//Si setta la lista nella request e si effettua il forward alla pagina VisualizzaListaRisposte.jsp
		request.setAttribute("richiesta", list);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/VisualizzaListaRisposte.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}

