package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import Modelo.ModeloCuidados;
import Vista.VentanaPrincipal;

public class ControladorCuidados implements ActionListener {

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
		if (e.getSource() == vista.cuidados.btnRefrescar) {
			vista.cuidados.setTabla(modelo.getCriasTabla());
			return;
		}

		Vector<Vector<String>> saludCrias = new Vector<Vector<String>>();

		for (int i = 0; i < vista.cuidados.tabla.getRowCount(); i++) {
			Vector<String> aux = new Vector<String>();

			String idActual = vista.cuidados.tabla.getValueAt(i, 0) + "";
			String estadoSalud = vista.cuidados.tabla.getValueAt(i, 1) + "";

			aux.add(idActual);
			aux.add(estadoSalud);

			saludCrias.add(aux);
		}

		if (modelo.actualizaSalud(saludCrias))
			vista.cuidados.showMessage("Cambios guardados con éxito.", false);
		else
			vista.cuidados.showMessage("Hubo un error...", true);
	}
}
