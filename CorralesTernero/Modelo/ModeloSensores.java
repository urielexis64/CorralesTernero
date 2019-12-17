package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import EjecutarApp.Ejecutar;

public class ModeloSensores {

	private final Logger LOGGER = Logger.getLogger(ModeloSensores.class.getName());

	private Connection conexion;

	public ModeloSensores() {
		conexion = ConexionBDSingleton.getConexion();
	}

	public boolean randomSensores() {
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

	public Vector<Vector<Object>> getInfoSensores() {
		Vector<Vector<Object>> crias = new Vector<Vector<Object>>();

		String sentencia = "SELECT * FROM VW_INFORMACION_SENSORES";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO INFORMACIÓN SOBRE SENSORES...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<Object> aux = new Vector<>();
				aux.add(tuplasBD.getString(1));
				aux.add(tuplasBD.getInt(2) + "");
				aux.add(tuplasBD.getString(3));
				aux.add(tuplasBD.getString(4));
				aux.add(tuplasBD.getString(5));
				crias.add(aux);
			}
			LOGGER.info("INFORMACIÓN DE SENSORES OBTENIDA CON ÉXITO");
			return crias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	public int asignarSensor(int idCria, String idSensor) {
		String sentencia = "EXEC PA_ASIGNA_SENSOR ?, ?";

		PreparedStatement ps = null;
		Statement st = null;
		try {
			st = conexion.createStatement();

			if (st.executeUpdate("update CRIAS set peso=peso WHERE ID_CRIA = " + idCria) == 0)
				return 1;

			ResultSet rs = st.executeQuery("SELECT CLASIFICACION, SENSOR_ID FROM CRIAS WHERE ID_CRIA = " + idCria);
			rs.next();

			if (!rs.getString(1).equals("COBERTURA 2"))
				return 2;

			if (rs.getString(2) != null)
				return 2;

			LOGGER.info("ASIGNANDO SENSOR A CRÍA #" + idCria);

			ps = conexion.prepareStatement(sentencia);
			ps.setInt(1, idCria);
			ps.setString(2, idSensor);

			ps.executeUpdate();

			LOGGER.info("SENSOR ASIGNADO CON ÉXITO -> " + idCria);
			return 0;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return -1;
		} finally {
			try {
				if (st != null)
					st.close();
				if (ps != null)
					ps.close();
			} catch (Exception ex) {
			}
		}
	}

	public Vector<String> getIdsSensores() {
		Vector<String> ids = new Vector<String>();

		String sentencia = "SELECT * FROM VW_IDS_SENSORES";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO SENSORES DISPONIBLES...");

			consulta = conexion.createStatement();

			ResultSet idsSensores = consulta.executeQuery(sentencia);

			while (idsSensores.next())
				ids.add(idsSensores.getString(1));

			LOGGER.info("SENSORES DISPONIBLES OBTENIDOS CON ÉXITO");
			return ids;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	public int registrarSensor(String idSensor) {
		String sentencia = "EXEC PA_REGISTRA_SENSOR ?";

		PreparedStatement ps = null;

		try {
			Statement st = conexion.createStatement();
			if (st.executeQuery("SELECT 1 FROM SENSORES WHERE SENSOR_ID = '" + idSensor + "'").next())
				return 1;

			LOGGER.info("REGISTRANDO SENSOR " + idSensor);

			ps = conexion.prepareStatement(sentencia);
			ps.setString(1, idSensor);
			ps.executeUpdate();
			LOGGER.info("SENSOR REGISTRADO CON ÉXITO -> " + idSensor);
			return 0;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return -1;
		}
	}
}
