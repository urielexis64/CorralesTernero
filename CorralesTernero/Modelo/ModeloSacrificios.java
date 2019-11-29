package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public class ModeloSacrificios {

	private final Logger LOGGER = Logger.getLogger(ModeloSacrificios.class.getName());

	private Connection conexion;

	public ModeloSacrificios() {
		conexion = ConexionBDSingleton.getConexion();
	}
	
	public Vector<Vector<Object>> getCriasSacrificar(){
		Vector<Vector<Object>> crias = new Vector<Vector<Object>>();

		String sentencia = "SELECT * FROM VW_CRIAS_LISTAS_SACRIFICIO";

		Statement consulta = null;

		try {
			LOGGER.info("OBTENIENDO TUPLAS DE CRÍAS LISTAS PARA SACRIFICAR...");

			consulta = conexion.createStatement();

			ResultSet tuplasBD = consulta.executeQuery(sentencia);

			while (tuplasBD.next()) {
				Vector<Object> aux = new Vector<>();
				aux.add(tuplasBD.getInt(1) + "");
				aux.add(tuplasBD.getString(2) + "");
				aux.add(tuplasBD.getString(3)+"");
				aux.add(tuplasBD.getInt(4) + "");
				aux.add(tuplasBD.getString(5) + "");
				crias.add(aux);
			}
			LOGGER.info("TUPLAS OBTENIDAS CON ÉXITO");
			return crias;
		} catch (SQLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}
	
	public boolean cuelloCria(int criaId) {
		String sentencia = "EXEC PA_BAJA_CRIA "+criaId;
		
		try {
			LOGGER.info("DANDO CUELLO A CRÍA #"+criaId+"...");

			Statement consulta = conexion.createStatement();
			consulta.executeUpdate(sentencia);
			LOGGER.info("CRÍA DADA DE BAJA CON ÉXITO");
			
			return true;
		}catch(SQLException e) {
			LOGGER.severe(e.getMessage());
			return false;
		}
		
	}
	
}
