package EjecutarApp;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Controlador.ControladorConsulta;
import Controlador.ControladorTabla;
import Controlador.ControladorTitleBar;
import Controlador.ControladorRegistro;
import Modelo.ConexionBDSingleton;
import Modelo.ModeloConsulta;
import Modelo.ModeloEliminaCria;
import Modelo.ModeloRegistro;
import Vista.VentanaPrincipal;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;

public class Ejecutar {

	private static VentanaPrincipal vista;

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		login();
		
		UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));

		vista = new VentanaPrincipal();

		ModeloRegistro modeloRegistro = new ModeloRegistro();
		ModeloConsulta modeloConsulta = new ModeloConsulta();
		ModeloEliminaCria modeloEliminaCria = new ModeloEliminaCria();

		ControladorRegistro controladorRegistro = new ControladorRegistro(vista, modeloRegistro);
		ControladorConsulta controladorConsulta = new ControladorConsulta(vista, modeloConsulta);
		ControladorTabla controladorSeleccionTabla = new ControladorTabla(vista, modeloEliminaCria);
		ControladorTitleBar controladorTitleBar = new ControladorTitleBar(vista);

		vista.setControlador(controladorTitleBar);
		vista.registro.setControlador(controladorRegistro);
		vista.consulta.setControladorConsulta(controladorConsulta);
		vista.consulta.setControladorSeleccionTabla(controladorSeleccionTabla);
		vista.setVisible(true);
		
		
//		int a =123,b=48; //Operadores Bitwise
//		System.out.println(b&=a); //b= a&b
//		1111011
//		0110000
//		0110000 //Resultado
	}
	
	private static void login() {	
		ConexionBDSingleton.host = "LOCALHOST";
		ConexionBDSingleton.port = "1433";
		ConexionBDSingleton.databaseName = "PRUEBA";
		ConexionBDSingleton.user = "sa";
		ConexionBDSingleton.pwd = "123";
//		ConexionBD.host = JOptionPane.showInputDialog("Host: ");
//		ConexionBD.port = JOptionPane.showInputDialog("Puerto: ");
//		ConexionBD.databaseName = JOptionPane.showInputDialog("Nombre de la base de datos: ");
//		ConexionBD.user = JOptionPane.showInputDialog("User: ");
//		ConexionBD.pwd = JOptionPane.showInputDialog("Password: ");

		if (ConexionBDSingleton.getConexion() == null) {
			JOptionPane.showMessageDialog(null, "ERROR AL CONECTARSE A LA BASE DE DATOS", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public static Component getInstance() { // Para centrar el Toast respecto a la posicion
		return vista;
	}

}
