package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import EjecutarApp.Ejecutar;

public class ModeloCuidados {
	private final Logger LOGGER = Logger.getLogger(ModeloCuidados.class.getName());

	private Connection conexion;

	public ModeloCuidados() {
		conexion = ConexionBDSingleton.getConexion();
	}

	public Vector<Vector<Object>> getCriasTabla() {
		Vector<Vector<Object>> crias = new Vector<Vector<Object>>();

		String sentencia = "SELECT * FROM VW_CRIAS_FINAS";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO TUPLAS DE LA TABLA CRIAS...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<Object> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getInt(2) + "");
				crias.add(aux);
			}
			LOGGER.info("TUPLAS OBTENIDAS CON ÉXITO");
			return crias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	public boolean actualizaSalud(Vector<Vector<String>> crias) {
		String sentenciaEnferma = "EXEC PA_ACTUALIZA_ESTADO 2, ";
		String sentenciaSana = "EXEC PA_ACTUALIZA_ESTADO 1, ";

		Statement consulta = null;

		Vector<Vector<Object>> criasViejas = getCriasTabla();

		try {
			LOGGER.info("ACTUALIZANDO SALUD DE CRIAS...");

			consulta = conexion.createStatement();
			for (int i = 0; i < crias.size(); i++) {
				int idCria = Integer.parseInt(crias.get(i).get(0));
				if (crias.get(i).get(1).equals("true") && criasViejas.get(i).get(1).equals("1")) { //Si se puso en "Enferma" y antes estaba "sana", entra
					consulta.executeUpdate(sentenciaEnferma + idCria);
					ModeloLog.registraMovimiento(idCria, "Se enfermó", Ejecutar.NOMBRE_USUARIO);
				} else if (crias.get(i).get(1).equals("false") && criasViejas.get(i).get(1).equals("2")) {//Si se puso en "Sana" y antes estaba "enferma", entra
					consulta.executeUpdate(sentenciaSana + crias.get(i).get(0));
					ModeloLog.registraMovimiento(idCria, "Se recuperó", Ejecutar.NOMBRE_USUARIO);
				}
			}

			LOGGER.info("TUPLAS ACTUALIZADAS CON ÉXITO");
			return true;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return false;
		}
	}
}
