package Controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import Modelo.ModeloConsulta;
import Vista.VentanaPrincipal;

public class ControladorConsulta implements ActionListener, CaretListener, ItemListener, DateChangeListener {
	private VentanaPrincipal vista;
	private ModeloConsulta modelo;
	private static boolean asc = true;

	public ControladorConsulta(VentanaPrincipal vista, ModeloConsulta modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTabla("id_cria", asc);
	}

	public void llenaTabla(String ordenamiento, boolean ascendente) {
		vista.consulta.setTabla(modelo.getTotalCrias(ordenamiento, ascendente));
		vista.consulta.lblNumeroCrias.setText("Total de crías: " + modelo.getNumCrias());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JToggleButton) {
			JToggleButton btn = (JToggleButton) e.getSource();
			if (btn.isSelected()) {
				modelo.vivas = true;
				btn.setForeground(Color.GREEN);
				btn.setText("CRÍAS VIVAS");
			} else {
				modelo.vivas = false;
				btn.setForeground(Color.RED);
				btn.setText("CRÍAS SACRIFICADAS");
			}
			if (!vista.consulta.txtBuscar.getText().equals("")) {
				vista.consulta.txtBuscar.setText(vista.consulta.txtBuscar.getText()); // genera evento de caretUpdate y
																						// filtra tabla
				return;
			}
		}
		if (e.getSource() == vista.consulta.btnRefrescar) {
			llenaTabla(vista.consulta.comboBox.getSelectedItem().toString(), asc); // Boton refrescar
			return;
		}

		llenaTabla(vista.consulta.comboBox.getSelectedItem().toString(), asc ? false : true);
		vista.consulta.btnOrdenar.setForeground(asc ? Color.RED : Color.GREEN);
		asc = !asc;
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		JTextField txt = (JTextField) e.getSource();

		String valor = "", atributo = "";

		if (!txt.getText().equals("")) {
			valor = txt.getText(); // Valor en la caja de texto Buscar
			atributo = vista.consulta.comboBox.getSelectedItem().toString(); // Valor del ComboBox

			Vector<Vector<String>> resultado = modelo.getConsultaCrias(valor, atributo);

			if (resultado != null)
				vista.consulta.setTablaBusqueda(resultado);
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