package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ModeloEliminaCria {

	private Connection conexion;
	private Statement consulta;

	private static final Logger LOGGER = Logger.getLogger(ModeloEliminaCria.class.getName());

	public ModeloEliminaCria() {
		conexion = ConexionBD.getConexion();
	}

	public boolean eliminaCriaById(int id) {
		String sentencia = "DELETE FROM CRIAS WHERE ID_CRIA = " + id;

		try {
			consulta = conexion.createStatement();

			consulta.executeUpdate(sentencia);

			System.out.println("CRÍA ELIMINADA");
			return true;
		} catch (SQLException e) {
			System.err.println("CRÍA NO ELIMINADA " + e.getMessage());
			return false;
		} finally {
			try {
				consulta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean vaciarTabla() {
		String sentencia = "BEGIN TRAN TRANSACCION_TRUNCAR TRUNCATE TABLE CRIAS";

		try {
			conexion.setAutoCommit(false);

			consulta = conexion.createStatement();
			consulta.executeUpdate(sentencia);

		} catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
			return false;
		} finally {
			try {
				consulta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public void commitTransaccion(boolean commit) {
		try {
			if (commit) {
				conexion.prepareStatement("COMMIT TRAN TRANSACCION_TRUNCAR");
				LOGGER.info("HICE COMMIT");

				conexion.commit();
			} else {
				conexion.prepareStatement("ROLLBACK TRAN TRANSACCION_TRUNCAR");
				LOGGER.info("HICE ROLLBACK");
				conexion.rollback();
			}
			conexion.setAutoCommit(true);
		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
	}
}
