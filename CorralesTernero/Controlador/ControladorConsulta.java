package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Modelo.ModeloConsulta;
import Vista.VentanaPrincipal;

public class ControladorConsulta implements ActionListener, CaretListener, ItemListener {
	private VentanaPrincipal vista;
	private ModeloConsulta modelo;

	public ControladorConsulta(VentanaPrincipal vista, ModeloConsulta modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTabla();
	}

	public void llenaTabla() {
		vista.consulta.setTabla(modelo.getTotalCrias());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		llenaTabla();
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		JTextField txt = (JTextField) e.getSource();

		String valor = "", atributo = "";

		if (!txt.getText().equals("")) {
			try {
				valor = txt.getText(); // Valor en la caja de texto Buscar
				atributo = vista.consulta.comboBox.getSelectedItem().toString(); // Valor del ComboBox

				vista.consulta.setTablaBusqueda(modelo.getCria(valor, atributo));
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.out.println(valor + " " + atributo);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem().equals("COLOR_MUSCULO"))
			vista.consulta.txtBuscar.setEnabledRegex(false);
		else
			vista.consulta.txtBuscar.setEnabledRegex(true);
	}
}
