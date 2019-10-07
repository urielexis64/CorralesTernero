package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ModeloRegistro {
	private static final Logger LOGGER = Logger.getLogger(ModeloRegistro.class.getName());

	private Connection conexion;
	private PreparedStatement consultaPreparada;
	private Statement consultaIdentity;

	public ModeloRegistro() {
		conexion = ConexionBDSingleton.getConexion();
		try {
			consultaPreparada = conexion.prepareStatement("EXECUTE PA_INSERTACRIA ?, ?, ?, ?");
			consultaIdentity = conexion.createStatement();
		} catch (SQLException e) {
		}
	}

	public int registrarCria(float peso, String colorMusculo, int porcentajeGrasa, String fecha_entrada) {
		try {
			LOGGER.info("INSERTANDO CRIA...");

			consultaPreparada.setFloat(1, peso);
			consultaPreparada.setString(2, colorMusculo);
			consultaPreparada.setInt(3, porcentajeGrasa);
			consultaPreparada.setString(4, fecha_entrada);
			consultaPreparada.executeUpdate();

			ResultSet rs = consultaIdentity.executeQuery("SELECT IDENT_CURRENT ('CRIAS')");
			rs.next();
			int idGenerado = rs.getInt(1);

			LOGGER.info("INSERCIÓN REALIZADA CORRECTAMENTE -> ID = " + idGenerado);
			return idGenerado;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return -1;
		}
	}

//	public int registrarCria(float peso, String colorMusculo, int porcentajeGrasa, String fecha_entrada) { //Inyeccion SQL
//		String insercion = " EXECUTE PA_INSERTACRIA " + peso + ", '" + colorMusculo + "', "
//				+ porcentajeGrasa + ", '" + fecha_entrada + "'";
//
//		try {
//			Statement consulta = conexion.createStatement();
//
//			consulta.executeUpdate(insercion);
//
//			ResultSet rs = consultaIdentity.executeQuery("SELECT IDENT_CURRENT ('CRIAS')");
//			rs.next();
//			int idGenerado = rs.getInt(1);
//			LOGGER.info("INSERCIÓN REALIZADA CORRECTAMENTE (ID = " +idGenerado + ")");
//			return idGenerado;
//		} catch (Exception e) {
//			LOGGER.severe(e.getMessage());
//			return -1;
//		}
//	}
}
