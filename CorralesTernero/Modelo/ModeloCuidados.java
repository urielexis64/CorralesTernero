package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloCuidados {
	private final Logger LOGGER = Logger.getLogger(ModeloCuidados.class.getName());

	private Connection conexion;

	public ModeloCuidados() {
		conexion = ConexionBDSingleton.getConexion();
	}
	
	public Vector<Vector<Object>> getCriasTabla(){
		Vector<Vector<Object>> crias = new Vector<Vector<Object>>();

		String sentencia = "SELECT ID_CRIA, CORRAL_ID FROM CRIAS";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO TUPLAS DE LA TABLA CRIAS...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<Object> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getInt(2) + "");
				crias.add(aux);
			}
			LOGGER.info("TUPLAS OBTENIDAS CON ÉXITO");
			return crias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}
}
