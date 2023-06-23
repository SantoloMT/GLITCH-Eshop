package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.bean.Prodotto;
import model.bean.Videogioco;
import model.bean.ConPool;

public class VideogiocoDB  implements VideogiocoDAO{
	private ProdottoDAO pDAO = new ProdottoDB();

	@Override
	public List<Videogioco> findAllVideogioco() {
		try (Connection con = ConPool.getConnection()) {
		PreparedStatement ps = con
				.prepareStatement("SELECT prodottoId, nome, genere, piattaforma FROM Videogioco");
		ArrayList<Videogioco> videogioco = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
			Videogioco p = new Videogioco();
			p.setId(rs.getInt(1));
			p.setNome(rs.getString(2));
			p.setGenere(rs.getString(3));
			p.setPiattaforma(rs.getString(4));
			p.setPrezzo(prod.getPrezzo());
			p.setDescrizione(prod.getDescrizione());
			p.setImmagine(prod.getImmagine());
			videogioco.add(p);
		}
		return videogioco;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	}
	
	

	@Override
	public List<Videogioco> doRetriveVideogiocoAllRange(int min, int max) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT prodottoId, nome, genere, piattaforma FROM Videogioco LIMIT ?, ?");
			ps.setInt(1, min);
			ps.setInt(2, max);
			ArrayList<Videogioco> videogioco = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				Videogioco p = new Videogioco();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setGenere(rs.getString(3));
				p.setPiattaforma(rs.getString(4));
				p.setPrezzo(prod.getPrezzo());
				p.setDescrizione(prod.getDescrizione());
				p.setImmagine(prod.getImmagine());
				videogioco.add(p);
			}
			return videogioco;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Videogioco findVideogiocoById(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT prodottoId, nome, genere, piattaforma FROM videogioco WHERE prodottoId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				Videogioco p = new Videogioco();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setGenere(rs.getString(3));
				p.setPiattaforma(rs.getString(4));
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
	public List<Videogioco> retriveByNome(String nome) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select prodottoId, nome, genere, piattaforma FROM Videogioco WHERE nome = ?  ");
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			Videogioco p ;
			ArrayList<Videogioco> array = new ArrayList<Videogioco>();
			while (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				p = new Videogioco();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setGenere(rs.getString(3));
				p.setPiattaforma(rs.getString(4));
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
	public List<Videogioco> retriveByGenere(String genere) {
		
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select prodottoId, nome, genere, piattaforma  FROM Videogioco WHERE genere=?  ");
			ps.setString(1, genere);
			ResultSet rs = ps.executeQuery();
			Videogioco p = new Videogioco();
			ArrayList<Videogioco> array = new ArrayList<Videogioco>();
			while (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setGenere(rs.getString(3));
				p.setPiattaforma(rs.getString(4));
				p.setPrezzo(prod.getPrezzo());
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
	public List<Videogioco> retriveByPiattaforma(String piattaforma) {

		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select prodottoId, nome, genere, piattaforma  FROM Videogioco WHERE piattaforma=?  ");
			ps.setString(1, piattaforma);
			ResultSet rs = ps.executeQuery();
			Videogioco p = new Videogioco();
			ArrayList<Videogioco> array = new ArrayList<Videogioco>();
			while (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setGenere(rs.getString(3));
				p.setPiattaforma(rs.getString(4));
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
	public void createVideogioco(Videogioco prodotto) {

		try (Connection con = ConPool.getConnection()) {
			pDAO.createProdotto(prodotto);
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Videogioco (prodottoId, nome, genere, piattaforma) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, prodotto.getId());
			ps.setString(2, prodotto.getNome());
			ps.setString(3, prodotto.getGenere());
			ps.setString(4, prodotto.getPiattaforma());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	

	@Override
	public void removeProdotto(int id) {
		try (Connection con = ConPool.getConnection()) {
			pDAO.removeProdotto(id);
			PreparedStatement ps = con.prepareStatement("DELETE FROM Videogioco WHERE prodottoId=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	


	public ArrayList<String> doRetrieveLike(String against) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select nome FROM Videogioco WHERE nome LIKE '%" + against + "%'");
			ResultSet rs = ps.executeQuery();
			ArrayList<String> list = new ArrayList<String>(); 
			if(!rs.next()) {
				return null;
			}
			
			do {
				
				list.add(rs.getString("nome"));
			
			}while(rs.next());			
			
			return list;
			}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	@Override
	public Videogioco retriveByNomeAndPiattaforma(String nome, String piatt) {
		
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"Select prodottoId, nome, genere, piattaforma  FROM Videogioco WHERE nome=? AND piattaforma=?  ");
			ps.setString(1, nome);
			ps.setString(2, piatt);
			ResultSet rs = ps.executeQuery();
			Videogioco p = new Videogioco();
			
			if (rs.next()) {
				Prodotto prod = pDAO.findProdottoById(rs.getInt(1));
				
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setGenere(rs.getString(3));
				p.setPiattaforma(rs.getString(4));
				p.setPrezzo(prod.getPrezzo());
				p.setDescrizione(prod.getDescrizione());
				p.setImmagine(prod.getImmagine());
				
				return p;
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}