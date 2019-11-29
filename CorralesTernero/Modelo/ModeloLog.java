package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloLog {
	private final static Logger LOGGER = Logger.getLogger(ModeloLog.class.getName());

	private Connection conexion;
	private static Statement consulta;

	public ModeloLog() {
		conexion = ConexionBDSingleton.getConexion();
		try {
			consulta = conexion.createStatement();
		} catch (SQLException e) {
		}
	}

	public static boolean registraMovimiento(int idCria, String movimiento) {
		String sentenciaMovimiento = "EXEC PA_REGISTRA_MOVIMIENTO " + idCria + ", '" + movimiento + "'";

		try {
			LOGGER.info("REGISTRANDO MOVIMIENTO ID -> " + idCria);

			consulta.execute(sentenciaMovimiento);

			LOGGER.info("MOVIMIENTO REGISTRADO -> ID = " + idCria);
			return true;
		} catch (SQLException e) {
			LOGGER.severe("MOVIMIENTO NO REGISTRADO -> ID = " + e.getMessage());
			return false;
		}
	}

	public Vector<Vector<String>> getMovimientos() {
		try {
			ResultSet rs = consulta.executeQuery("SELECT * FROM LOG");
			Vector<Vector<String>> movimientos = new Vector<Vector<String>>();

			while (rs.next()) {
				Vector<String> tupla = new Vector<String>();
				tupla.add(rs.getInt(1) + "");
				tupla.add(rs.getString(2));
				tupla.add(rs.getString(3));
				movimientos.add(tupla);
			}

			return movimientos;
		} catch (SQLException e) {
			return null;
		}
	}

}
