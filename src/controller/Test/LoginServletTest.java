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

import controller.LoginServlet;
import controller.MyServletException;
import model.bean.Utente;

public class LoginServletTest {

	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static LoginServlet servlet;
	
	@Before
	public void setUp() throws Exception {	
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new LoginServlet();
		DatabaseHelper.initializeDatabase();
	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test (successo: username e password presenti nel database)
	@Test
	public void testLoginServlet() throws ServletException, IOException {
		request.addParameter("username", "Ferdinando98");
		request.addParameter("password", "Ferdinando98");

		servlet.doPost(request, response);
		
		boolean result;

		Utente u=(Utente) request.getSession().getAttribute("utente");
	
		if(u!=null)
			result=true;
		else
			result=false;
		
		assertEquals(result, true);
	}
	
	//Test (fallimento: username e password non presenti nel database)
	@Test
	public void testLoginServletFail() throws ServletException, IOException, MyServletException{
		request.addParameter("username", "Sandro98");
		request.addParameter("password", "12345678CVb");
		
		final String message = "Username e/o password non validi.";
		MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());	
	}
	
	//Test (fallimento: username presente in database e password non corrispondente ad esso)
	@Test
	public void testLoginServletPasswordFail() throws ServletException, IOException, MyServletException{
		request.addParameter("username", "Ferdinando98");
		request.addParameter("password", "12345678CVb");
			
		final String message = "Username e/o password non validi.";
		MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());	
	}

}
