package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloClasificacion {

	private final Logger LOGGER = Logger.getLogger(ModeloClasificacion.class.getName());

	private Connection conexion;

	public ModeloClasificacion() {
		conexion = ConexionBDSingleton.getConexion();
	}
	
	public Vector<Vector<String>> getCriasTabla(){
		Vector<Vector<String>> crias = new Vector<Vector<String>>();

		String sentencia = "SELECT * FROM VW_CRIAS_CLASIFICADAS";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO TUPLAS CLASIFICADAS DE LA TABLA CRIAS...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<String> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getInt(2) + "");
				aux.add(tuplasBD.getInt(3)+"");
				aux.add(tuplasBD.getInt(4) + "");
				aux.add(tuplasBD.getString(5));
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
