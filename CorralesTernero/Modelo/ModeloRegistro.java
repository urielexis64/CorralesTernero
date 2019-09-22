package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class ModeloRegistro {
	private Connection conexion;

	public ModeloRegistro() {
		conexion = ConexionBD.getConexion();
	}

	public boolean registrarCria(int id, int peso, String colorMusculo, int porcentajeGrasa) {
		String insercion = " INSERT INTO CRIAS VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement consultaPreparada = conexion.prepareStatement(insercion);

			consultaPreparada.setInt(1, id);
			consultaPreparada.setInt(2, peso);
			consultaPreparada.setString(3, colorMusculo);
			consultaPreparada.setInt(4, porcentajeGrasa);
			int columnasAfectadas = consultaPreparada.executeUpdate();
			return true;
		} catch (Exception e) {
			System.err.println("Violación de restricción de PRIMARY KEY ");
			return false;
		} /*
			 * finally { ConexionBD.cierraConexion(); }
			 */
	}

}
