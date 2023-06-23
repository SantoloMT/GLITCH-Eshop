package model.dao;


import java.util.ArrayList;
import java.util.List;
import model.bean.Videogioco;


public interface VideogiocoDAO{
	
	void createVideogioco(Videogioco prodotto);
	
	void removeProdotto(int id);
	
	//Restituisce una lista di tutti i videogiochi presenti nel database
	List<Videogioco> findAllVideogioco();
	
	//Restituisce una lista limitata di videogiochi compresi tra i valori "min" e "max" passati come parametri
	List<Videogioco> doRetriveVideogiocoAllRange(int min, int max);
	
	 //restituisce il videogioco che ha come id l'intero passato come parametro
	Videogioco findVideogiocoById(int id);
	
	//Restitusce una lista di videogiochi avente come nome la stringa passata come argomento
	List<Videogioco> retriveByNome(String nome);
	
	//Restitusce una lista di videogiochi avente come genere la stringa passata come argomento
	List<Videogioco> retriveByGenere(String genere);
	
	//Restitusce una lista di videogiochi avente come piattaforma la stringa passata come argomento
	List<Videogioco> retriveByPiattaforma(String piattaforma);
	//Restituisce i videogiochi che rispettano le caratteristiche della stringa passata per parametro
	public ArrayList<String> doRetrieveLike(String against);
	
	//Restituisce i videogiochi che rispettano le caratteristiche della stringa passata per parametro
	public Videogioco retriveByNomeAndPiattaforma(String nome, String piatt);
	
}
