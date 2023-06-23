package controller.Test;

import static org.junit.Assert.*;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import controller.GestioneOffertaServlet;
import model.bean.Console;
import model.bean.Offerta;
import model.bean.Videogioco;
import model.dao.ConsoleDAO;
import model.dao.ConsoleDB;
import model.dao.OffertaDAO;
import model.dao.OffertaDB;
import model.dao.VideogiocoDAO;
import model.dao.VideogiocoDB;


public class GestioneOffertaServletTest {

	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static GestioneOffertaServlet servlet;
	
	@Before
	public void setUp() throws Exception {	
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new GestioneOffertaServlet();
		DatabaseHelper.initializeDatabase();
	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test inserimento offerta videogioco e controllo variazione di prezzo
	@Test
	public void testGestioneOffertaServletVideogiocoInserimento() throws ServletException, IOException {

		request.addParameter("operazione", "inserimento");
		request.addParameter("nome", "Black Friday");
		request.addParameter("sconto", "10");
		request.addParameter("categoria", "videogiochi");
		
		VideogiocoDAO vidDao=new VideogiocoDB();
		Videogioco vid=vidDao.findVideogiocoById(1);
		float prezzoIniziale=vid.getPrezzo();
		servlet.doPost(request,response);
		
		OffertaDAO off=new OffertaDB();
		Offerta o=off.retriveByCategoria("videogiochi").get(0);
		
		vidDao=new VideogiocoDB();
		vid=vidDao.findVideogiocoById(1);
		float prezzoFinale=vid.getPrezzo();
		
		boolean result;
		
		if(o.getNome().equals("Black Friday") && o.getSconto()==10 && prezzoIniziale>prezzoFinale)
			result=true;
		else
			result=false;
		
		assertTrue(result);
	}
	
	//Test rimozione offerta videogiochi e controllo variazione di prezzo
	@Test
	public void testGestioneOffertaServletVideogiocoRimozione() throws ServletException, IOException {

		request.addParameter("operazione", "inserimento");
		request.addParameter("nome", "Black Friday");
		request.addParameter("sconto", "10");
		request.addParameter("categoria", "videogiochi");
		servlet.doPost(request,response);
		
		VideogiocoDAO vidDao=new VideogiocoDB();
		Videogioco vid=vidDao.findVideogiocoById(1);
		float prezzoIniziale=vid.getPrezzo();
		request.addParameter("operazione", "rimozione");
		request.addParameter("offId", "1");
		//Elimina l'offerta
		servlet.doPost(request,response);
		
		vidDao=new VideogiocoDB();
		vid=new Videogioco();
		vid=vidDao.findVideogiocoById(1);
		float prezzoFinale=vid.getPrezzo();
		boolean result;
		
		if(prezzoIniziale!=prezzoFinale)
			result=true;
		else
			result=false;

		assertTrue(result);
	}
	
	//Test Inserimento offerta console e controllo variazione di prezzo
	@Test
	public void testGestioneOffertaServletConsoleInserimento() throws ServletException, IOException {

		request.addParameter("operazione", "inserimento");
		request.addParameter("nome", "Black Friday");
		request.addParameter("sconto", "10");
		request.addParameter("categoria", "Console");
		
		ConsoleDAO vidDao=new ConsoleDB();
		Console vid=vidDao.findConsoleById(2);
		float prezzoIniziale=vid.getPrezzo();
		servlet.doPost(request,response);
		
		OffertaDAO off=new OffertaDB();
		Offerta o=off.retriveByCategoria("Console").get(0);
		
		vidDao=new ConsoleDB();
		vid=vidDao.findConsoleById(2);
		float prezzoFinale=vid.getPrezzo();
		
		boolean result;
		
		if(o.getNome().equals("Black Friday") && o.getSconto()==10 && prezzoIniziale>prezzoFinale)
			result=true;
		else
			result=false;
		
		assertTrue(result);
	}
	
	//Test rimozione offerta console e controllo variazione di prezzo
	@Test
	public void testGestioneOffertaServletConsoleRimozione() throws ServletException, IOException {

		request.addParameter("operazione", "inserimento");
		request.addParameter("nome", "Black Friday");
		request.addParameter("sconto", "10");
		request.addParameter("categoria", "Console");
		servlet.doPost(request,response);
		
		ConsoleDAO vidDao=new ConsoleDB();
		Console vid=vidDao.findConsoleById(2);
		float prezzoIniziale=vid.getPrezzo();
		request.addParameter("operazione", "rimozione");
		request.addParameter("offId", "1");
		//Elimina l'offerta
		servlet.doPost(request,response);
		
		vidDao=new ConsoleDB();
		vid=new Console();
		vid=vidDao.findConsoleById(2);
		float prezzoFinale=vid.getPrezzo();
		boolean result;
		
		if(prezzoIniziale!=prezzoFinale)
			result=true;
		else
			result=false;

		assertTrue(result);
	}

}
