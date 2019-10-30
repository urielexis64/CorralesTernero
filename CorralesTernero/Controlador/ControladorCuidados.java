package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.ModeloCuidados;
import Vista.VentanaPrincipal;

public class ControladorCuidados implements ActionListener{

	private VentanaPrincipal vista;
	private ModeloCuidados modelo;

	public ControladorCuidados(VentanaPrincipal vista, ModeloCuidados modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTabla();
	}
	
	private void llenaTabla() {
		vista.cuidados.setTabla(modelo.getCriasTabla());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	

}
