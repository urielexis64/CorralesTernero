package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ModeloEliminaCria {
	private static final Logger LOGGER = Logger.getLogger(ModeloEliminaCria.class.getName());

	private Connection conexion;
	private Statement consulta;

	public ModeloEliminaCria() {
		conexion = ConexionBDSingleton.getConexion();
	}

	public boolean eliminaCriaById(int id) {
		String sentencia = "DELETE FROM CRIAS WHERE ID_CRIA = " + id;

		try {
			LOGGER.info("ELIMINANDO CRIA CON ID = " + id);
			
			consulta = conexion.createStatement();

			consulta.executeUpdate(sentencia);

			LOGGER.info("CRÍA ELIMINADA -> ID = " + id);
			return true;
		} catch (SQLException e) {
			LOGGER.severe("CRIA NO ELIMINADA -> ID = " + id);
			return false;
		}
	}

	public boolean vaciarTabla() {
		String sentencia = "BEGIN TRAN TRANSACCION_TRUNCAR TRUNCATE TABLE CRIAS";

		try {
			LOGGER.info("TRUNCANDO TABLA CRIAS...");
			conexion.setAutoCommit(false);

			consulta = conexion.createStatement();
			consulta.executeUpdate(sentencia);
			return true;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return false;
		}
	}

	public void commitTransaccion(boolean commit) {
		try {
			if (commit) {
				conexion.prepareStatement("COMMIT TRAN TRANSACCION_TRUNCAR");
				conexion.commit();
				LOGGER.info("HICE COMMIT");
			} else {
				conexion.prepareStatement("ROLLBACK TRAN TRANSACCION_TRUNCAR");
				conexion.rollback();
				LOGGER.info("HICE ROLLBACK");
			}
			conexion.setAutoCommit(true);
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
		}
	}
}
