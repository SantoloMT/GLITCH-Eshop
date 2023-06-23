package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Richiesta;
import model.dao.RichiestaDAO;
import model.dao.RichiestaDB;

@WebServlet("/LeggiRisposta")
public class LeggiRisposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RichiestaDAO rDAO = new RichiestaDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeggiRisposta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//si prende dalla richiesta la email che voglio visualizzare
		Richiesta r = rDAO.retriveById(Integer.parseInt(request.getParameter("email")));
		//si setta nella request
		request.setAttribute("richiesta", r);
		
		//effettua la forward a GestioneAssistenza.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/VisualizzaRisposta.jsp");
		requestDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

