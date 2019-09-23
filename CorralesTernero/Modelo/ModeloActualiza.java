package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModeloActualiza {
	private Connection conexion;

	public ModeloActualiza() {
		conexion = ConexionBD.getConexion();
	}

	public boolean actualizaCria(int id, int peso, String colorMusculo, int porcentajeGrasa) {
		String insercion = "UPDATE CRIAS SET PESO = ?, COLOR_MUSCULO = ?, PORCENTAJE_GRASA = ? WHERE ID_CRIA = ?";

		try {
			PreparedStatement consultaPreparada = conexion.prepareStatement(insercion);

			consultaPreparada.setInt(1, peso);
			consultaPreparada.setString(2, colorMusculo);
			consultaPreparada.setInt(3, porcentajeGrasa);
			consultaPreparada.setInt(4, id);

			consultaPreparada.executeUpdate();

			System.out.println("Actualizado con éxito");
			return true;
		} catch (Exception e) {
			System.err.println("Se generó un error: " + e.getMessage());
			return false;
		}
	}
}
