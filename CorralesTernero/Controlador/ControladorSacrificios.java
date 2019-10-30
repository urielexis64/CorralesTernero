package Controlador;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;

import Modelo.ModeloSacrificios;
import Vista.VentanaPrincipal;

public class ControladorSacrificios extends AbstractAction {

	private VentanaPrincipal vista;
	private ModeloSacrificios modelo;

	public ControladorSacrificios(VentanaPrincipal vista, ModeloSacrificios modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTabla();
	}

	private void llenaTabla() {
		vista.sacrificios.setTabla(modelo.getCriasSacrificar());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTable tabla = (JTable) e.getSource();
		String idCria = tabla.getValueAt(tabla.getSelectedRow(), 0) + "";
		System.out.println("ID " + idCria);
	}

}
