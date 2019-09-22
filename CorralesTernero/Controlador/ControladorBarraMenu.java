package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vista.VentanaPrincipal;

public class ControladorBarraMenu implements ActionListener {

	private VentanaPrincipal vista;

	public ControladorBarraMenu(VentanaPrincipal vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		vista.barraMenu.showInfoMessage();
	}
}
