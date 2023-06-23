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
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

@WebServlet("/RedirectGestioneRuoloServlet")
public class RedirectGestioneRuolo extends HttpServlet{

	private static final long serialVersionUID = 1L;
    private UtenteDAO uDAO = new UtenteDB();
	
    public RedirectGestioneRuolo() {
        super();
       
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Si prendono da DB tutti gli utenti
		List<Utente> list = uDAO.retriveAllUtenti(); 
		
		//Si inseriscono nella request
		request.setAttribute("Utente", list);
		
		//Si effettua la forward alla pagina GestioneRuoli.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/GestioneRuoli.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
