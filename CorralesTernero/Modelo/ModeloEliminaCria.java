package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloEliminaCria {

	private Connection conexion;

	public ModeloEliminaCria() {
		conexion = ConexionBD.getConexion();
	}

	public boolean eliminaCria(int id) {
		String insercion = "DELETE FROM CRIAS WHERE ID_CRIA = ?";

		PreparedStatement consultaPreparada =null;
		
		try {
			consultaPreparada = conexion.prepareStatement(insercion);

			consultaPreparada.setInt(1, id);

			consultaPreparada.executeUpdate();
			System.out.println("ELIMINADA");

			return true;
		} catch (SQLException e) {
			System.err.println("NO ELIMINADA " + e.getMessage());
			return false;
		} finally {
			try {
				consultaPreparada.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int vaciarTabla() {
		String borrarTuplas = "BEGIN TRAN TRANSACCION_TRUNCAR TRUNCATE TABLE CRIAS";
		String contarTuplas = "SELECT COUNT(*) FROM CRIAS";

		Statement truncate = null, count = null;

		int tuplasTotales = -1;

		try {
			count = conexion.createStatement();
			ResultSet rs = count.executeQuery(contarTuplas);
			rs.next();
			tuplasTotales = rs.getInt(1);

			conexion.setAutoCommit(false);

			truncate = conexion.createStatement();
			truncate.execute(borrarTuplas);

		} catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
		} finally {
			try {
				truncate.close();
				count.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tuplasTotales;
	}

	public void commitTransaccion(boolean commit) {
		try {
			if (commit) {
				conexion.prepareStatement("COMMIT TRAN TRANSACCION_TRUNCAR");
				System.out.println("HICE COMMIT");
				conexion.commit();
			} else {
				conexion.prepareStatement("ROLLBACK TRAN TRANSACCION_TRUNCAR");
				System.out.println("HICE ROLLBACK");
				conexion.rollback();
			}
			conexion.setAutoCommit(true);
		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
	}
}
