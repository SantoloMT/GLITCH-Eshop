package model.bean;


public class Richiesta{

	private static int counter=10;
	private int id;
	private String utenteEmail;
	private String utenteUsername;
	private String destinatario;
	private String descrizione;
	private Boolean stato = false;
	
	//Costruttore vuoto
	public Richiesta() {
		
	}
	
	//Costruisce un oggetto "Richiesta" passando come argomento l'email dell'utente, l'username, il destinatario e la descrizione
	public Richiesta(String utenteEmail,String utenteUsername, String destinatario, String descrizione)
	{
		counter++;
		this.id=counter;
		this.setUtenteEmail(utenteEmail);
		this.setUtenteUsername(utenteUsername);
		this.setDestinatario(destinatario);
		this.setDescrizione(descrizione);
	}
	
	//Costruisce un oggetto "Richiesta" passando come argomento l'id, l'email dell'utente, l'username, il destinatario e la descrizione
		public Richiesta(int id,String utenteEmail,String utenteUsername, String destinatario, String descrizione)
		{
			this.id=id;
			this.setUtenteEmail(utenteEmail);
			this.setUtenteUsername(utenteUsername);
			this.setDestinatario(destinatario);
			this.setDescrizione(descrizione);
		}

	//Restituisce l'email dell'utente
	public String getUtenteEmail() {
		return utenteEmail;
	}

	//Modifica l'email dell'utente
	public void setUtenteEmail(String utenteEmail) {
		this.utenteEmail = utenteEmail;
	}

	//Restituisce l'username dell'utente
	public String getUtenteUsername() {
		return utenteUsername;
	}

	//Modifica l'username dell'utente
	public void setUtenteUsername(String utenteUsername) {
		this.utenteUsername = utenteUsername;
	}

	//Restituisce l'email del destinatario
	public String getDestinatario() {
		return destinatario;
	}

	//Modifica l'email del destinatario
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	//Restituisce la descrizione della richiesta
	public String getDescrizione() {
		return descrizione;
	}

	//Modifica la descrizione della richiesta
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	//Restituisce lo stato della richiesta 
	public Boolean getStato() {
		return stato;
	}

	//Modifica lo stato della richiesta
	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
