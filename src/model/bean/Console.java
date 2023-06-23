package model.bean;



public class Console extends Prodotto{


	private String modello;
	private String casaProduttrice;
	
	//Costruttore vuoto
	public Console() {}
	
	//Costruisce un oggetto "Console" passando come argomento il path dell'immagine,il prezzo, la descrizione, il modello e la casa produttrice	
	public Console(int id,String immagine, float prezzo, String descrizione, String modello, String casaProduttrice)
	{
		super(id, immagine,prezzo,descrizione);
		this.setModello(modello);
		this.setCasaProduttrice(casaProduttrice);
	}
	
	public Console(String immagine, float prezzo, String descrizione, String modello, String casaProduttrice)
	{
		super(immagine,prezzo,descrizione);
		this.setModello(modello);
		this.setCasaProduttrice(casaProduttrice);
	}

	//Restituisce il modello della console
	public String getModello() {
		return modello;
	}

	//Modifica il modello della console
	public void setModello(String modello) {
		this.modello = modello;
	}

	//Restituisce la casa produttrice della console
	public String getCasaProduttrice() {
		return casaProduttrice;
	}

	//modifica la casa produttrice della console
	public void setCasaProduttrice(String casaProduttrice) {
		this.casaProduttrice = casaProduttrice;
	}
	
	
	
	
	
}
