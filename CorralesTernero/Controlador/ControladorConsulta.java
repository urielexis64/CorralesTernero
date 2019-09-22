package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Modelo.ModeloConsulta;
import Vista.VentanaPrincipal;

public class ControladorConsulta implements ActionListener, CaretListener {
	private VentanaPrincipal vista;
	private ModeloConsulta modelo;

	public ControladorConsulta(VentanaPrincipal vista, ModeloConsulta modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTabla();
	}

	public void llenaTabla() {
		vista.consulta.setTabla(modelo.getCrias());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		llenaTabla();
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (!txt.getText().equals(""))
			vista.consulta.setTabla(modelo.getCriaById(Integer.parseInt(txt.getText())));
	}
}
