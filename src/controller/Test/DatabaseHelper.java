package controller.Test;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;





import model.bean.ConPool;

public class DatabaseHelper {
	public static void initializeDatabase() throws SQLException, FileNotFoundException {
		
		Connection conn = ConPool.getConnection();
		ScriptRunner sr = new ScriptRunner(conn);
		java.io.Reader create = new BufferedReader(new FileReader("creazione.sql"));
		java.io.Reader init = new BufferedReader(new FileReader("inserimento.sql"));
		sr.runScript(create);
		sr.runScript(init);
	}
	
	public static void resetDatabase() throws SQLException, FileNotFoundException {

		Connection conn = ConPool.getConnection();
		ScriptRunner sr = new ScriptRunner(conn);
		java.io.Reader reader = new BufferedReader(new FileReader("resetDatabaseTest.sql"));
		sr.runScript(reader);
	}
}