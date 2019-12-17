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

	public Object[] iniciarSesion(String correo, String contra) {
		try {
			ps = conexion.prepareStatement("EXEC PA_LOGIN_USUARIO ?, ?");
			ps.setString(1, correo);
			ps.setString(2, contra);
	
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int primeraColumna = rs.getInt(1);
				if (primeraColumna == -1)
					return new Object[] { primeraColumna };

				LOGGER.info("LOGIN REALIZADO CON ÉXITO");
				return new Object[] { rs.getInt(1), rs.getString(2).toString(), rs.getString(3).toString() };
			} else
				throw new SQLException();
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	public boolean cerrarSesion(int idUsuario) {
		try {
			ps = conexion.prepareStatement("EXEC PA_LOGOUT_USUARIO ?");
			ps.setInt(1, idUsuario);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			return false;
		}
	}
}
