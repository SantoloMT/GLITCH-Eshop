package controller.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.GestioneCarrelloServlet;
import model.bean.Carrello;
import model.bean.ProdottoQuantita;
import model.bean.Utente;
import model.dao.CarrelloDAO;
import model.dao.CarrelloDB;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

public class GestioneCarrelloTest {

	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static GestioneCarrelloServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new GestioneCarrelloServlet();
		DatabaseHelper.initializeDatabase();

	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test inserimento prodotto nel carrello
	@Test
	public void testGestioneCarrelloServlet() throws ServletException, IOException {
	
		UtenteDAO user=new UtenteDB();
		Utente u=user.retriveByUsername("Ferdinando98");
		
		request.getSession().setAttribute("utente", u);
		
		
		Carrello carrello = new Carrello();
		carrello.setUsername(u.getUsername());
		carrello.setUtenteEmail(u.getEmail());
		
		request.getSession().setAttribute("carrello", carrello);
		
	
		request.addParameter("operazione", "Inserimento");
		request.addParameter("prodId", "1");
		request.addParameter("number", "1");
		servlet.doPost(request, response);
		
		CarrelloDAO cartDAO=new CarrelloDB();
		Carrello cart =cartDAO.retriveByUtente(u);
		boolean result;
		
		ProdottoQuantita prod=cart.get(1);
		if(prod.getProdotto().getId() == 1 && prod.getQuantita() == 1)
			result=true;
		else
			result=false;
		
		assertEquals(result, true);
		
	}

}
