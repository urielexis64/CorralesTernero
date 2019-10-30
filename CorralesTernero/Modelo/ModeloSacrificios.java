package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloSacrificios {

	private final Logger LOGGER = Logger.getLogger(ModeloSacrificios.class.getName());

	private Connection conexion;

	public ModeloSacrificios() {
		conexion = ConexionBDSingleton.getConexion();
	}
	
	public Vector<Vector<Object>> getCriasSacrificar(){
		Vector<Vector<Object>> crias = new Vector<Vector<Object>>();

		String sentencia = "SELECT ID_CRIA, CLASIFICACION, FECHA_ENTRADA, VECES_CUARENTENA FROM CRIAS";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO TUPLAS DE LA TABLA CRIAS...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<Object> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getString(2) + "");
				aux.add(tuplasBD.getString(3)+"");
				aux.add(tuplasBD.getInt(4) + "");
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
