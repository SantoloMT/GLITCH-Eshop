package model.bean;




public class Videogioco extends Prodotto{

	private String nome;
	private String genere;
	private String piattaforma;
	
	//Costruttore vuoto
	public Videogioco() {}
	
	//Costruisce un obj "Videogioco" passando come argomento il path dell'immagine, il prezzo, la descrizione, il nome, il genere e la piattaforma
	public Videogioco(int id,String immagine, float prezzo, String descrizione, String nome, String genere, String piattaforma)
	{
		super(id,immagine,prezzo,descrizione);
		this.setNome(nome);
		this.setGenere(genere);
		this.setPiattaforma(piattaforma);
	}
	
	public Videogioco(String immagine, float prezzo, String descrizione, String nome, String genere, String piattaforma)
	{
		super(immagine,prezzo,descrizione);
		this.setNome(nome);
		this.setGenere(genere);
		this.setPiattaforma(piattaforma);
	}

	//Restituisce la piattaforma del videogioco
	public String getPiattaforma() {
		return piattaforma;
	}

	//Modifica la piattaforma del videogioco
	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}

	//Restituisce il nome del videogioco
	public String getNome() {
		return nome;
	}

	//Modifica il nome del videogioco
	public void setNome(String nome) {
		this.nome = nome;
	}

	//Restituisce il genere del videogioco
	public String getGenere() {
		return genere;
	}

	//Modifica il nome del videogioco
	public void setGenere(String genere) {
		this.genere = genere;
	}
}
