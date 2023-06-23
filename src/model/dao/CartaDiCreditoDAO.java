package model.dao;

import java.util.List;



import model.bean.CartaDiCredito;


public interface CartaDiCreditoDAO{
	
	//Rende persistente la carta di credito "cdc"
	void createCartaDiCredito(CartaDiCredito cdc);
	
	//Rimuove la carta di credito avente "numCarta" come numero identificativo dal DB
	void removeCartaDiCredito(String numCarta);
	
	//Restituisce la carta di credito avente "numCarta" come numero identificativo della carta
	CartaDiCredito retriveByNumCarta(String numCarta);
	
	//Restituisce la lista di carte di credito associate all'utente avente come username "user" 
	public List<CartaDiCredito> retriveByUtente(String user);

}
