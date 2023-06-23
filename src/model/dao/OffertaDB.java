package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.ConPool;
import model.bean.Offerta;


public class OffertaDB implements OffertaDAO{

	//Memorizza nel database l'offerta passata come argomento
		public void createOfferta(Offerta offerta) {
			try (Connection con = ConPool.getConnection()) {
				
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO Offerta (codice, sconto, categoria, nome) VALUES(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, offerta.getCodice());
				ps.setInt(2, offerta.getSconto());
				ps.setString(3, offerta.getCategoria());
				ps.setString(4, offerta.getNome());
				
				
				if (ps.executeUpdate() != 1) {
					throw new RuntimeException("INSERT error.");
				}

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Rimuove l'offerta che ha come codice l'intero passato come argomento dal database
		public void deleteOfferta(int codice) {
			try (Connection con = ConPool.getConnection()) {
				
				PreparedStatement ps = con.prepareStatement("DELETE FROM Offerta WHERE codice=?");
				ps.setInt(1, codice);
				if (ps.executeUpdate() != 1) {
					throw new RuntimeException("DELETE error.");
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Restituisce la lista di offerta associate alla determinata categoria
		public List<Offerta> retriveByCategoria(String categoria) {
			try (Connection con = ConPool.getConnection()) {
				PreparedStatement ps = con.prepareStatement(
						"SELECT codice, sconto, categoria, nome FROM Offerta WHERE categoria=? ");
				ps.setString(1, categoria);
				
				ArrayList<Offerta> offerte = new ArrayList<>();
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Offerta p = new Offerta();
					p.setCodice(rs.getInt(1));
					p.setSconto(rs.getInt(2));
					p.setCategoria(rs.getString(3));
					p.setNome(rs.getString(4));
					offerte.add(p);
				}
				return offerte;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Restituisce l'offerta avente come id l'intero passato come argomento
		public Offerta retriveOffertaById(int codice) {
			try (Connection con = ConPool.getConnection()) {
				PreparedStatement ps = con
						.prepareStatement("SELECT codice, sconto, categoria, nome FROM Offerta WHERE codice=?");
				ps.setInt(1, codice);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Offerta p = new Offerta();
					p.setCodice(rs.getInt(1));
					p.setSconto(rs.getInt(2));
					p.setCategoria(rs.getString(3));
					p.setNome(rs.getString(4));
					return p;
				}
				return null;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public List<Offerta> findAllOfferta() {
			try (Connection con = ConPool.getConnection()) {
				PreparedStatement ps = con
						.prepareStatement("SELECT codice, sconto, categoria, nome FROM Offerta");
				ResultSet rs = ps.executeQuery();
				ArrayList<Offerta> off = new ArrayList<Offerta>();
				while(rs.next()) {
					Offerta p = new Offerta();
					p.setCodice(rs.getInt(1));
					p.setSconto(rs.getInt(2));
					p.setCategoria(rs.getString(3));
					p.setNome(rs.getString(4));
					off.add(p);
				}
				return off;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
