package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import Controlador.*;
import de.craften.ui.swingmaterial.MaterialButton;
import de.craften.ui.swingmaterial.MaterialButton.Type;
import de.craften.ui.swingmaterial.MaterialColor;
import de.craften.ui.swingmaterial.MaterialComboBox;
import de.craften.ui.swingmaterial.fonts.MaterialIcons;
import mdlaf.utils.MaterialColors;
import misHerramientas.IconTextField;
import mdlaf.shadows.DropShadowBorder;

public class PestañaConsulta extends JPanel {
	private JTable tabla;
	private static ModeloTabla modeloTabla;
	private JScrollPane scrollTable;
	public MaterialButton btnRefrescar, btnVaciar;

	public JPopupMenu menuFlotante;
	public JMenuItem itemEliminar, itemEditar;
	public IconTextField txtBuscar;

	public MaterialComboBox<String> comboBox;

	public PestañaConsulta() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		setFocusable(true);

		comboBox = new MaterialComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "ID_CRIA", "PESO", "COLOR_MUSCULO", "PORCENTAJE_GRASA" }));
		comboBox.setBounds(280, 16, 200, 45);
		comboBox.setAccent(MaterialColors.YELLOW_500);
		SwingUtilities.updateComponentTreeUI(comboBox); // Actualizar la apariencia (error)

		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setType(Type.RAISED);
		btnRefrescar.setBorder(new DropShadowBorder());
		btnRefrescar.setBounds(5, 70, 80, 80);

		btnVaciar = new MaterialButton();
		btnVaciar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnVaciar.setText(String.valueOf(MaterialIcons.DELETE_FOREVER));
		btnVaciar.setType(Type.RAISED);
		btnVaciar.setBorder(new DropShadowBorder());
		btnVaciar.setBounds(5, 140, 80, 80);

		menuFlotante = new JPopupMenu();
		itemEliminar = new JMenuItem("Eliminar cría");
		itemEliminar.setName("Eliminar");
		itemEditar = new JMenuItem("Editar cría");
		itemEditar.setName("Editar");
		menuFlotante.add(itemEliminar);
		menuFlotante.addSeparator();
		menuFlotante.add(itemEditar);

		iniciarTabla();
		scrollTable = new JScrollPane(tabla);
		scrollTable.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollTable.setBounds(75, 60, 650, 450);

		txtBuscar = new IconTextField("Resources\\search_icon.png", "Buscar", 20);
		txtBuscar.setBounds(90, 25, 185, 30);

		add(comboBox);
		add(txtBuscar);
		add(scrollTable);
		add(btnRefrescar);
		add(btnVaciar);
	}

	private void iniciarTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);

		modeloTabla.addColumn("<html><div style='color: yellow; text-decoration: underline;'><h2>ID Cría</h2></div>");
		modeloTabla.addColumn("Peso");
		modeloTabla.addColumn("Color músculo");
		modeloTabla.addColumn("Porcentaje de grasa");

		tabla.setFont(new Font("Serif", Font.PLAIN, 16));
		// tabla.setComponentPopupMenu(menuFlotante); // Menú emergente
		tabla.getTableHeader().setReorderingAllowed(false); // Evitar mover columnas
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Evitar multiselección
		tabla.setAutoCreateRowSorter(true);

		TableColumnModel columnas = tabla.getColumnModel();
		columnas.getColumn(0).setPreferredWidth(25); // Cambiar ancho de la columna
		columnas.getColumn(1).setPreferredWidth(25);
	}

	public void setControlador(ControladorConsulta controlador) {
		btnRefrescar.addActionListener(controlador);
		txtBuscar.addCaretListener(controlador);
		comboBox.addItemListener(controlador);
	}

	public void setControladorSeleccionTabla(ControladorSeleccionTabla controlador) {
		tabla.addMouseListener(controlador);
		itemEliminar.addActionListener(controlador);
		itemEditar.addActionListener(controlador);
		btnVaciar.addActionListener(controlador);
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

	public void setTablaBusqueda(Vector<Vector<String>> objetoCria) {
		limpiarTabla();
		if (objetoCria.size() == 0) {
			((DefaultTableModel) tabla.getModel()).setColumnCount(0);
			((DefaultTableModel) tabla.getModel()).addColumn("Mensaje");
			tabla.setEnabled(false); // Evitar clicks en la fila del mensaje
			modeloTabla.addRow(new String[] { "NO SE ENCONTRARON RESULTADOS" });
			return;
		} else {
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
	}

	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
		if (((DefaultTableModel) tabla.getModel()).getColumnCount() != 4) {
			((DefaultTableModel) tabla.getModel()).setColumnCount(0);
			modeloTabla
					.addColumn("<html><div style='color: yellow; text-decoration: underline;'><h2>ID Cría</h2></div>");
			modeloTabla.addColumn("Peso");
			modeloTabla.addColumn("Color músculo");
			modeloTabla.addColumn("Porcentaje de grasa");
			TableColumnModel columnas = tabla.getColumnModel();

			tabla.setEnabled(true); // Permitir clicks en las filas
			columnas.getColumn(0).setPreferredWidth(25);
			columnas.getColumn(1).setPreferredWidth(25);
		}
	}
}

class ModeloTabla extends DefaultTableModel {

	public boolean isCellEditable(int row, int co) {
		return false;
	}

}
