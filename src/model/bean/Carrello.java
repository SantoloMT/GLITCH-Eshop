package model.bean;




import java.util.Collection;
import java.util.LinkedHashMap;


public class Carrello{

	
	private String utenteUsername;
	private String utenteEmail;
	private LinkedHashMap<Integer, ProdottoQuantita> prodotti = new LinkedHashMap<>();

	
	
	//Costruttore vuoto	
	public Carrello() {}
		
	//Costruisce un carrello passando l'username e l'email dell'utente
	public Carrello(String utenteUsername,String utenteEmail)
	{
		this.utenteUsername=utenteUsername;
		this.utenteEmail=utenteEmail;
			
	}
	
	
	//Restituisce la Collection di prodotti nel carrello
	public Collection<ProdottoQuantita> getProdotti() {
		return prodotti.values();
	}

	//Restituisce un "prodottoQuantita" avente come id del prodotto "prodId" 
	public ProdottoQuantita get(int prodId) {
		return prodotti.get(prodId);
	}
	
	//Aggiorna la quantità del prodotto nel carrello
	public void updateQuantita(int prodId , int quantita) {
		ProdottoQuantita pr = prodotti.get(prodId);
		pr.setQuantita(quantita);
		prodotti.replace(prodId, pr);
		
	}

	//Inserisce un prodotto e la sua quantità nel carrello
	public void put(Prodotto prodotto, int quantita) {
		prodotti.put(prodotto.getId(), new ProdottoQuantita(prodotto, quantita,utenteUsername));
	}

	//Rimuove un prodotto avente "prodId" come id dal carrello
	public ProdottoQuantita remove(int prodId) {
		return prodotti.remove(prodId);
	}

	//Restituisce il prezzo totale(derivato dalla somma del prezzo del singolo prodotto moltiplicato per la sua quantità) dei prodotti nella collection 
	public String getPrezzoTotProdotti() {
		return String.format("%.2f", prodotti.values().stream().mapToDouble(p -> p.getPrezzoTot()).sum());
	}
	
	
	
	//Restiuisce l'username dell'utente a cui è associato il carrello
	public String getUsername() {
		return utenteUsername;
	}
	

	//inserisce la username utente nel carrello
	public void setUsername(String utenteUsername) {
		this.utenteUsername = utenteUsername;
	}
	
	//inserisce l'email utente nel carrello
	public void setUtenteEmail(String utenteEmail) {
		this.utenteEmail = utenteEmail;
	}

	//Restiuisce l'email dell'utente a cui è associato il carrello
	public String getUtenteEmail() {
		return utenteEmail;
	}
	
	//Controlla se il corrello esiste
	public boolean isEmpty() {
		 return  prodotti != null && prodotti.isEmpty() ;
	}
	
	
	

	
}
