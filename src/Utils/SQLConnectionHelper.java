package  Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SQLConnectionHelper {

	static private String url=null;

	static private Statement statement = null;

	static private Connection conn;

	private SQLConnectionHelper() {

	}

	static synchronized public Statement getConexion(){

		if(statement==null){

			url= "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=AGRICOLA;user=sa;password=sa;";

			try {

				conn = DriverManager.getConnection(url);

				statement = conn.createStatement();

			} catch (SQLException e) {

				return null;

			}
		}
		return statement;

	}

	static synchronized public void cierraConexion(){

		try {

			conn.close();

			statement.close();

		} catch(SQLException E){}

	}

}