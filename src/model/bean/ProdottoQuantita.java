
package model.bean;


public class ProdottoQuantita {
	

	private String utenteUsername;
	private Prodotto prodotto;
	private int quantita;

	//Costruisce un oggetto ProdottoQuantita passando per argomento un prodotto, la quantità e l'username dell'utente associato
	public ProdottoQuantita(Prodotto prodotto, int quantita,String utenteUsername) {
		this.utenteUsername=utenteUsername;
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	//Restituisce la quantita del prodotto
	public int getQuantita() {
		return quantita;
	}

	//Modifica la quantita del prodotto
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	//Restituisce il prodotto
	public Prodotto getProdotto() {
		return prodotto;
	}

	//Restituisce il prezzo del prodotto moltiplicato per la quantità
	public float getPrezzoTot() {
		return quantita * prodotto.getPrezzo();
	}

	//Restituisce l'username dell'utente associato all'oggetto
	public String getUtenteUsername() {
		return utenteUsername;
	}

	//Modifica l'username dell'utente associato all'oggetto
	public void setUtenteUsername(String utenteUsername) {
		this.utenteUsername = utenteUsername;
	}

	

}
