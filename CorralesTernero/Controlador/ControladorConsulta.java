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
	private boolean filtro;

	public ControladorConsulta(VentanaPrincipal vista, ModeloConsulta modelo) {
		this.vista = vista;
		this.modelo = modelo;
		filtro = false;
	}

	public void llenaTabla(boolean cambioEstado) {
		if (cambioEstado) {
			vista.consulta.lblPaginaActual.setText("Pág. " + (vista.consulta.pagActual = 1));
			modelo.inicio = 0;
		}
		if (filtro) {
			String valor = vista.consulta.txtBuscar.getText(); // Valor en la caja de texto Buscar
			String atributo = vista.consulta.comboBox.getSelectedItem().toString(); // Valor del ComboBox

			Vector<Vector<String>> resultado = modelo.getConsultaCrias(valor, atributo);

			if (resultado != null)
				vista.consulta.setTablaBusqueda(resultado);
			return;
		}
		vista.consulta.setTabla(modelo.getTotalCrias());
		int totalTuplas = modelo.getNumCrias();
		vista.consulta.btnSig.setEnabled(totalTuplas > 15 && modelo.inicio + 15 <= totalTuplas);
		vista.consulta.btnAnt.setEnabled(totalTuplas > 15 && modelo.inicio >= 15);
		vista.consulta.lblNumeroCrias.setText("Total de crías: " + totalTuplas);
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
			filtro=false;
			llenaTabla(true);
			return;
		}

		if (e.getSource() == vista.consulta.btnRefrescar) {
			filtro=false;
			llenaTabla(false); // Boton refrescar
			return;
		}

		if (e.getSource() == vista.consulta.btnSig) {
			modelo.inicio += 15;
			vista.consulta.lblPaginaActual.setText("Pág. " + ++vista.consulta.pagActual);
			vista.consulta.lblPaginaActual.revalidate();
			llenaTabla(false);
			return;
		}

		vista.consulta.lblPaginaActual.setText("Pág. " + --vista.consulta.pagActual);
		modelo.inicio -= 15;
		llenaTabla(false);
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		filtro = true;
		modelo.inicio = 0;
		vista.consulta.lblPaginaActual.setText("Pág. " + (vista.consulta.pagActual = 1));
		int totalTuplas = modelo.getNumCrias();
		vista.consulta.btnSig.setEnabled(totalTuplas > 15 && modelo.inicio + 15 <= totalTuplas);
		vista.consulta.btnAnt.setEnabled(totalTuplas > 15 && modelo.inicio >= 15);

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