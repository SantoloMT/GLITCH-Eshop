package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Console;
import model.bean.Videogioco;
import model.dao.ConsoleDAO;
import model.dao.ConsoleDB;
import model.dao.VideogiocoDAO;
import model.dao.VideogiocoDB;


@WebServlet("/RicercaProdott")
public class RicercaProdotto  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private VideogiocoDAO vDAO = new VideogiocoDB();
	private ConsoleDAO cDAO = new ConsoleDB();
       

    public RicercaProdotto() {
        super();
    }

    //La servlet permette di visualizzare i prodotti scelti dall'utente 
    //attraverso la ricerca tramite la barra di ricerca posta nell' header
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nome = request.getParameter("cerca");
		
		List<Videogioco> vid=new ArrayList<Videogioco>();
		
		vid = vDAO.retriveByNome(nome);
		//System.out.println("xxxx " +vid.get(0));
		Console cons =  cDAO.retriveByModello(nome);
		
		if(vid == null || vid.isEmpty()) {
			request.getSession().setAttribute("ricerca", "Console");
			request.getSession().setAttribute("itemTrovati", cons);
		}
		else if(cons==null){
			request.getSession().setAttribute("ricerca", "Videogioco");
			request.getSession().setAttribute("itemTrovati", vid);
		}
		else
			request.getSession().setAttribute("ricerca", null);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/RicercaProdotto.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
