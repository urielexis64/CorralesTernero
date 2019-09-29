package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import Modelo.ModeloConsulta;
import Vista.VentanaPrincipal;

public class ControladorConsulta implements ActionListener, CaretListener, ItemListener, DateChangeListener {
	private VentanaPrincipal vista;
	private ModeloConsulta modelo;

	public ControladorConsulta(VentanaPrincipal vista, ModeloConsulta modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTabla();
	}

	public void llenaTabla() {
		vista.consulta.setTabla(modelo.getTotalCrias());
		vista.consulta.lblNumeroCrias.setText("Total de crías: " + modelo.getNumCrias());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		llenaTabla(); // Boton refrescar
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		JTextField txt = (JTextField) e.getSource();

		String valor = "", atributo = "";

		if (!txt.getText().equals("")) {
			try {
				valor = txt.getText(); // Valor en la caja de texto Buscar
				atributo = vista.consulta.comboBox.getSelectedItem().toString(); // Valor del ComboBox

				vista.consulta.setTablaBusqueda(modelo.getConsultaCrias(valor, atributo));
				// vista.consulta.setTablaBusqueda(modelo.getConsultaCriasNo(valor, atributo));
				// Inyección SQL
			} catch (Exception ex) {
				System.out.println("Error al consultar: " + ex.getMessage());
			}

		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		vista.consulta.txtBuscar.setText(""); // Limpiar texto para evitar búsquedas erróneas

		if (e.getItem().equals("COLOR_MUSCULO")) {
			vista.consulta.txtBuscar.setEnabledRegex(false);
			vista.consulta.calendario.setEnabled(false);
			vista.consulta.txtBuscar.setEnabled(true);
		} else if (e.getItem().equals("FECHA_ENTRADA")) {
			vista.consulta.calendario.setEnabled(true);
			vista.consulta.txtBuscar.setEnabled(false);
		} else {
			vista.consulta.txtBuscar.setEnabledRegex(true);
			vista.consulta.calendario.setEnabled(false);
			vista.consulta.txtBuscar.setEnabled(true);
		}
	}

	@Override
	public void dateChanged(DateChangeEvent arg0) {
		vista.consulta.txtBuscar.setText(vista.consulta.calendario.getText());
	}
}
