package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ModeloConsulta {
	private Connection conexion;

	public ModeloConsulta() {
		conexion = ConexionBD.getConexion();
	}

	public Vector<Vector<String>> getCrias() {
		Vector<Vector<String>> crias = new Vector<Vector<String>>();

		String insercion = "SELECT * FROM CRIAS";

		try {
			PreparedStatement consultaPreparada = conexion.prepareStatement(insercion);

			ResultSet tuplasBD = consultaPreparada.executeQuery();

			while (tuplasBD.next()) {
				Vector<String> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getInt(2) + " kg");
				aux.add(tuplasBD.getString(3));
				aux.add(tuplasBD.getInt(4) + " %");
				crias.add(aux);
			}
			return crias;
		} catch (SQLException e) {
			return null;
		}
	}

	public Object[] getCriaById(int id) {
		Vector<String> cria = new Vector<String>();

		String insercion = "SELECT * FROM CRIAS WHERE ID_CRIA = ?";

		try {
			PreparedStatement consultaPreparada = conexion.prepareStatement(insercion);
			consultaPreparada.setInt(1, id);

			ResultSet tuplasBD = consultaPreparada.executeQuery();

			while (tuplasBD.next()) {
				cria.add(tuplasBD.getInt(1) + "");
				cria.add(tuplasBD.getInt(2) + " kg");
				cria.add(tuplasBD.getString(3));
				cria.add(tuplasBD.getInt(4) + " %");
			}
			if (cria.size() != 0)
				return cria.toArray();
			else
				return null;
		} catch (SQLException e) {
			return null;
		}
	}
}
