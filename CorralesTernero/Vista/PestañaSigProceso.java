package Vista;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import extras.ModeloTabla;
import material.componentes.MaterialButton;
import material.fonts.Roboto;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaSigProceso extends JPanel{

	public JTable tabla;
	private ModeloTabla modeloTabla;
	private JScrollPane scrollTabla;
	private JLabel lblTitulo;
	private MaterialButton btnSig;
	
	public PestañaSigProceso() {
		hazInterfaz();
		
		lblTitulo = new JLabel("Siguiente proceso");
		lblTitulo.setFont(Roboto.BLACK.deriveFont(28f));
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));
		lblTitulo.setBounds(50, 20, 380, 50);
		
		scrollTabla= new JScrollPane(tabla);
		scrollTabla.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollTabla.setBounds(35, 100, 700, 300);
		
		add(lblTitulo);
		add(scrollTabla);
	}

	private void hazInterfaz() {
		setLayout(null);
		iniciarTabla();
		
	}
	
	private void iniciarTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);
		defineColumnas();
		
		tabla.getTableHeader().setReorderingAllowed(false); // Evitar mover columnas
		tabla.setAutoCreateRowSorter(true); // Ordena columnas por orden alfabético
	}
	
	public void setTabla(Vector<Vector<String>> objetoCria) {
		limpiarTabla();

		Vector<String> nuevaCria;
		for (int i = 0; i < objetoCria.size(); i++) {
			nuevaCria = new Vector<String>();

			nuevaCria.add(objetoCria.get(i).get(0)); // ID_CRIA
			nuevaCria.add(objetoCria.get(i).get(1)); // CLASIFICACIÓN
			nuevaCria.add(objetoCria.get(i).get(2)); // FECHA_ENTRADA
			
			int dias =Integer.parseInt(objetoCria.get(i).get(3));
			
			int meses = dias /30;
			int nuevosDias = dias%30;
			
			nuevaCria.add(meses+" meses y "+nuevosDias+" días"); // TIEMPO
			nuevaCria.add(objetoCria.get(i).get(4)); // VECES_CUARENTENA
			
			modeloTabla.addRow(nuevaCria);
		}
	}
	

	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
	}
	
	private void defineColumnas() {
		((DefaultTableModel) tabla.getModel()).setColumnCount(0); // Borramos las columnas que haya (en caso de)
		modeloTabla.addColumn("<HTML><h2 style= text-decoration:underline>ID Cría");
		modeloTabla.addColumn("Clasificación");
		modeloTabla.addColumn("Fecha de entrada");
		modeloTabla.addColumn("Tiempo");
		modeloTabla.addColumn("Veces en cuarentena");
	}
	
}
