package model.dao;

import java.util.List;

import model.bean.Ordine;

public interface OrdineDAO {

	//Memorizza l'ordine passato per argomento nel database
	void createOrdine(Ordine ord);
	
	//Restituisce lo storico ordini di un utente avente come username la stringa passata per argomento
	List<Ordine> retriveByUtente(String user);
	
	//Restituisce l'oggetto Ordine dal database avente come ordineId l'intero passato per argomento
	Ordine retriveById(int id);
	
	
}
