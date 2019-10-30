package Vista;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controlador.ControladorCuidados;
import EjecutarApp.JCheckBoxColumn;
import EjecutarApp.ModeloTabla;

public class PestañaCuidados extends JPanel {
	private JLabel lblTitulo;
	private JTable tabla;
	private JCheckBoxColumn modeloTabla;
	private JScrollPane scrollPane;

	public PestañaCuidados() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		
		hazTabla();
		
		scrollPane= new JScrollPane(tabla);
		scrollPane.setBounds(300, 100, 300, 400);
		
		add(scrollPane);
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
		
	}
}
