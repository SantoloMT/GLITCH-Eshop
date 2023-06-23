package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.bean.ConPool;
import model.bean.Ordine;
import model.bean.ProdottiOrdine;



public class OrdineDB implements OrdineDAO{


	//Memorizza l'oggetto Ordine "ord" nel database
	public void createOrdine(Ordine ord)
	{
		try (Connection con = ConPool.getConnection()) {			
			ArrayList<ProdottiOrdine> pq = ord.getProdottiAcquistati();

			Iterator<ProdottiOrdine> prod;

			prod = pq.iterator();
			ProdottiOrdine p;

			//Scandisce i "ProdottiOrdine" (prodotti acquistati) contenuti nell'oggetto Ordine "ord" e li memorizza singolarmente nel database con lo stesso ordineId dell'oggetto Ordine "ord"
			while(prod.hasNext()) {

				p= prod.next();
				PreparedStatement ps = con.prepareStatement("INSERT INTO Ordine (ordineId, utenteUsername, utenteEmail, nome, quantita, prezzo, dataOrdinazione) VALUES(?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

				ps.setInt(1,ord.getOrdineId());
				ps.setString(2, p.getUtenteUsername());
				ps.setString(3, p.getUtenteEmail());
				ps.setString(4, p.getNome());
				ps.setInt(5, p.getQuantita());
				ps.setFloat(6, p.getPrezzo());
				ps.setDate(7, ord.getDataOrdinazioneDate());

				if (ps.executeUpdate() != 1) {

					throw new RuntimeException("INSERT error.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


	// Restituisce lo storico ordini di un utente avente come username la stringa passata per argomento
	public List<Ordine> retriveByUtente(String user)
	{
		try (Connection con = ConPool.getConnection()) {

			//Elenca le result query per "ordineId" in modo da distinguere i singoli ordini (ogni oggetto ordine ha un solo ordineId)
			PreparedStatement ps = con.prepareStatement(
					"SELECT ordineId, utenteUsername, utenteEmail, nome, quantita, prezzo, dataOrdinazione FROM Ordine WHERE utenteUsername= ? GROUP BY ordineId");

			ps.setString(1, user);

			ArrayList<Ordine> ordini=new ArrayList<Ordine>();
			ArrayList<ProdottiOrdine> prod = new ArrayList<ProdottiOrdine>();
			ResultSet rs = ps.executeQuery();
			Ordine ord=new Ordine();
			ProdottiOrdine p = new ProdottiOrdine();
			p = new ProdottiOrdine();	
			int counter = 0;
			while(rs.next()){
				//Iteriamo in base al numero di ProdottiAcquistati nel database
				//Se la result query (e quindi il prodotto acquistato) conterrà un ordineId diverso dal precedente, si tratterà di un nuovo ordine
				//salverà quindi i prodottiAcquistati (prelevati fino a quel punto e avente lo stesso ordineId) nell'oggetto Ordine "ord" in uso, aggiungerà "ord" all'array di Ordine "ordini" e 
				//creerà un nuovo oggetto Ordine "ord" contenente i ProdottiAcquistati di un nuovo ordine. 
				//Alla fine in "ordini" avremo lo storico di ordini dell'utente ordinata per "ordineId"

				

				
				ord.setUtenteUsername(rs.getString(2));
				p.setUtenteUsername(rs.getString(2));
				p.setUtenteEmail(rs.getString(3));
				p.setNome(rs.getString(4));
				p.setQuantita(rs.getInt(5));
				p.setPrezzo(rs.getFloat(6));
				ord.setDataOrdinazioneDate(rs.getDate(7));
				prod.add(p);
				if(ord.getOrdineId()!=rs.getInt(1))
				{
					ord.setOrdineId(rs.getInt(1));
					ord.setProdottiAcquistati(prod);
					ordini.add(ord);
					ord = new Ordine();
				}
				ord.setOrdineId(rs.getInt(1));
				counter = counter +1;
			}

			return ordini;
		
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

//Restituisce l'oggetto Ordine dal database avente come ordineId l'intero passato per argomento 
public Ordine retriveById(int id)
{
	try (Connection con = ConPool.getConnection()) {

		PreparedStatement ps = con.prepareStatement(
				"SELECT ordineId, utenteUsername, utenteEmail, nome, quantita, prezzo, dataOrdinazione FROM Ordine WHERE ordineId = ? ");

		ps.setInt(1, id);


		ArrayList<ProdottiOrdine> prod = new ArrayList<ProdottiOrdine>();
		ResultSet rs = ps.executeQuery();

		Ordine ord=new Ordine();

		//Crea i singoli "ProdottiOrdine" e li inserisce nell'arrayList "prod"
		//A fine ciclo l'ArrayList "prod" conterrà tutti i prodotti acquistati di un ordine
		while(rs.next()) {

			ProdottiOrdine p = new ProdottiOrdine();

			ord.setOrdineId(rs.getInt(1));
			ord.setUtenteUsername(rs.getString(2));
			p.setUtenteUsername(rs.getString(2));
			p.setUtenteEmail(rs.getString(3));
			p.setNome(rs.getString(4));
			p.setQuantita(rs.getInt(5));
			p.setPrezzo(rs.getFloat(6));
			ord.setDataOrdinazioneDate(rs.getDate(7));
			prod.add(p);
		}

		//Aggiungiamo l'array di prodotti acquistati all'ordine
		ord.setProdottiAcquistati(prod);

		return ord;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

}
