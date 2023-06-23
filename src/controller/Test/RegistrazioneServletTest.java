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

import controller.MyServletException;
import controller.RegistrazioneServlet;
import model.bean.Utente;
import model.dao.UtenteDAO;
import model.dao.UtenteDB;

public class RegistrazioneServletTest {


	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static RegistrazioneServlet servlet;
	
	@Before
	public void setUp() throws Exception {
		
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new RegistrazioneServlet();
		DatabaseHelper.initializeDatabase();
		
	}

	@After
	public void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
	}

	//Test (successo: campi sintatticamente corretti)
	@Test
	public void testRegistrazioneServletConSuccesso() throws ServletException, IOException, MyServletException{
		
		request.addParameter("nome", "Sandro");
		request.addParameter("cognome", "Mutone");
		request.addParameter("provincia", "NA");
		request.addParameter("CAP", "80033");
		request.addParameter("citta", "Nola");
		request.addParameter("via", "Via albano carrera");
		request.addParameter("numero", "45");
		request.addParameter("email", "s.mutone@studenti.unisa.it");
		request.addParameter("username", "paolo22");
		request.addParameter("password", "Cicalesse98");
		servlet.doPost(request, response);
		
		UtenteDAO user=new UtenteDB();
		Utente u= user.retriveByUsername("paolo22");
		boolean result;
		
		if(u!=null)
			result=true;
		else
			result=false;
		
		assertTrue(result);
	}
	
	//Test (fallito: formato nome scorretto)
	@Test 
	public void testRegistrazioneServletNomeFallita() throws ServletException, IOException, MyServletException{
		
		
		request.addParameter("nome", "Sandro1");
		request.addParameter("cognome", "Mutone");
		request.addParameter("provincia", "NA");
		request.addParameter("CAP", "80033");
		request.addParameter("citta", "Nola");
		request.addParameter("via", "Via albano carrera");
		request.addParameter("numero", "45");
		request.addParameter("email", "s.mutone@studenti.unisa.it");
		request.addParameter("username", "paolo22");
		request.addParameter("password", "Cicalesse98");

	
		 final String message = "Formato Nome Errato.";
			MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());	
	}
	
		//Test (fallito: formato cognome scorretto)
		@Test 
		public void testRegistrazioneServletCognomeFallita() throws ServletException, IOException, MyServletException{
			
			request.addParameter("nome", "Sandro");
			request.addParameter("cognome", "Mutone2");
			request.addParameter("provincia", "NA");
			request.addParameter("CAP", "80033");
			request.addParameter("citta", "Nola");
			request.addParameter("via", "Via albano carrera");
			request.addParameter("numero", "45");
			request.addParameter("email", "s.mutone@studenti.unisa.it");
			request.addParameter("username", "paolo22");
			request.addParameter("password", "Cicalesse98");
		
			 final String message = "Formato Cognome Errato.";
				MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
					servlet.doPost(request, response);
				});
				assertEquals(message, exceptionThrown.getMessage());	
		}
		
			//Test (fallito: formato provincia scorretto)
			@Test 
			public void testRegistrazioneServletProvinciaFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "Via pascoli 21");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45");
				request.addParameter("email", "s.mutone@studenti.unisa.it");
				request.addParameter("username", "paolo22");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Formato Provincia Errato.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
			//Test (fallito: formato CAP scorretto)
			@Test 
			public void testRegistrazioneServletCAPFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "LALA9");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45");
				request.addParameter("email", "s.mutone@studenti.unisa.it");
				request.addParameter("username", "paolo22");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Formato CAP Errato.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
		
			
			//Test (fallito: formato citta scorretto)
			@Test 
			public void testRegistrazioneServletCittaFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola95");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45");
				request.addParameter("email", "s.mutone@studenti.unisa.it");
				request.addParameter("username", "paolo22");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Formato Citta Errato.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
			//Test (fallito: formato via scorretto)
			@Test 
			public void testRegistrazioneServletViaFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera! 58");
				request.addParameter("numero", "45");
				request.addParameter("email", "s.mutone@studenti.unisa.it");
				request.addParameter("username", "paolo22");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Formato Via Errato.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
			//Test (fallito: formato numero scorretto)
			@Test 
			public void testRegistrazioneServletNumeroFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45gf");
				request.addParameter("email", "s.mutone@studenti.unisa.it");
				request.addParameter("username", "paolo22");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Formato Numero Errato.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
			//Test (fallito: formato email scorretto)
			@Test 
			public void testRegistrazioneServletEmailFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45");
				request.addParameter("email", "s.mutone@@studenti.unisa.it");
				request.addParameter("username", "paolo22");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Formato E-Mail Errato.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
			//Test (fallito: email esistente)
			@Test 
			public void testRegistrazioneServletEmailEsistenteFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45");
				request.addParameter("email", "nando998@libero.it");
				request.addParameter("username", "paolo22");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Username o email già presenti.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
			//Test (fallito: formato username scorretto)
			@Test 
			public void testRegistrazioneServletUsernameFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45");
				request.addParameter("email", "s.mutone@studenti.unisa.it");
				request.addParameter("username", "paolo22!@");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Formato Username Errato.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
			//Test (fallito: nickname esistente)
			@Test 
			public void testRegistrazioneServletUsernameEsistenteFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45");
				request.addParameter("email", "s.mutone@studenti.unisa.it");
				request.addParameter("username", "Ferdinando98");
				request.addParameter("password", "Cicalesse98");
				
				 final String message = "Username o email già presenti.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}
			
			//Test (fallito: formato password scorretto)
			@Test 
			public void testRegistrazioneServletPasswordFallita() throws ServletException, IOException, MyServletException{
					
				request.addParameter("nome", "Sandro");
				request.addParameter("cognome", "Mutone");
				request.addParameter("provincia", "NA");
				request.addParameter("CAP", "80033");
				request.addParameter("citta", "Nola");
				request.addParameter("via", "Via albano carrera");
				request.addParameter("numero", "45");
				request.addParameter("email", "s.mutone@studenti.unisa.it");
				request.addParameter("username", "paolo22");
				request.addParameter("password", "cicalesse");
				
				 final String message = "Formato Password Errato.";
					MyServletException exceptionThrown = assertThrows(MyServletException.class, () -> {
						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());	
				}


}
