package model.bean;

import java.util.regex.Pattern;

public class ValidazioneUtente {

	//Controlla che sia rispettata la sintassi (deve iniziare con lettere maiuscola e contenere caratteri alfabetici) di "nome"
	public static boolean checkNome(String nome)
	{
		return Pattern.matches("[A-Za-z\\s]+",nome) && Pattern.matches("[A-Z]+",nome.subSequence(0, 1)) && nome.length()<=20;
	}
	
	//Controlla che sia rispettata la sintassi (deve iniziare con lettere maiuscola e contenere caratteri alfabetici) di "cognome"
	public static boolean checkCognome(String cognome)
	{
		return Pattern.matches("[A-Za-z\\s]+",cognome) && Pattern.matches("[A-Z]+",cognome.subSequence(0, 1)) && cognome.length()<=20;
	}
	
	//Controlla che sia rispettata la sintassi (Deve avere il formato standard per le e-mail) di "email"
	public static boolean checkEmail(String email)
	{
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(regex) && email.length()<=40;
	}
	
	//Controlla che sia rispettata la sintassi (deve contenere caratteri alfanumerici) di "provincia"
	public static boolean checkProvincia(String provincia)
	{
		return Pattern.matches("[A-Za-z0-9]+",provincia) && provincia.length()<=3;
	}
	
	//Controlla che sia rispettata la sintassi (Deve avere caratteri numerici) di "cap"
	public static boolean checkCap(String cap)
	{
		return Pattern.matches("[0-9]+",cap) && cap.length()<=8;
	}
	
	//Controlla che sia rispettata la sintassi (Deve contenere caratteri alfabetici) di "citta"
	public static boolean checkCitta(String citta)
	{
		return Pattern.matches("[A-Za-z]+",citta) && citta.length()<=20;
	}
	
	//Controlla che sia rispettata la sintassi (Deve contenere caratteri alfanumerici) di "via"
	public static boolean checkVia(String via)
	{
		return Pattern.matches("[A-Za-z0-9\\s]+",via) && via.length()<=30;
	}
	
	//Controlla che sia rispettata la sintassi (Deve contenere caratteri numerici) di "numero"
	public static boolean checkNumero(String numero)
	{
		return Pattern.matches("[0-9]+",numero) && numero.length()<=5;
	}
	
	//Controlla che sia rispettata la sintassi (Deve avere un massimo di 20 caratteri e contenere caratteri alfanumerici) di "username"
	public static boolean checkUsername(String username)
	{
		return Pattern.matches("[A-Za-z0-9]+",username) && username.length()<=20;
	}
	
	//Controlla che sia rispettata la sintassi (deve avere un minimo di 8 caratteri, un massimo di 15 caratteri e
	//contenere caratteri alfanumerici, tra i quali almeno una lettera maiuscola) di "password"
	public static boolean checkPassword(String password)
	{
		String pattern="((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15})";
		return Pattern.matches(pattern,password);
		
	}
	
	//Controlla che sia rispettata la sintassi (Deve contenere caratteri alfabetici e la prima lettera deve essere in maiuscolo) di "ruolo"
	public static boolean checkRuolo(String ruolo)
	{
		return Pattern.matches("[A-Za-z]+",ruolo) && Pattern.matches("[A-Z]+",ruolo.subSequence(0, 1));
		
	}
	
}
