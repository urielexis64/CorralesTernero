package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModeloEliminaCria {

	private Connection conexion;

	public ModeloEliminaCria() {
		conexion = ConexionBD.getConexion();
	}

	public boolean eliminaCria(int id) {
		String insercion = "DELETE FROM CRIAS WHERE ID_CRIA = ?";

		try {
			PreparedStatement consultaPreparada = conexion.prepareStatement(insercion);

			consultaPreparada.setInt(1, id);

			consultaPreparada.executeUpdate();
			System.out.println("ELIMINADA");

			return true;
		} catch (SQLException e) {
			System.out.println("NO ELIMINADA " + e.getMessage());
			return false;
		}
	}

	public int vaciarTabla() {
		String borrarTuplas = "BEGIN TRAN DELETE FROM CRIAS";

		PreparedStatement consultaPreparada;

		try {
			conexion.setAutoCommit(false);

			consultaPreparada = conexion.prepareStatement(borrarTuplas);

			int tuplasBorradas = consultaPreparada.executeUpdate();

			return tuplasBorradas;
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			return -1;
		}
	}

	public void commitTransaccion(boolean commit) {
		try {
			if (commit) {
				conexion.prepareStatement("COMMIT TRAN");
				System.out.println("HICE COMMIT");
				conexion.commit();
			} else {
				conexion.prepareStatement("ROLLBACK TRAN");
				System.out.println("HICE ROLLBACK");
				conexion.rollback();
			}
			conexion.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
	}

}
