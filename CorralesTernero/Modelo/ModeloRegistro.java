package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ModeloRegistro {
	private static final Logger LOGGER = Logger.getLogger(ModeloRegistro.class.getName());

	private Connection conexion;

	public ModeloRegistro() {
		conexion = ConexionBDSingleton.getConexion();
	}

	public boolean registrarCria(int id, int peso, String colorMusculo, int porcentajeGrasa, String fecha_entrada) {
		String insercion = "EXECUTE PA_INSERTACRIA ?, ?, ?, ?, ?";

		try {
			LOGGER.info("INSERTANDO CRIA -> ID = " + id);
			PreparedStatement consultaPreparada = conexion.prepareStatement(insercion);

			consultaPreparada.setInt(1, id);
			consultaPreparada.setInt(2, peso);
			consultaPreparada.setString(3, colorMusculo);
			consultaPreparada.setInt(4, porcentajeGrasa);
			consultaPreparada.setString(5, fecha_entrada);

			consultaPreparada.executeUpdate();
			LOGGER.info("INSERCIÓN REALIZADA CORRECTAMENTE -> ID = " + id);
			return true;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return false;
		}
	}

//	public boolean registrarCria(int id, int peso, String colorMusculo, int porcentajeGrasa, String fecha_entrada) { //Inyeccion SQL
//		String insercion = " EXECUTE PA_INSERTACRIA " + id + ", " + peso + ", '" + colorMusculo + "', "
//				+ porcentajeGrasa + ", '" + fecha_entrada + "'";
//
//		try {
//			Statement consultaPreparada = conexion.createStatement();
//
//			consultaPreparada.executeUpdate(insercion);
//			LOGGER.info("INSERCIÓN REALIZADA CORRECTAMENTE (ID = " + id + ")");
//			return true;
//		} catch (Exception e) {
//			LOGGER.severe(e.getMessage());
//			return false;
//		}
//	}
}
