package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModeloRegistro {
	private Connection conexion;

	public ModeloRegistro() {
		conexion = ConexionBD.getConexion();
	}

	public boolean registrarCria(int id, int peso, String colorMusculo, int porcentajeGrasa, String fecha_entrada) {
		String insercion = " EXECUTE PA_INSERTACRIA ?, ?, ?, ?, ?";

		try {
			PreparedStatement consultaPreparada = conexion.prepareStatement(insercion);

			consultaPreparada.setInt(1, id);
			consultaPreparada.setInt(2, peso);
			consultaPreparada.setString(3, colorMusculo);
			consultaPreparada.setInt(4, porcentajeGrasa);
			consultaPreparada.setString(5, fecha_entrada);

			consultaPreparada.executeUpdate();

			return true;
		} catch (Exception e) {
			System.err.println("Violación de restricción de PRIMARY KEY"+e.getMessage());
			return false;
		}
	}
}
