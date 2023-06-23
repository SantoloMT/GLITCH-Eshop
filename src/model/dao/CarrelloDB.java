package model.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;


import model.bean.Carrello;
import model.bean.ConPool;
import model.bean.Console;
import model.bean.ProdottoQuantita;
import model.bean.Utente;
import model.bean.Videogioco;


public class CarrelloDB implements CarrelloDAO{


	private ConsoleDB cDAO = new ConsoleDB(); 
	private VideogiocoDB vidDAO = new VideogiocoDB();


	//Restituisce il carrello dell'utente avente come username la stringa passata come argomento
	public Carrello retriveByUtente(Utente user) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT prodottoID, utenteUsername, utenteEmail, quantita FROM Carrello WHERE utenteUsername = ? ");
			ps.setString(1, user.getUsername());
			Carrello carrello = new Carrello();
			ResultSet rs = ps.executeQuery();
			Videogioco vid = new Videogioco();
			Console cons = new Console();
			if(rs != null) {
				carrello.setUsername(user.getUsername());
				carrello.setUtenteEmail(user.getEmail());
				//Se il prodotto ha un id corrispondete ad una console verrà creato un oggetto Console, viceversa un oggetto Videogioco e verrà aggiunto in carrello
				while (rs.next()) {

					if( (cons=  cDAO.findConsoleById(rs.getInt(1))) != null ) {

						carrello.put(cons, rs.getInt(4));

					}else {
						vid = vidDAO.findVideogiocoById(rs.getInt(1));

						carrello.put(vid, rs.getInt(4));

					}

				}
				return carrello;
			}else {
				return carrello;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	//Memorizza il carrello passato come argomento nel database
	//Se il carrello già esiste lo aggiornerà altrimenti lo creerà e lo memorizzerà nel database
	public void createCarrello(Carrello cart) {
		try (Connection con = ConPool.getConnection()) {			
			Collection<ProdottoQuantita> pq = cart.getProdotti();
			Iterator<ProdottoQuantita> prod;

			prod = pq.iterator();
			ProdottoQuantita p;
			while(prod.hasNext()) {

				p = prod.next();
				PreparedStatement v = con
						.prepareStatement("SELECT prodottoID, utenteUsername, utenteEmail, quantita FROM Carrello where prodottoID = ? AND utenteUsername = ? AND utenteEmail = ?");
				v.setInt(1, p.getProdotto().getId());
				v.setString(2, cart.getUsername());
				v.setString(3, cart.getUtenteEmail());
				ResultSet rs = v.executeQuery();

				
				if(!rs.next()) {
					PreparedStatement ps = con.prepareStatement("INSERT INTO Carrello (prodottoID, utenteUsername, utenteEmail, quantita) VALUES(?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);

					ps.setInt(1,p.getProdotto().getId());
					ps.setString(2, cart.getUsername());
					ps.setString(3, cart.getUtenteEmail());
					ps.setInt(4, p.getQuantita());

					if (ps.executeUpdate() != 1) {

						throw new RuntimeException("INSERT error.");
					}

				}else {
					
					doUpdate(p);
				}


			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Aggiorna la quantità dell'oggetto carrello nel database
	public void doUpdate(ProdottoQuantita p) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE Carrello SET quantita=? WHERE prodottoID=? AND utenteUsername = ?");
			ps.setInt(1,p.getQuantita());
			ps.setInt(2, p.getProdotto().getId());
			ps.setString(3, p.getUtenteUsername());

			if (ps.executeUpdate() != 1 ) {
				throw new RuntimeException("UPDATE error.");

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Aggiorna la quantità dell'oggetto carrello nel database
	public void doUpdateEmail(Utente u, Carrello c) {
		try (Connection con = ConPool.getConnection()) {
			Collection<ProdottoQuantita> pq = c.getProdotti();

			Iterator<ProdottoQuantita> prod;

			prod = pq.iterator();
			ProdottoQuantita p;

			while(prod.hasNext()) {
				p = prod.next();
				PreparedStatement ps = con
						.prepareStatement("UPDATE Carrello SET utenteEmail=? WHERE prodottoID=? AND utenteUsername = ?");
				ps.setString(1,u.getEmail());
				ps.setInt(2, p.getProdotto().getId());
				ps.setString(3, u.getUsername());

				if (ps.executeUpdate() != 1 ) {
					throw new RuntimeException("UPDATE error.");

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	//Rimuove l'oggetto carrello avente come id l'intero passato come argomento e come username dell'utente la stringa "utenteUsername"
	public void doDelete(int prodId, String utenteUsername) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Carrello WHERE utenteUsername=? AND prodottoID=?");
			ps.setString(1, utenteUsername);
			ps.setInt(2, prodId);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Rimuove tutti gli oggetti carrello associati all'utente avente come username la stringa passata per argomento
	public void removeCarrello(String utenteUsername) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Carrello WHERE utenteUsername=?");
			ps.setString(1, utenteUsername);


			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
