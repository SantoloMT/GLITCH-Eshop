package controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Console;
import model.bean.Offerta;
import model.bean.ValidazioneOfferta;
import model.bean.Videogioco;
import model.dao.ConsoleDAO;
import model.dao.ConsoleDB;
import model.dao.OffertaDAO;
import model.dao.OffertaDB;
import model.dao.ProdottoDAO;
import model.dao.ProdottoDB;
import model.dao.VideogiocoDAO;
import model.dao.VideogiocoDB;



/**
 * Servlet implementation class GestioneOffertaServlet
 */
@WebServlet("/GestioneOffertaServlet")
public class GestioneOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OffertaDAO oDAO = new OffertaDB();
	private ConsoleDAO cDAO = new ConsoleDB();
	private VideogiocoDAO vDAO = new VideogiocoDB();
	private ProdottoDAO pDAO=new ProdottoDB();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestioneOffertaServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Si prende il valore del parametro "operazione" e si verifica se equivale ad "inserimento"
		String caso = request.getParameter("operazione");
		if(caso.equalsIgnoreCase("inserimento")) {

			//se l'equalsIgnoreCase() restituisce true si rende persistente l'offerta in DB

			String nome = request.getParameter("nome");
			String sconto = request.getParameter("sconto");
			String categoria =  request.getParameter("categoria");
			if(!ValidazioneOfferta.checkNome(nome)|| nome == null) {
				throw new MyServletException("Nome offerta non corretta.");
			}			
			if(!ValidazioneOfferta.checkPercentualeSconto(sconto) || sconto == null) {
				throw new MyServletException("Percentuale sconto non corretta.");
			}
			if(!ValidazioneOfferta.checkCategoria(categoria) || categoria == null) {
				throw new MyServletException("Categoria offerta non corretta.");
			}
			
			//si crea il bean Offerta e si settano i dati
			Offerta offerta = new Offerta(Integer.parseInt(sconto),categoria,nome);


			//si salva in DB
			oDAO.createOfferta(offerta);
			
			//aggiorno la lista
			List<Offerta> list = oDAO.findAllOfferta();
			request.setAttribute("offerta", list);
			//ora aggiungiamo l'offerta alla categoria a cui si riferisce
			int percSconto;
			
			if( (offerta.getCategoria()).equalsIgnoreCase("Console")) {

				//se si riferisce ad una console, le prendo tutte da DB ed applico loro lo
				//sconto sul prezzo
				List<Console> console = cDAO.findAllConsole();
				
				for( Console c: console) {

					percSconto = offerta.getSconto();
					float prezzo = c.getPrezzo();

					float elimSconto = (prezzo * percSconto)/100;
					prezzo = prezzo - elimSconto;
					c.setPrezzo(prezzo);
					pDAO.doUpdatePrezzo(c);
					

				}

			}else {
				//se si riferisce ad un videogioco, li prendo tutti da DB ed applico loro lo
				//sconto sul prezzo
				List<Videogioco> videogioco = vDAO.findAllVideogioco();


				for( Videogioco v : videogioco) {

					percSconto = offerta.getSconto();
					float prezzo = v.getPrezzo();

					float elimSconto = (prezzo * percSconto)/100;
					prezzo = prezzo - elimSconto;
					v.setPrezzo(prezzo);
					pDAO.doUpdatePrezzo(v);
					

				}
			}
		}else {
			//in questo caso il parametro "operazione" contiene "rimozione"
			//e quindi si procede alla rimozione dell'offerta

			//si prende dalla request l'id dell'offerta da eliminare
			int code = Integer.parseInt( request.getParameter("offertaId") );

			//si prende da DB l'offerta con quell'id
			Offerta o = oDAO.retriveOffertaById(code);
			
			
			//si rimuove da DB il prodotto
			oDAO.deleteOfferta(code);	
			//Aggiorno la lista
			
			List<Offerta> list = oDAO.findAllOfferta();
			request.setAttribute("offerta", list);

			//ora eliminiamo l'offerta alla categoria a cui si riferiva
			if( (o.getCategoria()).equalsIgnoreCase("Console")) {

				//se si riferisce ad una console, le prendo tutte da DB ed elimino loro lo
				//sconto 
				List<Console> console = cDAO.findAllConsole();

				for( Console c: console) {

					int sconto = o.getSconto();
					float prezzo = c.getPrezzo();

					float elimSconto = (prezzo * sconto)/100;
					prezzo = prezzo + elimSconto;
					c.setPrezzo(prezzo);
					pDAO.doUpdatePrezzo(c);

				}

			}else {
				//se si riferisce ad un videogioco, li prendo tutti da DB ed elimino loro lo
				//sconto 
				List<Videogioco> videogioco = vDAO.findAllVideogioco();


				for( Videogioco v : videogioco) {

					int sconto = o.getSconto();
					float prezzo = v.getPrezzo();

					float elimSconto = (prezzo * sconto)/100;
					prezzo = prezzo + elimSconto;
					v.setPrezzo(prezzo);
					pDAO.doUpdatePrezzo(v);

				}
			}
		}
		//Si esegue la forward alla pagina GestioneOfferte del sito
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/GestioniOfferte.jsp");
		requestDispatcher.forward(request, response);
	}






	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
