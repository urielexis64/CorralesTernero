package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.ModeloClasificacion;
import Vista.VentanaPrincipal;

public class ControladorClasificacion implements ActionListener  {

	private VentanaPrincipal vista;
	private ModeloClasificacion modelo;

	public ControladorClasificacion(VentanaPrincipal vista, ModeloClasificacion modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTablas();
	}
	

	public void llenaTablas() {
		vista.clasificacion.limpiarTabla();
		vista.clasificacion.setTabla(modelo.getCriasTabla());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		llenaTablas();
	}
		
}
