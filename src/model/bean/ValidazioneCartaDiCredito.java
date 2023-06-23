package model.bean;

import java.util.regex.Pattern;

public class ValidazioneCartaDiCredito {

	//Controlla che sia rispettata la sintassi (caratteri alfabetici e prima lettera maiuscola) di "nome"
	public static boolean checkNome(String nome){
		return Pattern.matches("[A-Za-z\\s]+",nome) && Pattern.matches("[A-Z]+",nome.subSequence(0, 1));
	}
	
	//Controlla che sia rispettata la sintassi (caratteri alfabetici e prima lettera maiuscola) di "cognome"
	public static boolean checkCognome(String cognome)
	{
		return Pattern.matches("[A-Za-z\\s]+",cognome) && Pattern.matches("[A-Z]+",cognome.subSequence(0, 1));
	}
	
	//Controlla che sia rispettata la sintassi (Deve essere formato da 16 cifre numeriche) di "nI" (Numero identificativo)
	public static boolean checkNumeroIdentificativo(String nI)
	{
		if(nI.length()!=16)
			return false;
		else
			return Pattern.matches("[0-9]+",nI);
	}
	
	//Controlla che sia rispettata la sintassi (Deve contenere 2 caratteri numerici nel formato â€œMMâ€�) di "meseScadenza" (Scadenza)
	public static boolean checkMeseScadenza(String meseScadenza)
	{
		if(meseScadenza.length()!=2)
			return false;
		
		int mese=Integer.parseInt(meseScadenza);
		
		if(mese>0 && mese<=12)
		{
			return true;
		}
		else
			return false;	
	}
	
	//Controlla che sia rispettata la sintassi (Deve contenere 2 caratteri numerici nel formato â€œAAâ€�) di "annoScadenza" (Scadenza)
	public static boolean checkAnnoScadenza(String annoScadenza)
	{
		if(annoScadenza.length()!=2)
			return false;
		
		int anno=Integer.parseInt(annoScadenza);
		if(anno>=20)
		{
			return true;
		}
		else
			return false;	
	}
	
	//Controlla che sia rispettata la sintassi (Deve essere formato da 3 caratteri numerici) di "cVV"
	public static boolean checkCVV(String cVV)
	{
		if(cVV.length()!=3)
			return false;
		else
			return Pattern.matches("[0-9]+",cVV);
		
	}
	
	
	
	
	
	
	

}
