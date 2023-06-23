package controller.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import controller.InserimentoCartaServlet;
import controller.MyServletException;
import model.bean.CartaDiCredito;
import model.bean.Utente;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

public class InserimentoCartaServletTest {


	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static InserimentoCartaServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new InserimentoCartaServlet();
		DatabaseHelper.initializeDatabase();

	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test (successo: inserimento carta di credito con campi corretti)
	@Test
	public void testInserimentoCartaServletSuccesso() throws ServletException, IOException {

		UtenteDAO user=new UtenteDB();
		Utente u=user.retriveByUsername("Ferdinando98");
		
		request.getSession().setAttribute("utente", u);
		
		request.addParameter("numero", "3658745214569875");
		request.addParameter("nome", "Ferdinando");
		request.addParameter("cognome", "Napolitano");
		request.addParameter("scadenzaMese", "02");
		request.addParameter("scadenzaAnno", "22");
		request.addParameter("cvv", "737");
		
		servlet.doPost(request, response);
		
		u=user.retriveByUsername("Ferdinando98");
		
		CartaDiCredito carta=u.getCartaDiCredito();
		boolean result;
		
		if(carta.getNumeroCarta().equals("3658745214569875") && carta.getNome().equals("Ferdinando") &&
				carta.getCognome().equals("Napolitano") && carta.getCvv() == 737)
			result=true;
		else
			result=false;
		
		assertTrue(result);
		
	}
	
	//Test (fallimento: numero identificativo scorretto)
	@Test
	public void testInserimentoCartaServletFail() throws ServletException, IOException, MyServletException {
		UtenteDAO user=new UtenteDB();
		Utente u=user.retriveByUsername("Ferdinando98");
		
		request.getSession().setAttribute("utente", u);
		
		request.addParameter("numero", "3658745214569545875");
		request.addParameter("nome", "Ferdinando");
		request.addParameter("cognome", "Napolitano");
		request.addParameter("scadenzaMese", "02");
		request.addParameter("scadenzaAnno", "22");
		request.addParameter("cvv", "737");
		
		
		 final String message = "Formato Numero Identificativo Carta Errato.";
			MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());	
		
	}

}
