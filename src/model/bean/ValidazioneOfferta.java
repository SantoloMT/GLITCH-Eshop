package model.bean;

import java.util.regex.Pattern;

public class ValidazioneOfferta {

	
	//Controlla che sia rispettata la sintassi (Deve avere caratteri alfanumerici) di "nome"
	public static boolean checkNome(String Nome)
	{
		return Pattern.matches("[A-Za-z0-9\\s]+",Nome);
	}
	
	//Controlla che sia rispettata la sintassi (Deve avere caratteri numerici interi) di "sconto"
	public static boolean checkPercentualeSconto(String sconto)
	{
		return Pattern.matches("[0-9]+",sconto);
	}
	
	//Controlla che sia rispettata la sintassi (Deve avere caratteri alfabetici) di "categoria"
	public static boolean checkCategoria(String categoria)
	{
		return Pattern.matches("[A-Za-z]+",categoria);
	}
}
