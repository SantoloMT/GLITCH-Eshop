package controller.Test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.RichiestaAssistenzaServlet;
import model.bean.Richiesta;
import model.bean.Utente;
import model.dao.RichiestaDAO;
import model.dao.RichiestaDB;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

public class RichiestaAssistenzaServletTest {

	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static RichiestaAssistenzaServlet servlet;
	
	@Before
	public void setUp() throws Exception {	
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new RichiestaAssistenzaServlet();
		DatabaseHelper.initializeDatabase();
	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test inserimento richiesta nel database
	@Test
	public void testRichiestaAssistenzaServlet() throws ServletException, IOException {
		
		UtenteDAO uDao=new UtenteDB();
		Utente u=uDao.retriveByUsername("Ferdinando98");
		request.addParameter("destinatario", "adminCat99@glitch.com");
		request.addParameter("descrizione", "La password non si cambia");
		request.getSession().setAttribute("utente", u);
		servlet.doPost(request,response);
		
		RichiestaDAO rDao=new RichiestaDB();
		Richiesta rc=rDao.retriveByMittente("nando998@libero.it").get(0);
		boolean result;
		
		if(rc.getDescrizione().equals("La password non si cambia") && rc.getDestinatario().equals("adminCat99@glitch.com"))
			result=true;
		else
			result=false;
		
		assertTrue(result);
		
		
	}

}
