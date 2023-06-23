package controller.Test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import controller.RiepilogoOrdineServlet;
import model.bean.Carrello;
import model.bean.Ordine;
import model.bean.Utente;
import model.dao.CarrelloDAO;
import model.dao.CarrelloDB;
import model.dao.OrdineDAO;
import model.dao.OrdineDB;
import model.dao.ProdottoDAO;
import model.dao.ProdottoDB;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

public class RiepilogoOrdineServletTest {

	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static RiepilogoOrdineServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new RiepilogoOrdineServlet();
		DatabaseHelper.initializeDatabase();

	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test simula l'acquisto e crea un ordine
	@Test
	public void testRiepilogoOrdineServlet() throws ServletException, IOException {
		UtenteDAO user=new UtenteDB();
		Utente u=user.retriveByUsername("Ferdinando98");
		ProdottoDAO prod=new ProdottoDB();
		CarrelloDAO cartDao=new CarrelloDB();
		Carrello carrello = new Carrello();
		carrello.setUsername(u.getUsername());
		carrello.setUtenteEmail(u.getEmail());
		carrello.put(prod.findProdottoById(1), 1);
		
		//Inizializza e salva nel database un carrello
		cartDao.createCarrello(carrello);
		request.getSession().setAttribute("carrello", carrello);
		request.getSession().setAttribute("utente", u);
		servlet.doPost(request, response);
		
		OrdineDAO ordDao=new OrdineDB();
		Ordine ord=ordDao.retriveById(1);
		boolean result;
		
		if(ord.getUtenteUsername().equals("Ferdinando98") && ord.getProdottiAcquistati().get(0).getNome().equals("Call of Duty: Modern Warfare"))
			result=true;
		else
			result=false;
		
		assertTrue(result);
		
	}

}
