package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

public class ModeloRegistroUsuarios {

	private final Logger LOGGER = Logger.getLogger(ModeloRegistroUsuarios.class.getName());

	private Connection conexion;
	private PreparedStatement consultaPreparada;

	public ModeloRegistroUsuarios() {
		conexion = ConexionBDSingleton.getConexion();
	}

	public boolean registrarUsuario(String nombre, String correo, String contra, String permisos) {
		try {
			consultaPreparada = conexion.prepareStatement("EXEC [Pa_Registrar_Usuario] ?, ?, ?, ?");

			consultaPreparada.setString(1, nombre);
			consultaPreparada.setString(2, correo);
			consultaPreparada.setString(3, contra);
			consultaPreparada.setString(4, permisos);

			consultaPreparada.executeUpdate();
			LOGGER.info("USUARIO REGISTRADO EXITOSAMENTE");
			return true;
		} catch (Exception e) {
			LOGGER.severe("ERROR AL REGISTRAR USUARIO: "+e.getMessage());
			return false;
		}
	}

}
