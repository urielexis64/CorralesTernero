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
		try {consulta = conexion.createStatement();}catch(SQLException e) {}
	}

	public boolean eliminaCriaById(int id) {
		String sentencia = "DELETE FROM CRIAS WHERE ID_CRIA = " + id;

		try {
			LOGGER.info("ELIMINANDO CRIA CON ID = " + id);
			
			consulta.executeUpdate(sentencia);

			LOGGER.info("CRÍA ELIMINADA -> ID = " + id);
			return true;
		} catch (SQLException e) {
			LOGGER.severe("CRIA NO ELIMINADA -> ID = " + id);
			return false;
		}
	}

	public boolean vaciarTabla() {
		String sentencia = "TRUNCATE TABLE CRIAS";

		try {
			LOGGER.info("TRUNCANDO TABLA CRIAS...");
			conexion.setAutoCommit(false); //Es como un BEGIN TRAN

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
				conexion.commit();
				LOGGER.info("HICE COMMIT");
			} else {
				conexion.rollback();
				LOGGER.info("HICE ROLLBACK");
			}
			conexion.setAutoCommit(true);
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
		}
	}
}
