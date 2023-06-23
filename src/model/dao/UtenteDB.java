package model.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Utente;
import model.bean.ConPool;

public class UtenteDB implements UtenteDAO{



	//Memorizza l'utente passato come argomento nel database
	public void createUtente(Utente u) 
	{
		try (Connection con = ConPool.getConnection()) {

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Utente (username, email, cartaDiCredito, password, nome, cognome, provincia, cap, citta, via, numero, ruolo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, null);
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getNome());
			ps.setString(6, u.getCognome());
			ps.setString(7, u.getProvincia());
			ps.setInt(8, u.getCap());
			ps.setString(9, u.getCitta());
			ps.setString(10, u.getVia());
			ps.setInt(11, u.getNumero());
			ps.setString(12,  u.getRuolo());

			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	//Rimuove l'utente, avente come username la stringa passata come argomento, dal database
	public void deleteUtente(String user) {
		try(Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Utente WHERE username = ? ");
			ps.setString(1, user);
			if(ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Aggiorna le informazioni anagrafiche dell'utente (Email, indirizzo) nel database
	public void updateUtente(Utente u) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE Utente SET email = ? , password = ? , provincia = ? , cap = ? , citta = ? , via= ? , numero = ? WHERE username = ?");

			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getProvincia());
			ps.setInt(4, u.getCap());
			ps.setString(5, u.getCitta());
			ps.setString(6, u.getVia());
			ps.setInt(7, u.getNumero());
			ps.setString(8, u.getUsername());

			if (ps.executeUpdate() != 1 ) {
				throw new RuntimeException("UPDATE error.");

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateCartaDiCreditoUtente(Utente u) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE Utente SET cartaDiCredito = ? WHERE username = ?");

			ps.setString(1, u.getCartaDiCredito().getNumeroCarta());
			ps.setString(2, u.getUsername());
			if (ps.executeUpdate() != 1 ) {
				throw new RuntimeException("UPDATE Carta Di Credito di utente error.");

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Aggiorna i ruoli dell'utente nel database
	public void updateRuoloUtente(Utente u) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE Utente SET ruolo = ? WHERE username = ?");

			ps.setString(1, u.getRuolo());
			ps.setString(2, u.getUsername());
			if (ps.executeUpdate() != 1 ) {
				throw new RuntimeException("UPDATE Ruolo error.");

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Restituisce l'utente avente come username la stringa passata come argomento
	public Utente retriveByUsername(String user) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT username, email, cartaDiCredito, password, nome, cognome, provincia, cap, citta, via, numero, ruolo FROM Utente WHERE username=?");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			CartaDiCreditoDAO cartaDAO=new CartaDiCreditoDB();

			if (rs.next()) {
				Utente p = new Utente();
				p.setUsername(rs.getString(1));
				p.setEmail(rs.getString(2));

				if(rs.getString(3) != null)
					p.setCartaDiCredito(cartaDAO.retriveByNumCarta(rs.getString(3)));
				else
					p.setCartaDiCredito(null);

				p.setPassword(rs.getString(4));
				p.setNome(rs.getString(5));
				p.setCognome(rs.getString(6));
				p.setProvincia(rs.getString(7));
				p.setCap(rs.getInt(8));
				p.setCitta(rs.getString(9));
				p.setVia(rs.getString(10));
				p.setNumero(rs.getInt(11));

				if(rs.getString(12) != null)
					p.setRuolo( rs.getString(12));
				else
					p.setRuolo( null);

				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Restituisce l'utente avete come email la stringa passata come argomento
	public Utente retriveByEmail(String email) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT username, email, cartaDiCredito, password, nome, cognome, provincia, cap, citta, via, numero, ruolo FROM Utente WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			CartaDiCreditoDAO cartaDAO=new CartaDiCreditoDB();

			if (rs.next()) {
				Utente p = new Utente();
				p.setUsername(rs.getString(1));
				p.setEmail(rs.getString(2));

				if(rs.getString(3) != null)
					p.setCartaDiCredito(cartaDAO.retriveByNumCarta(rs.getString(3)));
				else
					p.setCartaDiCredito(null);

				p.setPassword(rs.getString(4));
				p.setNome(rs.getString(5));
				p.setCognome(rs.getString(6));
				p.setProvincia(rs.getString(7));
				p.setCap(rs.getInt(8));
				p.setCitta(rs.getString(9));
				p.setVia(rs.getString(10));
				p.setNumero(rs.getInt(11));
				p.setRuolo( rs.getString(12));
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}




	//Restituisce la lista degli utenti presenti nel database
	public List<Utente> retriveAllUtenti() {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT username, email, cartaDiCredito, password, nome, cognome, provincia, cap, citta, via, numero, ruolo FROM Utente ");

			ArrayList<Utente> utenti = new ArrayList<Utente>();
			ResultSet rs = ps.executeQuery();
			CartaDiCreditoDAO cartaDAO=new CartaDiCreditoDB();

			while (rs.next()) {
				Utente p = new Utente();
				p.setUsername(rs.getString(1));
				p.setEmail(rs.getString(2));

				if(rs.getString(3) != null)
					p.setCartaDiCredito(cartaDAO.retriveByNumCarta(rs.getString(3)));
				else
					p.setCartaDiCredito(null);

				p.setPassword(rs.getString(4));
				p.setNome(rs.getString(5));
				p.setCognome(rs.getString(6));
				p.setProvincia(rs.getString(7));
				p.setCap(rs.getInt(8));
				p.setCitta(rs.getString(9));
				p.setVia(rs.getString(10));
				p.setNumero(rs.getInt(11));
				p.setRuolo(rs.getString(12));
				utenti.add(p);
			}
			return utenti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> retriveEmailByAssistenza(String ruolo) {

		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT email FROM Utente WHERE ruolo=?");
			ps.setString(1, ruolo);
			ResultSet rs = ps.executeQuery();
			ArrayList<String> array = new ArrayList<String>();

			while (rs.next()) {
				array.add(rs.getString(1));

			}
			if(array.isEmpty()) {
				return null;
			}else {
				return array;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}










}
