package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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

}
