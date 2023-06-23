package model.bean;

import java.util.regex.Pattern;

public class ValidazioneConsole extends ValidazioneProdotto{

	//Controlla che sia rispettata la sintassi (Deve avere caratteri alfanumerici) di "modello"
	public static boolean checkModello(String modello)
	{
		return Pattern.matches("[A-Za-z0-9\\s]+",modello);
	}
	
	//Controlla che sia rispettata la sintassi (Deve avere caratteri alfabetici) di "casaProduttrice"
	public static boolean checkCasaProduttrice(String casaProduttrice)
	{
		return Pattern.matches("[A-Za-z\\s]+",casaProduttrice);
	}
}
