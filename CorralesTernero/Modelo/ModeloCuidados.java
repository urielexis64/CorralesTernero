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

	public Vector<Vector<Object>> getCriasTabla() {
		Vector<Vector<Object>> crias = new Vector<Vector<Object>>();

		String sentencia = "SELECT * FROM VW_CRIAS_FINAS";

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

	public boolean actualizaSalud(Vector<Vector<String>> crias) {
		String sentenciaEnferma = "UPDATE CRIAS SET CORRAL_ID = 2, ALIM_ID = 2, VECES_CUARENTENA = VECES_CUARENTENA+1 WHERE ID_CRIA = ";
		String sentenciaSana = "UPDATE CRIAS SET CORRAL_ID = 1, ALIM_ID = 1 WHERE ID_CRIA = ";

		Statement consulta = null;

		try {
			LOGGER.info("ACTUALIZANDO SALUD DE CRIAS...");

			consulta = conexion.createStatement();
			for (int i = 0; i < crias.size(); i++) {
				if (crias.get(i).get(1).equals("true"))
					consulta.executeUpdate(sentenciaEnferma + crias.get(i).get(0));
				else
					consulta.executeUpdate(sentenciaSana + crias.get(i).get(0));
			}

			LOGGER.info("TUPLAS ACTUALIZADAS CON ÉXITO");
			return true;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return false;
		}
	}
}
