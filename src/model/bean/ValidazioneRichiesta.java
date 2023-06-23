package model.bean;

import java.util.regex.Pattern;

public class ValidazioneRichiesta {

	//Controlla che sia rispettata la sintassi (Deve avere caratteri alfanumerici) di "descrizione"
	public static boolean checkDescrizione(String descrizione)
	{
		return Pattern.matches("[A-Za-z0-9\\s\\W]+",descrizione);
	}
}
