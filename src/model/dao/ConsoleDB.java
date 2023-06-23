package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.ConPool;
import model.bean.Console;
import model.bean.Prodotto;




public class ConsoleDB implements ConsoleDAO{
	
	private ProdottoDAO pDAO = new ProdottoDB();

	

	
	@Override
	//Restituisce una lista limitata di console comprese tra i valori "min" e "max" passati come argomento
	public List<Console> doRetriveConsoleAllRange(int min, int max) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT prodottoId, modello, casaProduttrice FROM Console LIMIT ?, ?");
			ps.setInt(1, min);
			ps.setInt(2, max);
			ArrayList<Console> console = new ArrayList<Console>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				Console p = new Console();
				p.setId(rs.getInt(1));
				p.setModello(rs.getString(2));
				p.setCasaProduttrice(rs.getString(3));
				p.setPrezzo(prod.getPrezzo());
				p.setDescrizione(prod.getDescrizione());
				p.setImmagine(prod.getImmagine());
				console.add(p);
			}
			return console;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	//Restituisce la console che ha come id l'intero passato come argomento
	public Console findConsoleById(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT prodottoId, modello, casaProduttrice FROM Console WHERE prodottoId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				Console p = new Console();
				p.setId(rs.getInt(1));
				p.setModello(rs.getString(2));
				p.setCasaProduttrice(rs.getString(3));
				p.setPrezzo(prod.getPrezzo());
				p.setDescrizione(prod.getDescrizione());
				p.setImmagine(prod.getImmagine());

				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	//Restituisce la console avente come modello la stringa passata come argomento
	public Console retriveByModello(String modello) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT prodottoId, modello, casaProduttrice FROM Console WHERE modello = ? ");
			ps.setString(1, modello);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				Console p = new Console();
				p.setId(rs.getInt(1));
				p.setModello(rs.getString(2));
				p.setCasaProduttrice(rs.getString(3));
				p.setPrezzo(prod.getPrezzo());
				p.setDescrizione(prod.getDescrizione());
				p.setImmagine(prod.getImmagine());
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	//Restituisce la lista di console aventi come casa produttrice la stringa passata come argomento
	public List<Console> retriveByCasaProduttirce(String casaProduttrice) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select prodottoId, modello, casaProduttrice FROM Console WHERE casaProduttrice = ? ");
			
			ps.setString(1, casaProduttrice);
			ResultSet rs = ps.executeQuery();
			Console p = new Console();
			ArrayList<Console> array = new ArrayList<Console>();
			while (rs.next()) {
				
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				p=new Console();
				p.setId(rs.getInt(1));
				p.setModello(rs.getString(2));
				p.setCasaProduttrice(rs.getString(3));
				p.setPrezzo(prod.getPrezzo());
				p.setDescrizione(prod.getDescrizione());
				p.setImmagine(prod.getImmagine());
				array.add(p);
				
			}
			return array;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	//Memorizza una console nel database
	public void createConsole(Console prodotto) {

		try (Connection con = ConPool.getConnection()) {
			pDAO.createProdotto(prodotto);
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Console (prodottoId, modello, casaProduttrice) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, prodotto.getId());
			ps.setString(2, prodotto.getModello());
			ps.setString(3, prodotto.getCasaProduttrice());
			
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	

	@Override
	//Rimuove una console avente come id "codice" dal database
	public void removeConsole(int id) {
		try (Connection con = ConPool.getConnection()) {
			pDAO.removeProdotto(id);
			PreparedStatement ps = con.prepareStatement("DELETE FROM Console WHERE prodottoId = ?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	//Restituisce una lista di console avente modello simile a quello passato per argomento
	public ArrayList<String> doRetrieveLikeModello(String modello) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select modello FROM Console WHERE modello LIKE '%" + modello + "%'");
			ResultSet rs = ps.executeQuery();
			ArrayList<String> list = new ArrayList<String>(); 
			if(!rs.next()) {
				return null;
			}
			
			do {
				
				list.add(rs.getString("modello"));
			
			}while(rs.next());			
			
			return list;
			}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Console> findAllConsole() {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT prodottoId, modello, casaProduttrice FROM Console ");
			ArrayList<Console> console = new ArrayList<Console>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				Console p = new Console();
				p.setId(rs.getInt(1));
				p.setModello(rs.getString(2));
				p.setCasaProduttrice(rs.getString(3));
				p.setPrezzo(prod.getPrezzo());
				console.add(p);
			}
			return console;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
    
}
