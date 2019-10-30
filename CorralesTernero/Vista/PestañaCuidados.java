package Vista;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controlador.ControladorCuidados;
import EjecutarApp.JCheckBoxColumn;
import EjecutarApp.ModeloTabla;
import material.componentes.MaterialButton;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;

public class PestañaCuidados extends JPanel {
	private JLabel lblTitulo;
	public JTable tabla;
	private JCheckBoxColumn modeloTabla;
	private JScrollPane scrollPane;
	private MaterialButton btnGuardar;
	
	public PestañaCuidados() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		
		lblTitulo = new JLabel("Estado de salud de las crías");
		lblTitulo.setFont(Roboto.BLACK.deriveFont(24f));
		lblTitulo.setBounds(50, 20, 400, 50);
		
		hazTabla();
		
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(50, 100, 400, 400);
		
		btnGuardar = new MaterialButton();
		btnGuardar.setFont(MaterialIcons.ICON_FONT.deriveFont(35f));
		btnGuardar.setText(String.valueOf(MaterialIcons.SAVE));
		btnGuardar.setBounds(455, 90, 80, 80);
		
		add(lblTitulo);
		add(scrollPane);
		add(btnGuardar);
	}

	private void hazTabla() {
		modeloTabla = new JCheckBoxColumn();
		tabla = new JTable(modeloTabla);

		modeloTabla.addColumn("ID CRIA");
		modeloTabla.addColumn("ENFERMA");
	}

	public void setTabla(Vector<Vector<Object>> objetoCria) {

//		limpiarTabla();

		Vector<Object> nuevaCria;
		for (int i = 0; i < objetoCria.size(); i++) {
			nuevaCria = new Vector<Object>();

			nuevaCria.add(objetoCria.get(i).get(0)); // ID
			nuevaCria.add(objetoCria.get(i).get(1).equals("1") ? false : true);

			modeloTabla.addRow(nuevaCria);
		}

	}
	
	public void setControladorCuidados(ControladorCuidados controlador) {
		btnGuardar.addActionListener(controlador);
	}
}
