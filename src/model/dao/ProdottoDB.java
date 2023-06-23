package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.ConPool;

import model.bean.Prodotto;


public class ProdottoDB implements ProdottoDAO{

	
	public List<Prodotto> doRetrieveAllRange(int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT id, immagine, prezzo, descrizione FROM Prodotto LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ArrayList<Prodotto> prod = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			java.sql.Blob b1; 
			java.sql.Blob b2 ;
			while (rs.next()) {
				
				b1 = rs.getBlob("immagine");
				b2 = rs.getBlob("descrizione");
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setImmagine( new String(b1.getBytes(1l, (int) b1.length())));
				p.setPrezzo(rs.getFloat(3));
				p.setDescrizione( new String(b2.getBytes(1l, (int) b2.length())));
				prod.add(p);
			}
			return prod;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Prodotto findProdottoById(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT id, immagine, prezzo, descrizione FROM Prodotto WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				java.sql.Blob b1 = rs.getBlob(2);
				java.sql.Blob b2 = rs.getBlob(4);
				p.setImmagine(new String(b1.getBytes(1l, (int) b1.length())));
				p.setPrezzo(rs.getFloat(3));
				p.setDescrizione(new String(b2.getBytes(1l, (int) b2.length())));
				
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void createProdotto(Prodotto prodotto) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Prodotto (id, immagine, prezzo, descrizione) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, prodotto.getId());
			ps.setString(2, prodotto.getImmagine());	
			ps.setFloat(3, prodotto.getPrezzo());
			ps.setString(4, prodotto.getDescrizione());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doUpdatePrezzo(Prodotto prodotto) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE Prodotto SET prezzo=? WHERE id=?");
	
			ps.setFloat(1, prodotto.getPrezzo());
			ps.setInt(2, prodotto.getId());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

	public void removeProdotto(int codice) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Prodotto WHERE id=?");
			ps.setInt(1, codice);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
