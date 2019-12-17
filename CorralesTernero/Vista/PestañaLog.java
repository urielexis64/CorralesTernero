package Vista;

import java.awt.Color;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controlador.ControladorLog;
import extras.ModeloTabla;
import material.componentes.MaterialButton;
import material.componentes.MaterialButton.Type;
import material.extras.IconTextField;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaLog extends JPanel {
	private JTable tabla;
	private ModeloTabla modeloTabla;
	private JScrollPane scrollTable;
	public MaterialButton btnRefrescar;
	private JLabel lblTitulo;
	public IconTextField txtBuscar;
	
	public PestañaLog() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		iniciarTabla();
		
		lblTitulo = new JLabel("Log", SwingConstants.CENTER);
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));
		lblTitulo.setFont(Roboto.BLACK.deriveFont(30f));
		lblTitulo.setBounds(45, 10, 70, 50);
		
		scrollTable = new JScrollPane(tabla);
		scrollTable.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollTable.setBounds(30, 60, 700, 450);
		
		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setType(Type.RAISED);
		btnRefrescar.setBorder(new DropShadowBorder());
		btnRefrescar.setBounds(710, 65, 80, 80);

		txtBuscar = new IconTextField("Resources\\search_icon.png", "Buscar por ID", 20);
		txtBuscar.setBounds(510, 25, 185, 30);
		
		add(btnRefrescar);
		add(lblTitulo);
		add(scrollTable);
		add(txtBuscar);
	}
	
	public void setControlador(ControladorLog controlador){
		btnRefrescar.addActionListener(controlador);
		txtBuscar.addActionListener(controlador);
	}

	private void iniciarTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);
		defineColumnas();
		defineAnchoColumnas(); // Cambio tamaño máximo de las columnas

		tabla.getTableHeader().setReorderingAllowed(false); // Evitar mover columnas
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Evitar multiselección
		tabla.setAutoCreateRowSorter(true); // Ordena columnas por orden alfabético
	}

	private void defineColumnas() {
		((DefaultTableModel) tabla.getModel()).setColumnCount(0); // Borramos las columnas que haya (en caso de)
		modeloTabla.addColumn("<HTML><h2 style= text-decoration:underline>ID Cría");
		modeloTabla.addColumn("Movimiento");
		modeloTabla.addColumn("Fecha");
		modeloTabla.addColumn("Realizado por:");
	}

	private void defineAnchoColumnas() {
		TableColumnModel columnas = tabla.getColumnModel();
		columnas.getColumn(0).setMaxWidth(100);
		columnas.getColumn(1).setMaxWidth(250);
		columnas.getColumn(2).setMaxWidth(210);
		columnas.getColumn(3).setMaxWidth(130);
	}
	
	public void setTabla(Vector<Vector<String>> objetoMovimiento){
		limpiarTabla();
		if(objetoMovimiento.isEmpty()) {
			((DefaultTableModel) tabla.getModel()).setColumnCount(0); // Borramos las columnas que haya (en caso de)
			modeloTabla.addColumn("MENSAJE");
			modeloTabla.addRow(new Object[] {"NO SE ENCONTRARON RESULTADOS."});
			return;
		}
		defineColumnas();
		defineAnchoColumnas();
		Vector<String> nuevoMovimiento;
		for (int i = 0; i < objetoMovimiento.size(); i++) {
			nuevoMovimiento = new Vector<String>();
			nuevoMovimiento.add(objetoMovimiento.get(i).get(0)); // ID
			nuevoMovimiento.add(objetoMovimiento.get(i).get(1)); // Descripción
			nuevoMovimiento.add(objetoMovimiento.get(i).get(2)); // Fecha
			nuevoMovimiento.add(objetoMovimiento.get(i).get(3)); // Usuario
			modeloTabla.addRow(nuevoMovimiento);
		}
	}
	
	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
	}
}
