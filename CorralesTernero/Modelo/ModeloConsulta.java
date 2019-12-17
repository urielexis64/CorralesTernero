package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloConsulta {
	private final Logger LOGGER = Logger.getLogger(ModeloConsulta.class.getName());

	private Connection conexion;
	public boolean vivas, asc;
	public int inicio;

	public ModeloConsulta() {
		conexion = ConexionBDSingleton.getConexion();
		vivas = true;
		asc = true;
		inicio = 0;
	}

	public Vector<Vector<String>> getTotalCrias() {
		Vector<Vector<String>> crias = new Vector<Vector<String>>();

		String sentencia = "EXEC PA_CRIAS_CONSULTA NULL, NULL, '" + (asc ? "asc" : "desc") + "' , " + (vivas ? 1 : 0)
				+ ", " + inicio + ", 15";

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
				aux.add(tuplasBD.getString(5));
				crias.add(aux);
			}
			LOGGER.info("TUPLAS OBTENIDAS CON ÉXITO");
			return crias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	public Vector<Vector<String>> getConsultaCrias(String valor, String criterio) {
		Vector<Vector<String>> conjuntoCrias = new Vector<Vector<String>>();
		String sentencia = "EXEC PA_CRIAS_CONSULTA ?, ?, '" + (asc ? "asc" : "desc") + "' , "
				+ (vivas ? 1 : 0) + ", " + inicio + ", 15";
		PreparedStatement consultaPreparada = null;

		try {
			LOGGER.info("OBTENIENDO TUPLAS FILTRADAS POR " + criterio);
			consultaPreparada = conexion.prepareStatement(sentencia);

			consultaPreparada.setString(1, criterio);
			consultaPreparada.setString(2, valor);

			ResultSet tuplasBD = consultaPreparada.executeQuery();

			while (tuplasBD.next()) {
				Vector<String> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getFloat(2) + " kg");
				aux.add(tuplasBD.getString(3));
				aux.add(tuplasBD.getInt(4) + " %");
				aux.add(tuplasBD.getString(5));
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
		String contarTuplas = "SELECT COUNT(*) FROM CRIAS WHERE cria_estado = "+(vivas?1:0);
		int numCrias;

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO NÚMERO DE TUPLAS...");

			consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery(contarTuplas);
			rs.next();
			numCrias = rs.getInt(1);

			LOGGER.info("SE CONTARON " + numCrias + " TUPLAS " + (vivas ? "(vivas)" : "(fallecidas)"));
			return numCrias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return -1;
		}
	}
}