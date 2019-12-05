
package EjecutarApp;

import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Controlador.ControladorClasificacion;
import Controlador.ControladorConsulta;
import Controlador.ControladorCuidados;
import Controlador.ControladorLog;
import Controlador.ControladorLoginUsuario;
import Controlador.ControladorModal;
import Controlador.ControladorTabla;
import Controlador.ControladorTitleBar;
import Controlador.ControladorRegistro;
import Controlador.ControladorRegistroUsuarios;
import Controlador.ControladorInformes;
import Controlador.ControladorSacrificios;
import Controlador.ControladorSensores;
import Controlador.ControladorSigProceso;
import Modelo.ConexionBDSingleton;
import Modelo.ModeloActualiza;
import Modelo.ModeloClasificacion;
import Modelo.ModeloConsulta;
import Modelo.ModeloCuidados;
import Modelo.ModeloEliminaCria;
import Modelo.ModeloLog;
import Modelo.ModeloLoginUsuario;
import Modelo.ModeloRegistro;
import Modelo.ModeloRegistroUsuarios;
import Modelo.ModeloSacrificios;
import Modelo.ModeloSensores;
import Modelo.ModeloSigProceso;
import Vista.LoginUsuario;
import Vista.VentanaPrincipal;
import extras.SplashScreen;
import javazoom.jl.player.Player;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;

public class Ejecutar {

	private static VentanaPrincipal vista;
	public static String NOMBRE_USUARIO;

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
		login();
//		iniciaAplicacion("Prueba", "111111111");
		LoginUsuario loginUsuario = new LoginUsuario();
		ModeloLoginUsuario modeloLoginUsuario = new ModeloLoginUsuario();
		ControladorLoginUsuario controladorLoginUsuario = new ControladorLoginUsuario(loginUsuario, modeloLoginUsuario);
		loginUsuario.setControlador(controladorLoginUsuario);
	}

	public static void iniciaAplicacion(String nombreUsuario, String permisos) {
		NOMBRE_USUARIO = nombreUsuario;

		SplashScreen splash = new SplashScreen();
		splash.show();

		moo();
		vista = new VentanaPrincipal(nombreUsuario, permisos);

		ModeloRegistro modeloRegistro = new ModeloRegistro();
		ModeloClasificacion modeloClasificaicon = new ModeloClasificacion();
		ModeloConsulta modeloConsulta = new ModeloConsulta();
		ModeloEliminaCria modeloEliminaCria = new ModeloEliminaCria();
		ModeloActualiza modeloActualiza = new ModeloActualiza();
		ModeloSacrificios modeloSacrificios = new ModeloSacrificios();
		ModeloCuidados modeloCuidados = new ModeloCuidados();
		ModeloSensores modeloSensores = new ModeloSensores();
		ModeloLog modeloLog = new ModeloLog();
		ModeloSigProceso modeloSigProceso = new ModeloSigProceso();
		ModeloRegistroUsuarios modeloRegistroUsuarios = new ModeloRegistroUsuarios();

		ControladorRegistro controladorRegistro = new ControladorRegistro(vista, modeloRegistro);
		ControladorClasificacion controladorClasificacion = new ControladorClasificacion(vista, modeloClasificaicon);
		ControladorConsulta controladorConsulta = new ControladorConsulta(vista, modeloConsulta);
		ControladorTabla controladorSeleccionTabla = new ControladorTabla(vista, modeloEliminaCria);
		ControladorModal controladorModal = new ControladorModal(vista, modeloActualiza);
		ControladorSacrificios controladorSacrificios = new ControladorSacrificios(vista, modeloSacrificios);
		ControladorTitleBar controladorTitleBar = new ControladorTitleBar(vista);
		ControladorCuidados controladorCuidados = new ControladorCuidados(vista, modeloCuidados);
		ControladorSensores controladorSensores = new ControladorSensores(vista, modeloSensores);
		ControladorLog controladorLog = new ControladorLog(vista, modeloLog);
		ControladorSigProceso controladorSigProceso = new ControladorSigProceso(vista, modeloSigProceso);
		ControladorInformes controladorInformes = new ControladorInformes(vista, modeloSigProceso);
		ControladorRegistroUsuarios controladorRegistroUsuarios = new ControladorRegistroUsuarios(vista,
				modeloRegistroUsuarios);

		vista.setControlador(controladorTitleBar);
		vista.registro.setControlador(controladorRegistro);
		vista.clasificacion.setControlador(controladorClasificacion);
		vista.consulta.setControladorConsulta(controladorConsulta);
		vista.consulta.setControladorSeleccionTabla(controladorSeleccionTabla);
		vista.consulta.modal.setControlador(controladorModal);
		vista.sacrificios.setControlador(controladorSacrificios);
		vista.cuidados.setControladorCuidados(controladorCuidados);
		vista.sensores.setControladorSensores(controladorSensores);
		vista.log.setControlador(controladorLog);
		vista.informes.setControlador(controladorInformes);
		vista.sigProceso.setControlador(controladorSigProceso);
		vista.registroUsuarios.setControlador(controladorRegistroUsuarios);

		vista.setVisible(true);
		splash.hide();
	}

	private static void login() {
		ConexionBDSingleton.host = "LOCALHOST";
		ConexionBDSingleton.port = "1433";
		ConexionBDSingleton.databaseName = "CORRALESTERNERO";
		ConexionBDSingleton.user = "sa";
		ConexionBDSingleton.pwd = "123";
//		ConexionBDSingleton.host = JOptionPane.showInputDialog("Host: ");
//		ConexionBDSingleton.port = JOptionPane.showInputDialog("Puerto: ");
//		ConexionBDSingleton.databaseName = JOptionPane.showInputDialog("Nombre de la base de datos: ");
//		ConexionBDSingleton.user = JOptionPane.showInputDialog("User: ");
//		ConexionBDSingleton.pwd = JOptionPane.showInputDialog("Password: ");

		if (ConexionBDSingleton.getConexion() == null) {
			JOptionPane.showMessageDialog(null, "ERROR AL CONECTARSE A LA BASE DE DATOS", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void moo() {
		new Thread(() -> {
			try {
				Player moo = new Player(new FileInputStream("Resources\\moo.mp3"));
				moo.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static VentanaPrincipal getInstance() { // Para centrar el Toast respecto a la posicion
		return vista;
	}
}
