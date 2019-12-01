package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloSigProceso {
	private final Logger LOGGER = Logger.getLogger(ModeloSigProceso.class.getName());

	private Connection conexion;

	public ModeloSigProceso() {
		conexion = ConexionBDSingleton.getConexion();
	}
	
	public Vector<Vector<String>> getCriasSigProceso(){
		Vector<Vector<String>> crias = new Vector<Vector<String>>();

		String sentencia = "SELECT * FROM VW_CRIAS_SIG_PROCESO";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO INFORMACIÓN SOBRE CRÍAS (SIG. PROCESO)...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<String> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getString(2) + "");
				aux.add(tuplasBD.getString(3));
				aux.add(tuplasBD.getInt(4)+"");
				aux.add(tuplasBD.getInt(5)+"");
				
				crias.add(aux);
			}
			LOGGER.info("INFORMACIÓN DE CRÍAS (SIG. PROCESO) OBTENIDA CON ÉXITO");
			return crias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}
	
	
	
}
