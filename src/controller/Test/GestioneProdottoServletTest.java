package controller.Test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.GestioneProdottoServlet;
import controller.MyServletException;
import model.bean.Console;
import model.bean.Videogioco;
import model.dao.ConsoleDAO;
import model.dao.ConsoleDB;
import model.dao.ProdottoDAO;
import model.dao.ProdottoDB;
import model.dao.VideogiocoDAO;
import model.dao.VideogiocoDB;

public class GestioneProdottoServletTest {

	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static GestioneProdottoServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new GestioneProdottoServlet();
		DatabaseHelper.initializeDatabase();

	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}
	
	//Test (successo: Aggiunta videogiochi) 
	@Test
	public void testGestioneProdottoServletVideogioco() throws ServletException, IOException, MyServletException {

		request.addParameter("operazione", "inserimento");
		request.addParameter("Videogioco", "videogioco");
		request.addParameter("immagine", "test/img");
		request.addParameter("descrizione", "Videogioco di guerra");
		request.addParameter("prezzo", "70.00");
		request.addParameter("nome", "Battlefield");
		request.addParameter("genere", "FPS");
		request.addParameter("piattaforma", "XboxOne");
		
		servlet.doPost(request, response);
		
		VideogiocoDAO vidDao=new VideogiocoDB();
		Videogioco vid= vidDao.retriveByNome("Battlefield").get(0);
		
		boolean result;

		if(vid.getNome().equals("Battlefield") && vid.getDescrizione().equals("Videogioco di guerra") && vid.getGenere().equals("FPS") && vid.getPiattaforma().equals("XboxOne"))
			result=true;
		else
			result=false;
		
		assertTrue(result);	
	}
	
	//Test (successo: aggiunta console)
	@Test
	public void testGestioneProdottoServletConsole() throws ServletException, IOException, MyServletException {

		request.addParameter("operazione", "inserimento");
		request.addParameter("Console", "console");
		request.addParameter("immagine", "test/img");
		request.addParameter("descrizione", "Console potente");
		request.addParameter("prezzo", "100.00");
		request.addParameter("modello", "Xbox360");
		request.addParameter("casaProduttrice", "Microsoft");

		servlet.doPost(request, response);
		
		ConsoleDAO consDao=new ConsoleDB();
		Console cons=consDao.retriveByModello("Xbox360");
		
		boolean result;

		if(cons.getCasaProduttrice().equals("Microsoft") && cons.getDescrizione().equals("Console potente") && cons.getPrezzo()==100.00)
			result=true;
		else
			result=false;
		
		assertTrue(result);	
	}
	
	//Test (successo: rimozione prodotti)
	@Test
	public void testGestioneProdottoServletRimozione() throws ServletException, IOException, MyServletException {

		request.addParameter("operazione", "rimozione");
		request.addParameter("prodId", "1");
		servlet.doPost(request, response);
		
		ProdottoDAO prodDao=new ProdottoDB();
		boolean result;
		if(prodDao.findProdottoById(1) == null)
			result=true;
		else
			result=false;
		
		assertTrue(result);
			
	}

}
