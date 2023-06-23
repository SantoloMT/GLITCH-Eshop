package model.dao;

import java.util.List;
import model.bean.Offerta;


public interface OffertaDAO {

	//Memorizza nel database l'offerta passata come argomento
	void createOfferta(Offerta offerta);
	
	//Rimuove l'offerta che ha come codice l'intero passato come argomento dal database
	void deleteOfferta(int codice);
	
	//Restituisce l'offerta avente come id l'intero passato come argomento
	Offerta retriveOffertaById(int codice);
	
	//Restituisce la lista di offerta associate alla determinata categoria
	List<Offerta> retriveByCategoria(String categoria);
	
	//Restituisce la lista di offerte presenti in DB
	List<Offerta> findAllOfferta();
		
}
