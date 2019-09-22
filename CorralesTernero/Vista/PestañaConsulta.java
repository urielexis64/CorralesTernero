package Vista;

import java.awt.Font;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controlador.*;
import de.craften.ui.swingmaterial.MaterialButton;
import de.craften.ui.swingmaterial.MaterialButton.Type;
import de.craften.ui.swingmaterial.fonts.MaterialIcons;
import mdlaf.utils.MaterialColors;
import misHerramientas.IconTextField;

public class Pesta�aConsulta extends JPanel {
	private JTable tabla;
	private static ModeloTabla modeloTabla;
	private JScrollPane scrollTable;
	public MaterialButton btnRefrescar;

	public JPopupMenu menuFlotante;
	public JMenuItem itemEliminar, itemEditar;
	public IconTextField txtBuscar;

	public Pesta�aConsulta() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setType(Type.FLAT);
		btnRefrescar.setBorder(new mdlaf.shadows.RoundedCornerBorder(MaterialColors.LIGHT_BLUE_A100));
		btnRefrescar.setBounds(5, 15, 80, 80);

		iniciarTabla();
		scrollTable = new JScrollPane(tabla);
		scrollTable.setBorder(
				new mdlaf.shadows.DropShadowBorder(MaterialColors.GREEN_500, 2, 15, .5f, 10, true, true, true, true));
		scrollTable.setBounds(75, 60, 650, 550);

		menuFlotante = new JPopupMenu();
		itemEliminar = new JMenuItem("Eliminar cr�a");
		itemEditar = new JMenuItem("Editar cr�a");
		menuFlotante.add(itemEliminar);
		menuFlotante.addSeparator(); 
		menuFlotante.add(itemEditar);

		tabla.setComponentPopupMenu(menuFlotante);

		txtBuscar = new IconTextField("Resources\\search_icon.png", "Buscar");
		txtBuscar.setBounds(80, 10, 180, 30);

		add(txtBuscar);
		add(scrollTable);
		add(btnRefrescar);
	}

	private void iniciarTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);

		modeloTabla.addColumn("<html><div style='color: yellow; text-decoration: underline;'><h2>ID Cr�a</h2></div>");
		modeloTabla.addColumn("Peso");
		modeloTabla.addColumn("Color m�sculo");
		modeloTabla.addColumn("Porcentaje de grasa");

		tabla.setFont(new Font("Serif", Font.PLAIN, 16));
		tabla.setRowHeight(25); // Altura de cada columna
		tabla.getTableHeader().setReorderingAllowed(false); // Evitar mover columnas

		TableColumnModel columnas = tabla.getColumnModel();
		columnas.getColumn(0).setPreferredWidth(30);
	}

	public void setControlador(ControladorConsulta controlador) {
		btnRefrescar.addActionListener(controlador);
		txtBuscar.addCaretListener(controlador);
	}

	public void setControladorSeleccionTabla(ControladorSeleccionTabla controlador) {
		tabla.addMouseListener(controlador);
		itemEliminar.addActionListener(controlador);
	}

	public void setTabla(Vector<Vector<String>> objetoCria) {
		limpiarTabla();
		Vector<String> nuevaCria;
		for (int i = 0; i < objetoCria.size(); i++) {
			nuevaCria = new Vector<String>();
			nuevaCria.add(objetoCria.get(i).get(0)); // ID
			nuevaCria.add(objetoCria.get(i).get(1)); // Peso
			nuevaCria.add(objetoCria.get(i).get(2)); // Color
			nuevaCria.add(objetoCria.get(i).get(3)); // Grasa
			modeloTabla.addRow(nuevaCria);
		}
	}

	public void setTabla(Object[] objetoCria) {
		limpiarTabla();
		if (objetoCria == null) {
			((DefaultTableModel) tabla.getModel()).setColumnCount(0);
			((DefaultTableModel) tabla.getModel()).addColumn("Mensaje");
			modeloTabla.addRow(new String[] { "NO SE ENCONTRARON RESULTADOS" });
			return;
		}
		modeloTabla.addRow(objetoCria);
	}

	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
		if (((DefaultTableModel) tabla.getModel()).getColumnCount() != 4) {
			((DefaultTableModel) tabla.getModel()).setColumnCount(0);
			modeloTabla
					.addColumn("<html><div style='color: yellow; text-decoration: underline;'><h2>ID Cr�a</h2></div>");
			modeloTabla.addColumn("Peso");
			modeloTabla.addColumn("Color m�sculo");
			modeloTabla.addColumn("Porcentaje de grasa");
			TableColumnModel columnas = tabla.getColumnModel();

			columnas.getColumn(0).setPreferredWidth(30);
		}
		SwingUtilities.updateComponentTreeUI(scrollTable);
	}
}

class ModeloTabla extends DefaultTableModel {

	public boolean isCellEditable(int row, int co) {
		return false;
	}

}
