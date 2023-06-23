package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RedirectInserimentoCartaServlet")
public class RedirectInserimentoCarta extends HttpServlet{

	private static final long serialVersionUID = 1L;
    
	public RedirectInserimentoCarta() {
        super();
       
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Si effettua il forward alla pagina PaginaPagamento.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/PaginaPagamento.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
