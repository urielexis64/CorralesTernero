package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ModeloEliminaCria {
	private final Logger LOGGER = Logger.getLogger(ModeloEliminaCria.class.getName());

	private Connection conexion;
	private Statement consulta;

	public ModeloEliminaCria() {
		conexion = ConexionBDSingleton.getConexion();
		try {consulta = conexion.createStatement();}catch(SQLException e) {}
	}

	public boolean eliminaCriaById(int id) {
		String sentenciaBorraCria = "EXEC PA_ELIMINA_CRIA " + id;
		
		try {
			LOGGER.info("ELIMINANDO CRIA CON ID = " + id);
			
			consulta.executeUpdate(sentenciaBorraCria);

			LOGGER.info("CRÍA ELIMINADA -> ID = " + id);
			return true;
		} catch (SQLException e) {
			LOGGER.severe("CRIA NO ELIMINADA -> ID = " + id+"\nError: "+e.getMessage());
			return false;
		}
	}

	public boolean vaciarTabla() {
		String sentencia = "EXEC PA_TRUNCA_CRIAS";

		try {
			LOGGER.info("TRUNCANDO TABLA CRIAS...");
//			conexion.setAutoCommit(false); //Es como un BEGIN TRAN

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
