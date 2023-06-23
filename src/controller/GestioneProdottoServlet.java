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
import model.bean.Prodotto;
import model.bean.ValidazioneConsole;
import model.bean.ValidazioneVideogioco;
import model.bean.Videogioco;
import model.dao.ConsoleDAO;
import model.dao.ConsoleDB;
import model.dao.ProdottoDAO;
import model.dao.ProdottoDB;
import model.dao.VideogiocoDAO;
import model.dao.VideogiocoDB;


/**
 * GestioneProdottoServlet permette di gestire i prodotti al gestore catalogo. Inserisce o rimuove un prodotto da DB
 */
@WebServlet("/GestioneProdottoServlet")
public class GestioneProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideogiocoDAO vDAO = new VideogiocoDB();
	private ProdottoDAO pDAO = new ProdottoDB(); 
	private ConsoleDAO cDAO = new ConsoleDB();
	private List<Videogioco> v;
	private List<Console> c;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestioneProdottoServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Si prende il valore del parametro "operazione" e si verifica se equivale ad "inserimento"
		String caso = request.getParameter("operazione");
		String prod = request.getParameter("Videogioco");
		String prod1 = request.getParameter("Console");
		if(caso.equalsIgnoreCase("inserimento")) {

			//se l'equalsIgnoreCase() restituisce true si procede all'inserimento 
			//di un prodotto in DB

			
			String imm =  request.getParameter("immagine");
			String descrizione = request.getParameter("descrizione");
			String prezzo = request.getParameter("prezzo");



			//si verifica se e' una console o un videogioco ( se uno dei due parametri - "Videogioco"
			// o "Console" - e' nullo/vuoto, verra' fatto l'inserimento dell'altro
			if(prod1 == null && prod != null) {

				// in questo caso e' un videogioco e si prendono i dati dalla request
				String nome =  request.getParameter("nome");
				String genere =  request.getParameter("genere");
				String piattaforma = request.getParameter("piattaforma");
				
				//si controlla la correttezza del formato dei dati
				
				if(!ValidazioneVideogioco.checkImmagine(imm) || imm == null) {

					throw new MyServletException("Formato Url Immagine Videogioco non corretto.");
				}
				if(!ValidazioneVideogioco.checkDescrizione(descrizione) || descrizione == null) {

					throw new MyServletException("Descrizione Videogioco non corretto.");
				}
				if(!ValidazioneVideogioco.checkPrezzo(prezzo) || prezzo == null) {

					throw new MyServletException("Formato Prezzo Videogioco non corretto.");
				}
				if(!ValidazioneVideogioco.checkNome(nome) || nome == null) {

					throw new MyServletException("Formato Nome Videogioco non corretto.");
				}
				if(!ValidazioneVideogioco.checkGenere(genere) || genere == null) {

					throw new MyServletException("Formato Genere Videogioco non corretto.");
				}
				if(!ValidazioneVideogioco.checkPiattaforma(piattaforma) || piattaforma == null) {

					throw new MyServletException("Formato Piattaforma Videogioco non corretto.");
				}

				//verifico che non sia già presente in DB
				if(vDAO.retriveByNomeAndPiattaforma(nome, piattaforma) != null) {
					throw new MyServletException("Videogioco già presente nel catalogo.");
				
				}

				//si crea un bean Videogioco e si inseriscono i dati

				Videogioco vid = new Videogioco(imm,Float.parseFloat(prezzo),descrizione,nome,genere,piattaforma);

				//aggiorno catalogo videogiochi
				vDAO.createVideogioco(vid); //si inserisce in DB
				v = vDAO.findAllVideogioco();
				request.setAttribute("Videogiochi", v);
			}else {

				//in questo caso e' una Console e si prendono i dati dalla request

				String modello = request.getParameter("modello");
				String casaProduttrice = request.getParameter("casaProduttrice");
				
				//si controlla la correttezza del formato dei dati
				
				if(!ValidazioneConsole.checkImmagine(imm) || imm == null) {

					throw new MyServletException("Formato Url Immagine Console non corretto.");
				}
				if(!ValidazioneConsole.checkDescrizione(descrizione) || descrizione == null) {

					throw new MyServletException("Descrizione Console non corretto.");
				}
				if(!ValidazioneConsole.checkPrezzo(prezzo) || prezzo == null) {

					throw new MyServletException("Formato Prezzo Console non corretto.");
				}
				if(!ValidazioneConsole.checkModello(modello) || modello == null) {

					throw new MyServletException("Formato Modello Console non corretto.");
				}
				if(!ValidazioneConsole.checkCasaProduttrice(casaProduttrice) || casaProduttrice == null) {

					throw new MyServletException("Formato Casa Produttrice Console non corretto.");
				}
				
				//verifico che non sia già presente in DB
				if(cDAO.retriveByModello(modello) != null) {
					throw new MyServletException("Console già presente nel catalogo.");
				
				}
				
				//si crea un bean Console e si inseriscono i dati
				Console cons = new Console(imm,Float.parseFloat(prezzo),descrizione,modello, casaProduttrice);

				//aggiorno catalogo console
				cDAO.createConsole(cons); //si inserisce in DB 
				c = cDAO.findAllConsole();
				request.setAttribute("Consoles", c);
			}


		}else if(caso.equalsIgnoreCase("rimozione")){
			//in questo caso il parametro "operazione" contiene "rimozione"
			//e quindi si procede alla rimozione del prodotto

			//si prende dalla request l'id del prootto da eliminare
			int code = Integer.parseInt( request.getParameter("prodId") );
			
			
			//si prende da DB il prodotto con quell'id
			Prodotto p = pDAO.findProdottoById(code);
			
			
			//si rimuove da DB il prodotto
			pDAO.removeProdotto(p.getId());	
			//aggiorno lista console e videogiochi del catalogo
			v = vDAO.findAllVideogioco();
			request.setAttribute("Videogiochi", v);
			
			c = cDAO.findAllConsole();
			request.setAttribute("Consoles", c);
		}
		//Si esegue la forward alla pagina GestioneProdotti del sito
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/GestioneProdotti.jsp");
		requestDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
