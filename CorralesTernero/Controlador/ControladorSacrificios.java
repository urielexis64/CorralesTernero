package Controlador;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Modelo.ModeloLog;
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
		if (e.getSource() instanceof JButton) {
			llenaTabla();
			return;
		}

		JTable tabla = (JTable) e.getSource();
		int idCria = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0) + "");

		if (JOptionPane.showConfirmDialog(vista, "¿Está seguro de dar de baja a la cría #" + idCria + "?") == 0) {
			if (modelo.cuelloCria(idCria)) {
				vista.sacrificios.showMessage("Cría dada de baja con éxito", false);
				ModeloLog.registraMovimiento(idCria, "Fue sacrificada");
			}else
				vista.sacrificios.showMessage("Hubo un error...", true);
			llenaTabla();
		}
	}
}
