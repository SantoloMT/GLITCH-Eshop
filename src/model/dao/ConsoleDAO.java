package model.dao;


import java.util.ArrayList;
import java.util.List;

import model.bean.Console;


public interface ConsoleDAO{

	//Memorizza una console nel database
	void createConsole(Console prodotto);
	
	//Rimuove una console avente come id "codice" dal database
	void removeConsole(int id);
	
	//Restituisce una lista limitata di console comprese tra i valori "min" e "max" passati come argomento
	List<Console> doRetriveConsoleAllRange(int min, int max);
	
	//Restituisce la console che ha come id l'intero passato come argomento
	Console findConsoleById(int id);
	
	//Restituisce la console avente come modello la stringa passata come argomento
	Console retriveByModello(String modello);
	
	//Restituisce la lista di console aventi come casa produttrice la stringa passata come argomento
	List<Console> retriveByCasaProduttirce(String casaProduttrice);
	
	//Restituisce tutte le console presenti in db
	List<Console> findAllConsole();
	
	//Restituisce una lista di console avente modello simile a quello passato per argomento
	ArrayList<String> doRetrieveLikeModello(String modello);
	
	
	
}
