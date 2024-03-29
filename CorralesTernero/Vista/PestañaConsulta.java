package Vista;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;

import Controlador.*;
import EjecutarApp.Ejecutar;
import extras.ModeloTabla;
import material.componentes.MaterialButton;
import material.componentes.MaterialComboBox;
import material.componentes.MaterialButton.Type;
import material.extras.IconTextField;
import material.extras.Rutinas;
import material.extras.ToastMessage;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;
import mdlaf.utils.MaterialColors;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.shadows.RoundedCornerBorder;

public class Pesta�aConsulta extends JPanel {
	public JTable tabla;
	private ModeloTabla modeloTabla;
	private JScrollPane scrollTable;

	public MaterialButton btnRefrescar, btnSig, btnAnt;
	public JLabel lblNumeroCrias, lblPaginaActual, lblTitulo;

	public JPopupMenu menuFlotante;
	public JMenuItem itemEliminar, itemEditar;

	public IconTextField txtBuscar;
	public MaterialComboBox<String> comboBox;
	public DatePicker calendario;

	public ModalActualiza modal;
	
	public JToggleButton btnEstado;
	public int pagActual;

	public Pesta�aConsulta() {
		pagActual=1;
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		iniciarTabla();
		modal = new ModalActualiza();

		lblTitulo = new JLabel("Consulta", SwingConstants.CENTER);
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));
		lblTitulo.setFont(Roboto.BLACK.deriveFont(26f));
		lblTitulo.setBounds(90, 10, 130, 40);

		scrollTable = new JScrollPane(tabla);
		scrollTable.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollTable.setBounds(75, 90, 700, 520);
		
		comboBox = new MaterialComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "ID_CRIA", "PESO", "COLOR_MUSCULO", "PORCENTAJE_GRASA", "FECHA_ENTRADA" }));
		comboBox.setBounds(290, 50, 180, 45);
		comboBox.setAccent(MaterialColors.YELLOW_500);
		SwingUtilities.updateComponentTreeUI(comboBox); // Actualizar la apariencia (error)
		
		btnEstado = new JToggleButton("CR�AS VIVAS");
		btnEstado.setSelected(true);
		btnEstado.setForeground(Color.GREEN);
		btnEstado.setBounds(280, 35, 190, 15);

		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setType(Type.RAISED);
		btnRefrescar.setBorder(new DropShadowBorder());
		btnRefrescar.setBounds(5, 95, 80, 80);
		
		btnSig = new MaterialButton();
		btnSig.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnSig.setText(String.valueOf(MaterialIcons.SKIP_NEXT));
		btnSig.setType(Type.FLAT);
		btnSig.setBorder(new DropShadowBorder());
		btnSig.setBounds(5, 200, 80, 80);
		btnSig.setEnabled(false);
		
		btnAnt = new MaterialButton();
		btnAnt.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnAnt.setText(String.valueOf(MaterialIcons.SKIP_PREVIOUS));
		btnAnt.setType(Type.FLAT);
		btnAnt.setBorder(new DropShadowBorder());
		btnAnt.setBounds(5, 150, 80, 80);
		btnAnt.setEnabled(false);

		menuFlotante = new JPopupMenu();
		itemEliminar = new JMenuItem("Eliminar cr�a");
		itemEliminar.setName("Eliminar");
		itemEditar = new JMenuItem("Editar cr�a");
		itemEditar.setName("Editar");
		menuFlotante.add(itemEliminar);
		menuFlotante.addSeparator();
		menuFlotante.add(itemEditar);

		txtBuscar = new IconTextField("Resources\\search_icon.png", "Buscar", 20);
		txtBuscar.setBounds(90, 55, 185, 30);

		lblNumeroCrias = new JLabel("Total de cr�as: ");
		lblNumeroCrias.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroCrias.setFont(new Font("Roboto", Font.BOLD, 16));
		lblNumeroCrias.setBorder(new RoundedCornerBorder(MaterialColors.DARKLY_BLUE));
		lblNumeroCrias.setBounds(570, 45, 190, 40);
		
		lblPaginaActual = new JLabel("P�g. "+pagActual, SwingConstants.CENTER);
		lblPaginaActual.setFont(new Font("Roboto", Font.BOLD, 16));
		lblPaginaActual.setBounds(15, 250, 60, 60);
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setVisibleDateTextField(false);
		dateSettings.setGapBeforeButtonPixels(0); // Redondez de las esquinas
		dateSettings.setFormatForDatesCommonEra("dd-MM-uuuu");
		dateSettings.setColor(DateArea.TextFieldBackgroundValidDate, MaterialColors.DARKLY_STRONG_BLUE);
		dateSettings.setColor(DateArea.TextTodayLabel, MaterialColors.DARKLY_STRONG_BLUE);
		dateSettings.setColor(DateArea.TextMonthAndYearMenuLabels, MaterialColors.DARKLY_STRONG_BLUE);
		dateSettings.setColor(DateArea.TextClearLabel, MaterialColors.DARKLY_STRONG_BLUE);
		dateSettings.setColor(DateArea.DatePickerTextValidDate, MaterialColors.WHITE);
		
		calendario = new DatePicker(dateSettings);
		calendario.getComponentToggleCalendarButton().setIcon(Rutinas.AjustarImagen("Resources\\calendar.png", 20, 20));
		calendario.getComponentToggleCalendarButton().setText("");
		calendario.setEnabled(false);
		calendario.getComponentToggleCalendarButton()
				.setDisabledIcon(Rutinas.AjustarImagen("Resources\\calendar.png", 20, 20));
		calendario.setBounds(480, 45, 45, 45);
		
		add(lblTitulo);
		add(btnRefrescar);
		add(btnSig);
		add(btnAnt);
		add(txtBuscar);
		add(scrollTable);
		add(comboBox);
		add(btnEstado);
		add(calendario);
		add(lblNumeroCrias);
		add(lblPaginaActual);
	}

	private void iniciarTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);
		defineColumnas();
		defineAnchoColumnas(); // Cambio tama�o m�ximo de las columnas

//		tabla.setComponentPopupMenu(menuFlotante); // Men� emergente
		tabla.getTableHeader().setReorderingAllowed(false); // Evitar mover columnas
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Evitar multiselecci�n
		tabla.setAutoCreateRowSorter(true); // Ordena columnas por orden alfab�tico
	}

	public void setControladorConsulta(ControladorConsulta controlador) {
		btnRefrescar.addActionListener(controlador);
		txtBuscar.addCaretListener(controlador);
		comboBox.addItemListener(controlador);
		calendario.addDateChangeListener(controlador);
		btnEstado.addActionListener(controlador);
		btnSig.addActionListener(controlador);
		btnAnt.addActionListener(controlador);
	}

	public void setControladorSeleccionTabla(ControladorTabla controlador) {
		tabla.addMouseListener(controlador);
		itemEliminar.addActionListener(controlador);
		itemEditar.addActionListener(controlador);
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
		if (objetoCria.size() == 0) {
			limpiarTabla();
			((DefaultTableModel) tabla.getModel()).setColumnCount(0);
			((DefaultTableModel) tabla.getModel()).addColumn("Mensaje");
			tabla.setEnabled(false); // Evitar clicks en la fila del mensaje
			modeloTabla.addRow(new String[] { "NO SE ENCONTRARON RESULTADOS" });
		} else {
			setTabla(objetoCria);
		}
	}

	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
		if (((DefaultTableModel) tabla.getModel()).getColumnCount() != 5) {
			defineColumnas();
			defineAnchoColumnas();
			tabla.setEnabled(true); // Permitir clicks en las filas
		}
	}

	private void defineColumnas() {
		((DefaultTableModel) tabla.getModel()).setColumnCount(0); // Borramos las columnas que haya (en caso de)
		modeloTabla.addColumn("<HTML><h2 style= text-decoration:underline>ID Cr�a");
		modeloTabla.addColumn("Peso");
		modeloTabla.addColumn("Color m�sculo");
		modeloTabla.addColumn("Porcentaje de grasa");
		modeloTabla.addColumn("Fecha de entrada");
	}

	private void defineAnchoColumnas() {
		TableColumnModel columnas = tabla.getColumnModel();
		columnas.getColumn(0).setMaxWidth(85);
		columnas.getColumn(1).setMaxWidth(85);
		columnas.getColumn(2).setMaxWidth(150);
		columnas.getColumn(3).setMaxWidth(170);
		columnas.getColumn(4).setMaxWidth(170);
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage(this);
		if (error)
			toast.setInfo(msg, MaterialColors.RED_500);
		else
			toast.setInfo(msg, MaterialColors.BLUE_500);

		toast.showToast();
	}

	public void editarCria() {
		modal.setLocationRelativeTo(Ejecutar.getInstance()); //lo reubico 
		int id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		String peso = tabla.getValueAt(tabla.getSelectedRow(), 1).toString().split(" ")[0];
		String color = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
		String grasa = tabla.getValueAt(tabla.getSelectedRow(), 3).toString().split(" ")[0];

		modal.setInfo(id, peso, color, grasa);
		modal.setVisible(true);
	}
}