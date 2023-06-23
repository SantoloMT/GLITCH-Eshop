package controller.Test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import controller.GestioneRuoloServlet;
import model.bean.Utente;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

public class GestioneRuoloServletTest {

	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static GestioneRuoloServlet servlet;
	
	@Before
	public void setUp() throws Exception {	
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new GestioneRuoloServlet();
		DatabaseHelper.initializeDatabase();
	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test aggiunta ruolo
	@Test
	public void testGestioneRuoloServletAggiunta() throws ServletException, IOException {
		request.addParameter("operazione", "aggiunta");
		request.addParameter("userUtente", "Ferdinando98");
		request.addParameter("ruolo", "Account");
		servlet.doPost(request,response);
		
		UtenteDAO uDao=new UtenteDB();
		Utente user=uDao.retriveByUsername("Ferdinando98");
		boolean result;
		if(user.getRuolo().equals("Account"))
			result=true;
		else
			result=false;
		
		assertTrue(result);
		
	}
	
	//Test rimozione ruolo
	@Test
	public void testGestioneRuoloServletRimozione() throws ServletException, IOException {
		request.addParameter("operazione", "rimozione");
		request.addParameter("userUtente", "Ferdinando98");
		request.addParameter("ruolo", "Account");
		servlet.doPost(request,response);
		
		UtenteDAO uDao=new UtenteDB();
		Utente user=uDao.retriveByUsername("Ferdinando98");
		boolean result;
		if(user.getRuolo()==null)
			result=true;
		else
			result=false;
		
		assertTrue(result);
		
	}

}
