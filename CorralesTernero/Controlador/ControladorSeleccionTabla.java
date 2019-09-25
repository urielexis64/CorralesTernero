package Controlador;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Modelo.ModeloEliminaCria;
import Vista.ModalEditarCria;
import Vista.VentanaPrincipal;

public class ControladorSeleccionTabla extends MouseAdapter implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloEliminaCria modelo;
	private static JTable tabla;

	public ControladorSeleccionTabla(VentanaPrincipal vista, ModeloEliminaCria modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	private void editarCria() {
		int id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		String peso = tabla.getValueAt(tabla.getSelectedRow(), 1).toString().split(" ")[0];
		String color = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
		String grasa = tabla.getValueAt(tabla.getSelectedRow(), 3).toString().split(" ")[0];

		ModalEditarCria modal = new ModalEditarCria();
		modal.setInfo(id, peso, color, grasa);
		modal.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && tabla.getColumnCount() == 4) { // Editar cría desde doble click
			editarCria();
			vista.consulta.btnRefrescar.doClick();
		}
	}

	public void mousePressed(MouseEvent e) {
		if (tabla == null) {
			tabla = (JTable) e.getSource();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (!tabla.isEnabled())
			return;

		int r = tabla.rowAtPoint(e.getPoint());
		if (r >= 0 && r < tabla.getRowCount()) {
			tabla.setRowSelectionInterval(r, r);
		} else {
			tabla.clearSelection();
		}

		int rowindex = tabla.getSelectedRow();
		if (rowindex < 0)
			return;
		if (e.isPopupTrigger()) {
			vista.consulta.itemEliminar
					.setText("Eliminar cría (ID = " + tabla.getValueAt(tabla.getSelectedRow(), 0) + ")");
			vista.consulta.menuFlotante.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) { // Eliminar cría (Menú emergente)

			if (((JMenuItem) e.getSource()).getName().equals("Editar")) {// Si es el item Editar
				editarCria(); // Editar cría (Menú emergente)
				return;
			}

			int id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()); // Si es el item Eliminar
			if (JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar a la cría con el ID #" + id) == 0) {
				modelo.eliminaCria(id);
				vista.consulta.btnRefrescar.doClick();
			}
			return;
		}
		if (e.getSource() instanceof JButton) { // Botón eliminar toda la tabla
			String t = "¿Está seguro de vaciar COMPLETAMENTE la tabla?";
			if (JOptionPane.showConfirmDialog(vista, t) == 0) {
				modelo.vaciarTabla();
				vista.consulta.btnRefrescar.doClick();
			}
			return;
		}
	}
}
