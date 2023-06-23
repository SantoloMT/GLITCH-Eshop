package controller.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.GestioneProfiloServlet;
import controller.MyServletException;
import model.bean.Carrello;
import model.bean.Utente;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

public class GestioneProfiloServletTest {

	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static GestioneProfiloServlet servlet;
	
	@Before
	public void setUp() throws Exception {	
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new GestioneProfiloServlet();
		DatabaseHelper.initializeDatabase();
	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test (successo: modifica profilo)
	@Test
	public void testGestioneProfiloServletTest() throws ServletException, IOException, RuntimeException {


		UtenteDAO user=new UtenteDB();
		Utente u=user.retriveByUsername("Ferdinando98");
		
		request.getSession().setAttribute("utente", u);
		
		
		Carrello carrello = new Carrello();
		carrello.setUsername(u.getUsername());
		carrello.setUtenteEmail(u.getEmail());
		
		
		request.getSession().setAttribute("carrello", carrello);
		request.addParameter("operazione", "modifica");
		request.addParameter("password", "12345678Cvb");
		request.addParameter("provincia", "");
		request.addParameter("CAP", "");
		request.addParameter("citta", "");
		request.addParameter("via", "");
		request.addParameter("numero", "");
		
		servlet.doPost(request, response);
		
		boolean result;

		u=user.retriveByUsername("Ferdinando98");
	
		if(u.getPassword().equals("12345678Cvb"))
			result=true;
		else
			result=false;
		
		assertTrue(result);
	}
	
		//Test (Fallito: Formato password errato)
		@Test
		public void testGestioneProfiloServletTestFail() throws ServletException, IOException, RuntimeException {


			UtenteDAO user=new UtenteDB();
			Utente u=user.retriveByUsername("Ferdinando98");
			
			request.getSession().setAttribute("utente", u);
			
			
			Carrello carrello = new Carrello();
			carrello.setUsername(u.getUsername());
			carrello.setUtenteEmail(u.getEmail());
			
			
			request.getSession().setAttribute("carrello", carrello);
			request.addParameter("operazione", "modifica");
			request.addParameter("password", "12345678vb");
			request.addParameter("email", "");
			request.addParameter("provincia", "");
			request.addParameter("CAP", "");
			request.addParameter("citta", "");
			request.addParameter("via", "");
			request.addParameter("numero", "");
			
			final String message = "Formato Password Errata.";
			MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());	
		}

}
