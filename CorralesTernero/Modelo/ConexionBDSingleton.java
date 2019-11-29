package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConexionBDSingleton {
	private static final Logger LOGGER = Logger.getLogger(ConexionBDSingleton.class.getName());

	private static String url = null;
	private static Connection conn;
	public static String host, port, databaseName, user, pwd;

	private ConexionBDSingleton() {
	}

	public static synchronized Connection getConexion() {
		if (conn == null) {
			url = "jdbc:sqlserver://" + host + "\\SQLEXPRESS:" + port + ";databaseName=" + databaseName + ";user="
					+ user + ";password=" + pwd;
			try {
				conn = DriverManager.getConnection(url);
//				LOGGER.info("Conectado a base de datos : " + databaseName);
			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
				return null;
			}
		}
		return conn;
	}

	public static void cierraConexion() {
//		try {
////			conn.close();
////			LOGGER.info("Conexión cerrada exitosamente");
//		} catch (SQLException e) {
//			LOGGER.severe(e.getMessage());
//		}
	}

}