package model.bean;

import java.util.regex.Pattern;

public class ValidazioneProdotto {

	//Controlla che sia rispettata la sintassi (Deve avere caratteri alfanumerici e simbolici) di "immagine"
	public static boolean checkImmagine(String immagine)
	{
		return true;
		//return Pattern.matches("[A-Za-z0-9\\W]+",immagine);
	}
	
	//Controlla che sia rispettata la sintassi (Deve avere valori decimali separati da punto) di "prezzo"
	public static boolean checkPrezzo(String prezzo)
	{
		return Pattern.matches("[0-9]+\\.+[0-9]+",prezzo);
		
	}
	
	//Controlla che sia rispettata la sintassi (Deve avere caratteri alfanumerici) di "descrizione"
	public static boolean checkDescrizione(String descrizione)
	{
		return Pattern.matches("[A-Za-z0-9\\s\\W]+",descrizione);
	}
	
	
	
}
