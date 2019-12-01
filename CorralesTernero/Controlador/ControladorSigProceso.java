package Controlador;

import Modelo.ModeloSigProceso;
import Vista.VentanaPrincipal;

public class ControladorSigProceso {
	
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
	
}
