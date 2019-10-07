package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.ModeloActualiza;
import Vista.ModalActualiza;
import Vista.VentanaPrincipal;

public class ControladorModal implements ActionListener {

	private VentanaPrincipal vista;
	private ModeloActualiza modelo;

	private ModalActualiza modal;

	public ControladorModal(VentanaPrincipal vista, ModeloActualiza modelo) {
		this.vista = vista;
		this.modelo = modelo;
		modal = vista.consulta.modal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == modal.btnLimpiar) {
			modal.limpiar();
			return;
		}

		if (!modal.verificarCampos()) {
			modal.showMessage("Llene todos los campos.", true);
			return;
		}

		int grasa = Integer.parseInt(modal.txtGrasaCria.getText());
		if (grasa > 100) {
			modal.showMessage("El porcentaje de grasa no puede ser mayor al 100 %", true);
			modal.txtGrasaCria.requestFocus();
			modal.txtGrasaCria.selectAll();
			return;
		}

		float peso = Float.parseFloat(modal.txtPesoCria.getText());
		String color = modal.txtColorCria.getText();

		if (modelo.actualizaCria(modal.idActual, peso, color, grasa)) {
			modal.showMessage("Actualizado con éxito", false);
			modal.setVisible(false);
			vista.consulta.btnRefrescar.doClick();
		} else {
			modal.showMessage("Hubo un error...", true);
		}
	}
}
