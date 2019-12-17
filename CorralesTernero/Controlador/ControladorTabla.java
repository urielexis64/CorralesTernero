package Controlador;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;

import Modelo.ModeloEliminaCria;
import Vista.VentanaPrincipal;
import material.extras.AccionComponente;
import mdlaf.utils.MaterialColors;

public class ControladorTabla extends MouseAdapter implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloEliminaCria modelo;
	private JTable tabla;
	private Timer timer;
	private Color colorTabla;

	public ControladorTabla(VentanaPrincipal vista, ModeloEliminaCria modelo) {
		this.vista = vista;
		this.modelo = modelo;
		timer = new Timer(1000, this);
	}

	private boolean seleccionaPunto(Point punto) {
		int r = tabla.rowAtPoint(punto);
		if (r >= 0) {
			tabla.setRowSelectionInterval(r, r);
			return true;
		}
		return false;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && tabla.getColumnCount() == 5) // Editar cría desde doble click
			vista.consulta.editarCria();
	}

	public void mousePressed(MouseEvent e) {
		if (tabla == null) {
			tabla = (JTable) e.getSource();
			colorTabla = tabla.getSelectionBackground();
		}

		if (!tabla.isEnabled())
			return;

		if (e.isControlDown() && e.getButton() == MouseEvent.BUTTON3) {
			seleccionaPunto(e.getPoint());
			tabla.setSelectionBackground(MaterialColors.RED_500);
		} else
			tabla.setSelectionBackground(colorTabla);
	}

	public void mouseReleased(MouseEvent e) {
		tabla.setSelectionBackground(colorTabla);

		if (!tabla.isEnabled())
			return;

		if (e.isControlDown() && e.isPopupTrigger()) { // Atajo para borrar sin mensaje emergente
			Point p = new Point(MouseInfo.getPointerInfo().getLocation().x - tabla.getLocationOnScreen().x,
					MouseInfo.getPointerInfo().getLocation().y - tabla.getLocationOnScreen().y);

			int filaSeleccionada = tabla.getSelectedRow();

			if (!seleccionaPunto(p) || tabla.getSelectedRow() != filaSeleccionada) // Si hay una fila ubicada en el
																					// mismo punto que la ubicación
																					// actual del mouse, la selecciona,
																					// si no, no selecciona nada y se
																					// corta el flujo
				return;
			int id = Integer.parseInt((tabla.getValueAt(tabla.getSelectedRow(), 0) + ""));
			modelo.eliminaCriaById(id);
			vista.consulta.btnRefrescar.doClick();
			vista.consulta.showMessage("Cría #" + id + " eliminada", false);
			return;
		}

		seleccionaPunto(e.getPoint());

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
				vista.consulta.editarCria(); // Editar cría (Menú emergente)
				return;
			}

			int id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()); // Si es el item Eliminar
			if (JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar a la cría con el ID #" + id+"?") == 0) {
				modelo.eliminaCriaById(id);
				vista.consulta.btnRefrescar.doClick();
				vista.consulta.showMessage("Cría eliminada", false);
			}
			return;
		}

//		if (e.getSource() == vista.consulta.btnVaciar) { // Botón eliminar toda la tabla
//			String mensaje = "¿Está seguro de vaciar COMPLETAMENTE la tabla?";
//
//			if (JOptionPane.showConfirmDialog(vista, mensaje, "Aviso", JOptionPane.YES_NO_OPTION) == 0) {
//
//				if (!modelo.vaciarTabla()) {
//					vista.consulta.showMessage("Hubo un error...", true);
//				} else {
//					String tuplasEliminadas = vista.consulta.lblNumeroCrias.getText().split(":")[1];
//					vista.consulta.showMessage("Se eliminaron " + tuplasEliminadas + " tuplas.", false);
//					vista.consulta.btnUndo.setVisible(true);
//					timer.start();
//					vista.consulta.lblSegundosTransaccion.setVisible(true);
//					AccionComponente.disco(vista.consulta.btnUndo, 6000, 100);
//				}
//				vista.consulta.btnRefrescar.doClick();
//			}
//			return;
//		}
//
//		if (e.getSource() == timer) {
//			if (contador > 0) {
//				vista.consulta.lblSegundosTransaccion.setText(--contador + "");
//				return;
//			}
//			terminarTransaccion(true); // Hace commit
//			return;
//		}
//
//		terminarTransaccion(false); // Hace rollback
	}

//	private void terminarTransaccion(boolean commit) {
//		timer.stop();
//		modelo.commitTransaccion(commit); // Si no presiona el boton UNDO
//		vista.consulta.btnUndo.setVisible(false);
//		vista.consulta.lblSegundosTransaccion.setText("5");
//		vista.consulta.lblSegundosTransaccion.setVisible(false);
//
//		if (!commit) { // Si fue rollback, actualiza la tabla
//			vista.consulta.btnRefrescar.doClick();
//			vista.consulta.showMessage("Se recuperaron las tuplas.", false);
//		}
//		contador = 5; // Se resetea el contador
//	}
//
//	private int contador = 5;

}
