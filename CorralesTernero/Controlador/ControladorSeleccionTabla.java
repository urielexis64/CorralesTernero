package Controlador;

import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Modelo.ModeloEliminaCria;
import Vista.VentanaPrincipal;

public class ControladorSeleccionTabla extends MouseAdapter implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloEliminaCria modelo;
	private static JTable tabla;

	public ControladorSeleccionTabla(VentanaPrincipal vista, ModeloEliminaCria modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (tabla == null) // Guardar referencia de la tabla solo una vez
			tabla = (JTable) e.getSource();
		vista.consulta.itemEliminar.setText("Eliminar cría (ID = " + tabla.getValueAt(tabla.getSelectedRow(), 0) + ")");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		if (JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar a la cría con el ID #" + id) == 0) {
			modelo.eliminaCria(id);
			vista.consulta.btnRefrescar.doClick();
		}
	}
}
