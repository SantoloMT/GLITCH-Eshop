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

@WebServlet("/RedirectRispostaAssistenza")
public class RedirectRispostaAssistenza extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RichiestaDAO rDAO = new RichiestaDB();
    
	public RedirectRispostaAssistenza() {
        super();
       
    }
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//prendiamo l'utente dalla sessione
		Utente u = (Utente) request.getSession().getAttribute("utente");
		
		//Restituiamo alla GestioneAssistenza.jsp la lista delle mail del gestore
		List<Richiesta> list = rDAO.retriveByDestinatario(u.getEmail());
		request.setAttribute("richiesta", list);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/GestioneAssistenza.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
