package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloSensores {

	private final Logger LOGGER = Logger.getLogger(ModeloSensores.class.getName());

	private Connection conexion;

	public ModeloSensores() {
		conexion = ConexionBDSingleton.getConexion();
	}
	
	public boolean randomSensores(){
		String sentencia = "EXEC [PA_DATOS_RANDOM_SENSORES]";
		Statement consulta = null;

		try {
			LOGGER.info("ESTABLECIENDO VALORES ALEATORIOS A SENSORES...");

			consulta = conexion.createStatement();

			consulta.executeUpdate(sentencia);

			LOGGER.info("VALORES ESTABLECIDOS CORRECTAMENTE");
			return true;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return false;
		}
	}
	
	public Vector<Vector<Object>> getInfoSensores(){
		Vector<Vector<Object>> crias = new Vector<Vector<Object>>();

		String sentencia = "SELECT * FROM SENSORES";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO INFORMACIÓN SOBRE SENSORES...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<Object> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getInt(2) + "");
				aux.add(tuplasBD.getString(3));
				aux.add(tuplasBD.getString(4));
				crias.add(aux);
			}
			LOGGER.info("INFORMACIÓN DE SENSORES OBTENIDA CON ÉXITO");
			return crias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}
}
