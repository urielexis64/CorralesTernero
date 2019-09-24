package EjecutarApp;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Controlador.ControladorBarraMenu;
import Controlador.ControladorConsulta;
import Controlador.ControladorSeleccionTabla;
import Controlador.Controlador_Registro;
import Modelo.ConexionBD;
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

		Controlador_Registro controladorRegistro = new Controlador_Registro(vista, modeloRegistro);
		ControladorConsulta controladorConsulta = new ControladorConsulta(vista, modeloConsulta);
		ControladorBarraMenu controladorMenu = new ControladorBarraMenu(vista);
		ControladorSeleccionTabla controladorSeleccionTabla = new ControladorSeleccionTabla(vista, modeloEliminaCria);

		vista.registro.setControlador(controladorRegistro);
		vista.barraMenu.setControlador(controladorMenu);
		vista.consulta.setControlador(controladorConsulta);
		vista.consulta.setControladorSeleccionTabla(controladorSeleccionTabla);

		vista.setVisible(true);
	}

	private static void login() {
		ConexionBD.host = JOptionPane.showInputDialog("Host: ");
		ConexionBD.port = JOptionPane.showInputDialog("Puerto: ");
		ConexionBD.databaseName = JOptionPane.showInputDialog("Nombre de la base de datos: ");
		ConexionBD.user = JOptionPane.showInputDialog("User: ");
		ConexionBD.pwd = JOptionPane.showInputDialog("Password: ");

		if (ConexionBD.getConexion() == null) {
			JOptionPane.showMessageDialog(null, "ERROR AL CONECTARSE A LA BASE DE DATOS", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public static Component getInstance() {
		return vista;
	}

}
