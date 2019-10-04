package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloConsulta {
	private static final Logger LOGGER = Logger.getLogger(ModeloConsulta.class.getName());

	private Connection conexion;
	private byte tipo;

	public ModeloConsulta() {
		conexion = ConexionBDSingleton.getConexion();
	}

	public Vector<Vector<String>> getTotalCrias() {
		Vector<Vector<String>> crias = new Vector<Vector<String>>();

		String sentencia = "SELECT * FROM CRIAS";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO TUPLAS DE LA TABLA CRIAS...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<String> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getFloat(2) + " kg");
				aux.add(tuplasBD.getString(3));
				aux.add(tuplasBD.getInt(4) + " %");

				String fecha = tuplasBD.getString(5); // Le doy formato a la fecha dd-mm-yyyy
				String partesFecha[] = fecha.split("-");

				aux.add(partesFecha[2] + "-" + partesFecha[1] + "-" + partesFecha[0]);
				crias.add(aux);
			}
			LOGGER.info("TUPLAS OBTENIDAS CON ÉXITO");
			return crias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	public Vector<Vector<String>> getConsultaCrias(String valor, String atributo) {
		Vector<Vector<String>> conjuntoCrias = new Vector<Vector<String>>();

		String sentencia = getSentencia(atributo);

		PreparedStatement consultaPreparada = null;

		try {
			LOGGER.info("OBTENIENDO TUPLAS FILTRADAS POR " + atributo);
			consultaPreparada = conexion.prepareStatement(sentencia);

			if (tipo == 1)
				consultaPreparada.setInt(1, Integer.parseInt(valor)); // Si es valor numérico
			else if (tipo == 2)
				consultaPreparada.setString(1, "%" + valor + "%");// Si es varchar
			else
				consultaPreparada.setString(1, valor); // Si es fecha

			ResultSet tuplasBD = consultaPreparada.executeQuery();

			while (tuplasBD.next()) {
				Vector<String> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getFloat(2) + " kg");
				aux.add(tuplasBD.getString(3));
				aux.add(tuplasBD.getInt(4) + " %");

				String fecha = tuplasBD.getString(5); // Le doy formato a la fecha dd-mm-yyyy
				String partesFecha[] = fecha.split("-");

				aux.add(partesFecha[2] + "-" + partesFecha[1] + "-" + partesFecha[0]);
				conjuntoCrias.add(aux);
			}
			LOGGER.info("TUPLAS FILTRADAS CON ÉXITO");
			return conjuntoCrias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	public int getNumCrias() {
		String contarTuplas = "SELECT COUNT(*) FROM CRIAS";
		int numCrias;

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO NÚMERO DE TUPLAS...");

			consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery(contarTuplas);
			rs.next();
			numCrias = rs.getInt(1);

			LOGGER.info("SE OBTUVIERON " + numCrias + " TUPLAS");
			return numCrias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return -1;
		}
	}

//	public Vector<Vector<String>> getConsultaCrias(String valor, String atributo) {
//		Vector<Vector<String>> conjuntoCrias = new Vector<Vector<String>>();
//		String sentencia = "";
//		switch (atributo) {
//		case "ID_CRIA":
//			tipo = 1;
//			sentencia = "SELECT * FROM CRIAS WHERE " + atributo + " = " + valor;
//			break;
//		case "PESO":
//		case "PORCENTAJE_GRASA":
//			tipo = 1;
//			sentencia = "SELECT * FROM CRIAS WHERE " + atributo + " >= " + valor;
//			break;
//		case "COLOR_MUSCULO":
//			tipo = 2;
//			sentencia = "SELECT * FROM CRIAS WHERE " + atributo + " LIKE '%" + valor + "%'";
//			break;
//		default: // Case FECHA_ENTRADA
//			tipo = 3;
//			sentencia = "SELECT * FROM CRIAS WHERE " + atributo + " >= " + valor;
//		}
//
//		Statement consulta = null;
//
//		try {
//			consulta = conexion.createStatement();
//
//			ResultSet tuplasBD = consulta.executeQuery(sentencia);
//
//			while (tuplasBD.next()) {
//				Vector<String> aux = new Vector<>();
//				aux.add(tuplasBD.getInt(1) + "");
//				aux.add(tuplasBD.getInt(2) + " kg");
//				aux.add(tuplasBD.getString(3));
//				aux.add(tuplasBD.getInt(4) + " %");
//
//				String fecha = tuplasBD.getString(5); // Le doy formato a la fecha dd-mm-yyyy
//				String partesFecha[] = fecha.split("-");
//
//				aux.add(partesFecha[2] + "-" + partesFecha[1] + "-" + partesFecha[0]);
//				conjuntoCrias.add(aux);
//			}
//			return conjuntoCrias;
//		} catch (SQLException e) {
//			System.out.println("Error: " + e.getMessage());
//			return null;
//		} 
//	}

	private String getSentencia(String atributo) {
		switch (atributo) {
		case "ID_CRIA":
			tipo = 1;
			return "SELECT * FROM CRIAS WHERE " + atributo + " = ?";
		case "PESO":
		case "PORCENTAJE_GRASA":
			tipo = 1;
			return "SELECT * FROM CRIAS WHERE " + atributo + " >= ?";
		case "COLOR_MUSCULO":
			tipo = 2;
			return "SELECT * FROM CRIAS WHERE " + atributo + " LIKE ?";
		default: // Case FECHA_ENTRADA
			tipo = 3;
			return "SELECT * FROM CRIAS WHERE " + atributo + " >= ?";
		}
	}
}
