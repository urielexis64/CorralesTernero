package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexionBD {
	static private String url = null;
	static private Connection conn;
	public static String host, port, databaseName, user, pwd;

	private ConexionBD() {
	}

	public static synchronized Connection getConexion() {
		if (conn == null) {
			url = "jdbc:sqlserver://" + host + "\\SQLEXPRESS:" + port + ";databaseName=" + databaseName + ";user="
					+ user + ";password=" + pwd;
			try {
				conn = DriverManager.getConnection(url);
				System.out.println("CONECTADO");
			} catch (SQLException e) {
				return null;
			}
		}
		return conn;
	}

	public static void cierraConexion() {
		try {
			conn.close();
		} catch (SQLException E) {
		}
	}

}