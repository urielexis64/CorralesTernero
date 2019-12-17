package Controlador;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import EjecutarApp.Ejecutar;
import Modelo.ConexionBDSingleton;
import Modelo.ModeloLoginUsuario;
import Vista.VentanaPrincipal;

public class ControladorTitleBar implements ActionListener, ChangeListener {

	private VentanaPrincipal vista;
	private ModeloLoginUsuario modelo;
	
	public ControladorTitleBar(VentanaPrincipal vista, ModeloLoginUsuario modelo) {
		this.vista = vista;
		this.modelo=modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "info":
			showMessage();
			break;
		case "minimizar":
			vista.setState(Frame.ICONIFIED);
			break;
		case "cerrar":
			if (JOptionPane.showConfirmDialog(vista, "¿Esta seguro de cerrar la aplicación?") != 0)
				return;
			modelo.cerrarSesion(Ejecutar.ID_USUARIO);
			ConexionBDSingleton.cierraConexion();
			System.exit(0);
		}
	}

	private void showMessage() {
		JOptionPane.showMessageDialog(vista, "<html>" + "<center>Realizado por: </center>"
				+ "<div style='color: #fffff0; h2 {margin-left: 5px;}'><h2>Uriel Alexis Aispuro Sánchez</h2></div><br> "
				+ "<h3><center>Versión 0.3.1</center></h3><br><h3><center>21/09/2019</center></h3>" + "</html>",
				"Acerca de...", -1);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane tab = (JTabbedPane) e.getSource();

		vista.setSize(vista.getWidth(), 800);

		switch (tab.getSelectedIndex()) {
		case 1:
			vista.clasificacion.btnRefrescar.doClick();
			break;
		case 2:
			vista.consulta.btnRefrescar.doClick();
			break;
		case 3:
			vista.cuidados.btnRefrescar.doClick();
			break;
		case 4:
			vista.sacrificios.btnRefrescar.doClick();
			break;
		case 5:
			vista.sensores.estado.btnRefrescar.doClick();
			break;
		case 6:
			vista.log.btnRefrescar.doClick();
			break;
		case 7:
			vista.setSize(vista.getWidth(), 270);
			break;
		case 8:
			vista.sigProceso.btnSig.doClick();
		}
	}
}
