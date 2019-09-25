package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ModeloConsulta {
	private Connection conexion;
	private byte tipo;

	public ModeloConsulta() {
		conexion = ConexionBD.getConexion();
	}

	public Vector<Vector<String>> getTotalCrias() {
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

	public Vector<Vector<String>> getCria(String valor, String atributo) {
		Vector<Vector<String>> conjuntoCrias = new Vector<Vector<String>>();

		String sentencia = getSentencia(atributo);

		try {
			PreparedStatement consultaPreparada = conexion.prepareStatement(sentencia);

			if (tipo == 1)
				consultaPreparada.setInt(1, Integer.parseInt(valor));
			else
				consultaPreparada.setString(1, "%" + valor + "%");

			ResultSet tuplasBD = consultaPreparada.executeQuery();

			while (tuplasBD.next()) {
				Vector<String> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getInt(2) + " kg");
				aux.add(tuplasBD.getString(3));
				aux.add(tuplasBD.getInt(4) + " %");
				conjuntoCrias.add(aux);
			}
			return conjuntoCrias;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	private String getSentencia(String atributo) {
		switch (atributo) {
		case "ID_CRIA":
			tipo = 1;
			return "SELECT * FROM CRIAS WHERE " + atributo + " = ?";
		case "PESO":
			tipo = 1;
			return "SELECT * FROM CRIAS WHERE " + atributo + " >= ?";
		case "COLOR_MUSCULO":
			tipo = 2;
			return "SELECT * FROM CRIAS WHERE " + atributo + " LIKE ?";
		default:
			tipo = 1;
			return "SELECT * FROM CRIAS WHERE " + atributo + " >= ?";
		}
	}
}
