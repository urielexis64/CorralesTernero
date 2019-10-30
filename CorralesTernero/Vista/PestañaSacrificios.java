package Vista;

import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSacrificios;
import EjecutarApp.ButtonColumn;
import EjecutarApp.ModeloTabla;
import material.fonts.Roboto;

public class PestañaSacrificios extends JPanel {

	private JLabel lblTitulo;
	private JTable tabla;
	private ModeloTabla modeloTabla;
	private JScrollPane scrollTabla;

	public PestañaSacrificios() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		lblTitulo = new JLabel("Crías listas para sacrificar");
		lblTitulo.setFont(Roboto.BLACK.deriveFont(28f));
		lblTitulo.setBounds(50, 20, 400, 50);

		hazTabla();

		scrollTabla = new JScrollPane(tabla);
		scrollTabla.setBounds(50, 100, 600, 300);

		add(lblTitulo);
		add(scrollTabla);
	}

	private void hazTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);
		defineColumnas();
	}
	
	public void setControlador(ControladorSacrificios controlador) {
		ButtonColumn buttonColumn = new ButtonColumn(tabla, controlador, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}

	public void setTabla(Vector<Vector<Object>> objetoCria) {
		limpiarTabla();
		
		Vector<Object> nuevaCria;
		for (int i = 0; i < objetoCria.size(); i++) {
			nuevaCria = new Vector<Object>();
			
			nuevaCria.add(objetoCria.get(i).get(0)); // ID
			nuevaCria.add(objetoCria.get(i).get(1)); // CLASIFICACIÓN
			nuevaCria.add(objetoCria.get(i).get(2)); // FECHA_ENTRADA
			nuevaCria.add(objetoCria.get(i).get(3)); // VECES CUARENTENA
			nuevaCria.add("CUELLO");
			
			modeloTabla.addRow(nuevaCria);
		}
	}

	private void defineColumnas() {
		modeloTabla.addColumn("<html><h2>ID Cría");
		modeloTabla.addColumn("<html><h4>Clasificación");
		modeloTabla.addColumn("<html><h4>Fecha de entrada");
		modeloTabla.addColumn("<html><h4>Veces en cuarentena");
		modeloTabla.addColumn("Sacrificar");
	}

	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
	}

}
