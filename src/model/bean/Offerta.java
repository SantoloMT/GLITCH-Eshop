package model.bean;




public class Offerta{

	private static int counter=0;
	private int codice;
	private int sconto;
	private String categoria;
	private String nome;
	
   

	//Costruttore vuoto
	public Offerta() {}
	
	//Costruisce un oggetto "offerta" passando come argomento il suo codice, lo sconto e la categoria
	public Offerta(int codice,int sconto,String categoria, String nome)
	{
		this.codice=codice;
		this.sconto=sconto;
		this.categoria=categoria;
		this.nome=nome;
	}
	
	public Offerta(int sconto,String categoria, String nome)
	{
		counter++;
		this.codice=counter;
		this.sconto=sconto;
		this.categoria=categoria;
		this.nome=nome;
	}

	//Restituisce il codice identificativo dell'offerta
	public int getCodice() {
		return codice;
	}
	
	public void setCodice(int codice)
	{
		this.codice=codice;
	}

	//Restituisce il valore dello sconto
	public int getSconto() {
		return sconto;
	}

	//Modifica il valore dello sconto
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	//Restituisce la categoria a cui è applicato lo sconto
	public String getCategoria() {
		return categoria;
	}

	//Modifica la categoria a cui è applicato lo sconto
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	 //Restituisce il nome dell'offerta
		public String getNome() {
			return nome;
		}
		
		//Modifica il nome dell'offerta
		public void setNome(String nome) {
			this.nome = nome;
		}
	
	
}
