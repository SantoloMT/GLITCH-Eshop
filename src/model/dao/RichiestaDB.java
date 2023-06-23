package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.ConPool;
import model.bean.Richiesta;

public class RichiestaDB implements RichiestaDAO{

	@Override
	public void createRichiesta(Richiesta rc) {

		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Richiesta (id, utenteEmail, utenteUsername, destinatario, descrizione, stato) VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, rc.getId());
			ps.setString(2, rc.getUtenteEmail());
			ps.setString(3,rc.getUtenteUsername());
			ps.setString(4, rc.getDestinatario());
			ps.setString(5, rc.getDescrizione());
			ps.setBoolean(6, rc.getStato());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateRichiesta(Richiesta rc) {

		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE Richiesta SET stato =? WHERE id=?");
			
			if(rc.getStato() == null) {
				ps.setBoolean(1,false);
				System.out.println("sono qui");
			}else {
				ps.setBoolean(1, rc.getStato());
			}
				ps.setInt(2, rc.getId());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteRichiesta(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Richiesta WHERE id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Richiesta> retriveByMittente(String mittente) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select id, utenteEmail, utenteUsername, destinatario, descrizione, stato FROM Richiesta WHERE utenteEmail=?");
			ps.setString(1, mittente);
			ResultSet rs = ps.executeQuery();
			Richiesta p = new Richiesta();
			ArrayList<Richiesta> array = new ArrayList<Richiesta>();
			while (rs.next()) {

				p.setId(rs.getInt(1));
				p.setUtenteEmail(rs.getString(2));
				p.setUtenteUsername(rs.getString(3));
				p.setDestinatario(rs.getString(4));
				p.setDescrizione(rs.getString(5));
				p.setStato(rs.getBoolean(6));
				array.add(p);

			}
			return array;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Richiesta> retriveByDestinatario(String destinatario) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select id, utenteEmail, utenteUsername, destinatario, descrizione, stato FROM Richiesta WHERE destinatario=?");
			ps.setString(1, destinatario);
			ResultSet rs = ps.executeQuery();
			Richiesta p = new Richiesta();
			ArrayList<Richiesta> array = new ArrayList<Richiesta>();
			while (rs.next()) {

				p.setId(rs.getInt(1));
				p.setUtenteEmail(rs.getString(2));
				p.setUtenteUsername(rs.getString(3));
				p.setDestinatario(rs.getString(4));
				p.setDescrizione(rs.getString(5));
				p.setStato(rs.getBoolean(6));
				array.add(p);
			}
			return array;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Richiesta retriveById(int id) {
		
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select id, utenteEmail, utenteUsername, destinatario, descrizione, stato FROM Richiesta WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Richiesta p = new Richiesta();
			
			if(rs.next() ) {

				p.setId(rs.getInt(1));
				p.setUtenteEmail(rs.getString(2));
				p.setUtenteUsername(rs.getString(3));
				p.setDestinatario(rs.getString(4));
				p.setDescrizione(rs.getString(5));
				p.setStato(rs.getBoolean(6));
				
			}
			return p;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Richiesta> retriveEmailNonLette(String email) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select id, utenteEmail, utenteUsername, destinatario, descrizione, stato FROM Richiesta WHERE destinatario=? AND stato=?");
			ps.setString(1, email);
			ps.setBoolean(2, false);
			ResultSet rs = ps.executeQuery();
			Richiesta p = new Richiesta();
			ArrayList<Richiesta> array = new ArrayList<Richiesta>();
			while (rs.next()) {

				p.setId(rs.getInt(1));
				p.setUtenteEmail(rs.getString(2));
				p.setUtenteUsername(rs.getString(3));
				p.setDestinatario(rs.getString(4));
				p.setDescrizione(rs.getString(5));
				p.setStato(rs.getBoolean(6));
				array.add(p);
			}
			return array;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



}
