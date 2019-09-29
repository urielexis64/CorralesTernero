package Controlador;

import java.awt.event.*;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;

import Modelo.ModeloEliminaCria;
import Vista.ModalEditarCria;
import Vista.VentanaPrincipal;
import misHerramientas.AccionComponente;

public class ControladorSeleccionTabla extends MouseAdapter implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloEliminaCria modelo;
	private static JTable tabla;
	private Timer timer;

	public ControladorSeleccionTabla(VentanaPrincipal vista, ModeloEliminaCria modelo) {
		this.vista = vista;
		this.modelo = modelo;
		timer = new Timer(5000, this);
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
		if (e.getClickCount() == 2 && tabla.getColumnCount() == 5) { // Editar cr�a desde doble click
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

		if (e.getSource() == vista.consulta.btnVaciar) { // Botón eliminar toda la tabla
			String mensaje = "�Est� seguro de vaciar COMPLETAMENTE la tabla?";

			if (JOptionPane.showConfirmDialog(vista, mensaje, "Aviso", JOptionPane.YES_NO_OPTION) == 0) {
				int tuplasEliminadas = modelo.vaciarTabla();

				if (tuplasEliminadas == -1) {
					vista.consulta.showMessage("Hubo un error...", true);
				} else {
					vista.consulta.showMessage("Se eliminaron " + tuplasEliminadas + " tuplas.", false);
					vista.consulta.btnUndo.setVisible(true);
					timer.start();
					AccionComponente.disco(vista.consulta.btnUndo, 5000, 100);
				}
				vista.consulta.btnRefrescar.doClick();
			}
			return;
		}

		if (e.getSource() == timer) {
			vista.consulta.btnUndo.setVisible(false);
			modelo.commitTransaccion(true); // Si no presiona el boton UNDO
			timer.stop();
			return;
		}

		timer.stop(); // Si se presiona el boton UNDO
		modelo.commitTransaccion(false);
		vista.consulta.btnUndo.setVisible(false);
		vista.consulta.showMessage("Se recuperaron las tuplas.", false);
		vista.consulta.btnRefrescar.doClick();
	}
}
