package Vista;

import java.awt.Font;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import Controlador.*;
import EjecutarApp.ToastMessage;
import de.craften.ui.swingmaterial.MaterialButton;
import de.craften.ui.swingmaterial.MaterialButton.Type;
import de.craften.ui.swingmaterial.MaterialComboBox;
import de.craften.ui.swingmaterial.MaterialProgressSpinner;
import de.craften.ui.swingmaterial.fonts.MaterialIcons;
import mdlaf.utils.MaterialColors;
import misHerramientas.IconTextField;
import misHerramientas.Rutinas;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.shadows.RoundedCornerBorder;

public class PestañaConsulta extends JPanel {
	private JTable tabla;
	private static ModeloTabla modeloTabla;
	private JScrollPane scrollTable;

	public MaterialButton btnRefrescar, btnVaciar, btnUndo;
	public JLabel lblNumeroCrias;

	public JPopupMenu menuFlotante;
	public JMenuItem itemEliminar, itemEditar;

	public IconTextField txtBuscar;
	public MaterialComboBox<String> comboBox;
	public DatePicker calendario;

	public PestañaConsulta() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		comboBox = new MaterialComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "ID_CRIA", "PESO", "COLOR_MUSCULO", "PORCENTAJE_GRASA", "FECHA_ENTRADA" }));
		comboBox.setBounds(290, 16, 180, 45);
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

		btnUndo = new MaterialButton();
		btnUndo.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnUndo.setText(String.valueOf(MaterialIcons.UNDO));
		btnUndo.setType(Type.FLAT);
		btnUndo.setBorder(new DropShadowBorder());
		btnUndo.setBounds(5, 220, 80, 80);
		btnUndo.setVisible(false);

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
		scrollTable.setBounds(75, 60, 700, 550);

		txtBuscar = new IconTextField("Resources\\search_icon.png", "Buscar", 20);
		txtBuscar.setBounds(90, 25, 185, 30);

		lblNumeroCrias = new JLabel("Total de crías: ");
		lblNumeroCrias.setFont(new Font("Roboto", Font.BOLD, 16));
		lblNumeroCrias.setBorder(new RoundedCornerBorder(MaterialColors.DARKLY_BLUE));
		lblNumeroCrias.setBounds(610, 15, 150, 40);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setVisibleDateTextField(false);
		dateSettings.setGapBeforeButtonPixels(0); // Redondez de las esquinas
		dateSettings.setFormatForDatesCommonEra("dd-MM-uuuu");
		calendario = new DatePicker(dateSettings);
		calendario.getComponentToggleCalendarButton().setIcon(Rutinas.AjustarImagen("Resources\\calendar.png", 20, 20));
		calendario.getComponentToggleCalendarButton().setText("");
		calendario.setEnabled(false);
		calendario.getComponentToggleCalendarButton()
				.setDisabledIcon(Rutinas.AjustarImagen("Resources\\calendar.png", 20, 20));
		calendario.setBounds(480, 15, 45, 45);

		add(btnRefrescar);
		add(btnVaciar);
		add(txtBuscar);
		add(scrollTable);
		add(comboBox);
		add(calendario);
		add(btnUndo);
		add(lblNumeroCrias);
	}

	private void iniciarTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);

		modeloTabla.addColumn("<html><div style='color: yellow; text-decoration: underline;'><h2>ID Cría</h2></div>");
		modeloTabla.addColumn("Peso");
		modeloTabla.addColumn("Color músculo");
		modeloTabla.addColumn("Porcentaje de grasa");
		modeloTabla.addColumn("Fecha de entrada");

		// tabla.setComponentPopupMenu(menuFlotante); // Menú emergente
		tabla.getTableHeader().setReorderingAllowed(false); // Evitar mover columnas
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Evitar multiselección
		tabla.setAutoCreateRowSorter(true); // Ordena columnas por orden alfabético

		TableColumnModel columnas = tabla.getColumnModel();
		columnas.getColumn(0).setMaxWidth(85); // Cambiar ancho de la columna
		columnas.getColumn(1).setMaxWidth(85);
		columnas.getColumn(2).setMaxWidth(150);
		columnas.getColumn(3).setMaxWidth(170);
		columnas.getColumn(4).setMaxWidth(170);

	}

	public void setControlador(ControladorConsulta controlador) {
		btnRefrescar.addActionListener(controlador);
		txtBuscar.addCaretListener(controlador);
		comboBox.addItemListener(controlador);
		calendario.addDateChangeListener(controlador);
	}

	public void setControladorSeleccionTabla(ControladorSeleccionTabla controlador) {
		tabla.addMouseListener(controlador);
		itemEliminar.addActionListener(controlador);
		itemEditar.addActionListener(controlador);
		btnVaciar.addActionListener(controlador);
		btnUndo.addActionListener(controlador);
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
			nuevaCria.add(objetoCria.get(i).get(4)); // Fecha entrada
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
				nuevaCria.add(objetoCria.get(i).get(4)); // Fecha entrada
				modeloTabla.addRow(nuevaCria);
			}
		}
	}

	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
		if (((DefaultTableModel) tabla.getModel()).getColumnCount() != 5) {
			((DefaultTableModel) tabla.getModel()).setColumnCount(0);
			modeloTabla
					.addColumn("<html><div style='color: yellow; text-decoration: underline;'><h3>ID Cría");
			modeloTabla.addColumn("Peso");
			modeloTabla.addColumn("Color músculo");
			modeloTabla.addColumn("Porcentaje de grasa");
			modeloTabla.addColumn("Fecha de entrada");

			tabla.setEnabled(true); // Permitir clicks en las filas

			TableColumnModel columnas = tabla.getColumnModel();
			columnas.getColumn(0).setMaxWidth(85); // Cambiar ancho de la columna
			columnas.getColumn(1).setMaxWidth(85);
			columnas.getColumn(2).setMaxWidth(150);
			columnas.getColumn(3).setMaxWidth(170);
			columnas.getColumn(4).setMaxWidth(170);
		}
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage();
		if (error) {
			toast.setInfo(msg, MaterialColors.RED_500);
		} else {
			toast.setInfo(msg, MaterialColors.BLUE_500);
		}
		toast.showToast();
	}
}

class ModeloTabla extends DefaultTableModel {

	public boolean isCellEditable(int row, int co) {
		return false;
	}

}
