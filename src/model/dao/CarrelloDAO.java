package model.dao;

import model.bean.Carrello;
import model.bean.ProdottoQuantita;
import model.bean.Utente;

public interface CarrelloDAO {

	//Restituisce il carrello dell'utente avente come username la stringa passata come argomento
	Carrello retriveByUtente(Utente user);
	
	//Memorizza il carrello passato come argomento nel database
	//Se il carrello già esiste lo aggiornerà altrimenti lo creerà e lo memorizzerà nel database
	void createCarrello(Carrello cart);
	
	//Aggiorna la quantità dell'oggetto carrello nel database
	void doUpdate(ProdottoQuantita p);
	
	//Aggiorna l'email del carrello connesso all'utente
	void doUpdateEmail(Utente u,Carrello c);
	
	//Rimuove l'oggetto carrello avente come id l'intero passato come argomento e come username dell'utente la stringa "utenteUsername"
	void doDelete(int prodId, String utenteUsername);
	
	//Rimuove tutti gli oggetti carrello associati all'utente avente come username la stringa passata per argomento
	void removeCarrello(String utenteUsername);
	
}
