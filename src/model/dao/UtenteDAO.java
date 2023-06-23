package model.dao;

import java.util.List;


import model.bean.Utente;


public interface UtenteDAO {

	//Memorizza l'utente passato come argomento nel database
	void createUtente(Utente u);
	
	//Rimuove l'utente, avente come username la stringa passata come argomento, dal database
	void deleteUtente(String user);
	
	//Aggiorna le informazioni anagrafiche dell'utente (Email, indirizzo) nel database
	void updateUtente(Utente u);
	
	//Aggiorna i ruoli dell'utente nel database
	void updateRuoloUtente(Utente u);
	
	//Restituisce l'utente avente come username la stringa passata come argomento
	Utente retriveByUsername(String user);
	
	//Restituisce l'utente avete come email la stringa passata come argomento
	Utente retriveByEmail(String email);
	
	//Restituisce la lista degli utenti presenti nel database
	List<Utente> retriveAllUtenti();
	
	//Aggiorna la carta di credito dell'utente
	void updateCartaDiCreditoUtente(Utente u);
	
	//Restituisce tutte le mail dei gestori Assistenza
	List<String> retriveEmailByAssistenza(String ruolo);
}
