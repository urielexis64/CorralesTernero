package Controlador;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import Vista.VentanaPrincipal;

public class ControladorTitleBar implements ActionListener {

	private VentanaPrincipal vista;

	public ControladorTitleBar(VentanaPrincipal vista) {
		this.vista = vista;
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
			System.exit(0);
		}
	}

	private void showMessage() {
		JOptionPane.showMessageDialog(vista, "<html>" + "<center>Realizado por: </center>"
				+ "<div style='color: #fffff0; h2 {margin-left: 5px;}'><h2>Uriel Alexis Aispuro Sánchez</h2></div><br> "
				+ "<h3><center>Versión 0.3.1</center></h3><br><h3><center>21/09/2019</center></h3>" + "</html>",
				"Acerca de...", -1);
		
	}
}
