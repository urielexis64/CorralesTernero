package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ModeloActualiza {
	private static final Logger LOGGER = Logger.getLogger(ModeloActualiza.class.getName());

	private Connection conexion;

	public ModeloActualiza() {
		conexion = ConexionBDSingleton.getConexion();
	}

	public boolean actualizaCria(int id, int peso, String colorMusculo, int porcentajeGrasa) {
		String insercion = "UPDATE CRIAS SET PESO = ?, COLOR_MUSCULO = ?, PORCENTAJE_GRASA = ? WHERE ID_CRIA = ?";

		PreparedStatement consultaPreparada = null;

		try {
			LOGGER.info("ACTUALIZANDO CRIA... -> ID = " + id);
			consultaPreparada = conexion.prepareStatement(insercion);

			consultaPreparada.setInt(1, peso);
			consultaPreparada.setString(2, colorMusculo);
			consultaPreparada.setInt(3, porcentajeGrasa);
			consultaPreparada.setInt(4, id);

			consultaPreparada.executeUpdate();

			LOGGER.info("CRÍA ACTUALIZADA CON ÉXITO");
			return true;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return false;
		} finally {
			try {
				consultaPreparada.close();
			} catch (SQLException e) {
				LOGGER.severe(e.getMessage());
			}
		}
	}

//	public boolean actualizaCria(int id, int peso, String colorMusculo, int porcentajeGrasa) { //INYECCION SQL CON CONSULTA NO PREPARADA
//		String insercion = "UPDATE CRIAS SET PESO = "+peso+", COLOR_MUSCULO = '"+colorMusculo+"', PORCENTAJE_GRASA = "+porcentajeGrasa+" WHERE ID_CRIA = "+id;
//
//		try {
//			Statement consulta = conexion.createStatement();
//			consulta.execute(insercion);
//
//			System.out.println("Actualizado con éxito");
//			return true;
//		} catch (Exception e) {
//			System.err.println("Se generó un error: " + e.getMessage());
//			return false;
//		}
//	}
}
