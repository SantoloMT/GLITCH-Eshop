package model.bean;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import model.dao.ConsoleDAO;
import model.dao.ConsoleDB;
import model.dao.VideogiocoDAO;
import model.dao.VideogiocoDB;


public class Ordine{


	private String utenteUsername;
	private int ordineId;
	private static int counter=0;
	private ArrayList<ProdottiOrdine> prodottiAcquistati;
	private GregorianCalendar dataOrdinazione;
	private Date ordinazioneDate;
	private float prezzoTot = 0;
	
	//Costruttore vuoto
	public Ordine() {
		/*counter++;
		ordineId=counter;*/
	}
	
	//Costruisce l'oggetto "Ordine" passando come argomento l'id ordine, l'username e la lista di prodotti acquistati
	public Ordine(String utenteUsername, ArrayList<ProdottiOrdine> prodottiAcquistati, GregorianCalendar dataOrdinazione)
	{
		counter++;
		ordineId=counter;
		this.setUtenteUsername(utenteUsername);
		this.setProdottiAcquistati(prodottiAcquistati);
		this.setDataOrdinazione(dataOrdinazione);
		this.ordinazioneDate=new Date(dataOrdinazione.getTimeInMillis());
		
	}
	
	//Inizializza l'ordine assegnandogli un id che si andra ad incrementale ad ogni nuova instanza
	public Ordine(String utenteUsername, GregorianCalendar dataOrdinazione)
	{
		counter++;
		ordineId=counter;
		this.setUtenteUsername(utenteUsername);
		this.setDataOrdinazione(dataOrdinazione);
		this.ordinazioneDate=new Date(dataOrdinazione.getTimeInMillis());
		
	}

	//Costruisce l'oggetto ordine passando come argomento l'id ordine
	public Ordine(int ordineId)
	{
		this.ordineId=ordineId;
	}
	
	//Preleva i prodotti dal carrello (passato per argomento) e li aggiunge alla lista di prodotti acquistati
	public void setProdottiOrdine(Carrello cart)
	{
		Collection<ProdottoQuantita> prod=cart.getProdotti();
		Iterator<ProdottoQuantita> it=prod.iterator();
		prodottiAcquistati=new ArrayList<ProdottiOrdine>();
		VideogiocoDAO vidDao=new VideogiocoDB();
		ConsoleDAO consDao=new ConsoleDB();
		
		while(it.hasNext())
		{
			ProdottoQuantita pQ=it.next();
			Prodotto pr=pQ.getProdotto();
			ProdottiOrdine pOut=new ProdottiOrdine();
			
			
			if(vidDao.findVideogiocoById(pr.getId()) == null)
			{
				Console cons=consDao.findConsoleById(pr.getId());
				pOut.setNome(cons.getModello());
			}
			else
			{
				Videogioco vd=vidDao.findVideogiocoById(pr.getId());
				pOut.setNome(vd.getNome());
			}
			
			pOut.setPrezzo(pQ.getPrezzoTot());
			pOut.setQuantita(pQ.getQuantita());
			pOut.setUtenteEmail(cart.getUtenteEmail());
			pOut.setUtenteUsername(cart.getUsername());
			prodottiAcquistati.add(pOut);
		}
		setPrezzoTot(getPrezzoTotale());
	}
	
	//Restituisce la data di ordinazione sotto forma di oggetto "Date"
	public Date getDataOrdinazioneDate()
	{
		return ordinazioneDate;
	}
		
	//Modifica la data di ordinazione passando per argomento un oggetto "Date" contenente la nuova data		
	public void setDataOrdinazioneDate(Date ord)
	{
		ordinazioneDate=ord;
	}
	
	//Restituisce la data di ordinazione sotto forma di "GregorianCalendar"
	public GregorianCalendar getDataOrdinazione() {
		return dataOrdinazione;
	}

	//Modifica la data di ordinazione passando un oggetto "GregorianCalendar"
	public void setDataOrdinazione(GregorianCalendar dataOrdinazione) {
		this.dataOrdinazione = dataOrdinazione;
		this.ordinazioneDate=new Date(dataOrdinazione.getTimeInMillis());
	}
	
	
	//Restituisce la lista dei prodotti acquistati
	public ArrayList<ProdottiOrdine> getProdottiAcquistati() {
		return prodottiAcquistati;
	}

	//Modifica la lista dei prodotti acquistati
	public void setProdottiAcquistati(ArrayList<ProdottiOrdine> prodottiAcquistati) {
		this.prodottiAcquistati = prodottiAcquistati;
		setPrezzoTot(getPrezzoTotale());
	}

	//Restituisce l'id dell'ordine
	public int getOrdineId() {
		return ordineId;
	}

	//Modifica l'id dell'ordine
	public void setOrdineId(int ordineId) {
		this.ordineId = ordineId;
	}

	//Restituisce l'username associato all'ordine
	public String getUtenteUsername() {
		return utenteUsername;
	}

	//Modifica l'username associato all'ordine
	public void setUtenteUsername(String utenteUsername) {
		this.utenteUsername = utenteUsername;
	}
	
	//Restituisce il prezzo totale degli ordini acquistati
	private float getPrezzoTotale()
	{
		float prezzo=0;
		for(ProdottiOrdine p:prodottiAcquistati) {
			prezzo+=p.getPrezzo() * p.getQuantita();
		}
		return prezzo;
			
	}

	public void setPrezzoTot(float prezzoTot) {
		this.prezzoTot = prezzoTot;
	}
	
	public float getPrezzoTot()
	{
		return prezzoTot;
	}
	
	
	


}
