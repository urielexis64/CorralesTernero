package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Modelo.ModeloLog;
import Vista.VentanaPrincipal;

public class ControladorLog implements ActionListener {

	private VentanaPrincipal vista;
	private ModeloLog modelo;

	public ControladorLog(VentanaPrincipal vista, ModeloLog modelo) {
		this.vista = vista;
		this.modelo = modelo;
		vista.log.setTabla(modelo.getMovimientos(-1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			vista.log.setTabla(modelo.getMovimientos(-1));
			return;
		}
		vista.log.setTabla(modelo.getMovimientos(Integer.parseInt(vista.log.txtBuscar.getText())));
	}

}
