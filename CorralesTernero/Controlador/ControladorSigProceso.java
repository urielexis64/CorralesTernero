package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.ModeloSigProceso;
import Vista.VentanaPrincipal;

public class ControladorSigProceso implements ActionListener {

	private VentanaPrincipal vista;
	private ModeloSigProceso modelo;

	public ControladorSigProceso(VentanaPrincipal vista, ModeloSigProceso modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTabla();
	}

	private void llenaTabla() {
		vista.sigProceso.setTabla(modelo.getCriasSigProceso());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		llenaTabla();
	}

}
