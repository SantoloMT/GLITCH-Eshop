package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UtenteDAO;
import model.dao.UtenteDB;

@WebServlet("/RedirectRichiestaAssistenzaServlet")
public class RedirectRichiestaAssistenza extends HttpServlet{

	private static final long serialVersionUID = 1L;
    private UtenteDAO uDAO = new UtenteDB();	
	public RedirectRichiestaAssistenza() {
        super();
       
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Si prendono da DB tutte le e-mail dei gestori adibiti all'Assistenza clienti 
		List<String> list = uDAO.retriveEmailByAssistenza("Assistenza");
		
		//Si inserisce la lista nella request e si effettua la forward alla pagina RichiestaAssistenza.jsp
		request.setAttribute("destinatario", list);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/RichiestaAssistenza.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
