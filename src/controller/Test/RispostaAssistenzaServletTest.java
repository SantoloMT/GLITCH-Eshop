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
import controller.RispostaAssistenzaServlet;
import model.bean.Richiesta;
import model.bean.Utente;
import model.dao.RichiestaDAO;
import model.dao.RichiestaDB;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

public class RispostaAssistenzaServletTest {

	static MockHttpServletRequest request;
	static MockHttpServletRequest requestInit;
	static MockHttpServletResponse response;
	static MockHttpServletResponse responseInit;
	static RispostaAssistenzaServlet servlet;
	static RichiestaAssistenzaServlet servletInit;
	
	@Before
	public void setUp() throws Exception {	
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new RispostaAssistenzaServlet();
		requestInit= new MockHttpServletRequest();
		responseInit=new MockHttpServletResponse();
		servletInit= new RichiestaAssistenzaServlet();
		DatabaseHelper.initializeDatabase();
	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		requestInit=null;
		responseInit=null;
		DatabaseHelper.resetDatabase();
	}

	//Test inserimento risposta nel database
	@Test
	public void testRispostaAssistenzaServlet() throws ServletException, IOException {

		UtenteDAO uDao=new UtenteDB();
		Utente u=uDao.retriveByUsername("Ferdinando98");
		requestInit.addParameter("destinatario", "adminCat99@glitch.com");
		requestInit.addParameter("descrizione", "La password non si cambia");
		requestInit.getSession().setAttribute("utente", u);
		
		//Creazione richiesta
		servletInit.doPost(requestInit,responseInit);
		
		uDao=new UtenteDB();
		u=new Utente();
		u=uDao.retriveByUsername("Admin98");
		
		request.addParameter("richiestaId", "11");
		request.addParameter("destinatario", "nando998@libero.it");
		request.addParameter("descrizione", "Riavvia il computer");
		request.getSession().setAttribute("utente", u);
		
		//Creazione risposta alla richiesta
		servlet.doPost(request,response);
		
		RichiestaDAO rDao=new RichiestaDB();
		Richiesta rc=new Richiesta();
		rc=rDao.retriveByMittente("adminCat99@glitch.com").get(0);
		boolean result;
		
		if(rc.getDescrizione().equals("Riavvia il computer") && rc.getDestinatario().equals("nando998@libero.it"))
			result=true;
		else
			result=false;
		
		assertTrue(result);
		
		
	}

}
