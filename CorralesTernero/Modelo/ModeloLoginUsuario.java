package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ModeloLoginUsuario {
	private final Logger LOGGER = Logger.getLogger(ModeloLoginUsuario.class.getName());

	private Connection conexion;
	private PreparedStatement ps;

	public ModeloLoginUsuario() {
		conexion = ConexionBDSingleton.getConexion();
	}

	public String[] iniciarSesion(String correo, String contra) {
		try {
			ps = conexion.prepareStatement("EXEC PA_LOGIN_USUARIO ?, ?");
			ps.setString(1, correo);
			ps.setString(2, contra);

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return new String[] { rs.getString(1), rs.getString(2) };
			else
				throw new SQLException();
		} catch (SQLException e) {
			return null;
		}
	}
}
