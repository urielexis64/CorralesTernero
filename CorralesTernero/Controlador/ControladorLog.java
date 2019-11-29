package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.ModeloLog;
import Vista.VentanaPrincipal;

public class ControladorLog implements ActionListener {

	private VentanaPrincipal vista;
	private ModeloLog modelo;

	public ControladorLog(VentanaPrincipal vista, ModeloLog modelo) {
		this.vista = vista;
		this.modelo = modelo;
		vista.log.setTabla(modelo.getMovimientos());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		vista.log.setTabla(modelo.getMovimientos());
	}

}
