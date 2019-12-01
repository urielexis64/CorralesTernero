package Vista;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controlador.ControladorSacrificios;
import extras.ButtonColumn;
import extras.ModeloTabla;
import material.componentes.MaterialButton;
import material.extras.ToastMessage;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaSacrificios extends JPanel {

	private JLabel lblTitulo;
	private JTable tabla;
	private ModeloTabla modeloTabla;
	private JScrollPane scrollTabla;
	public MaterialButton btnRefrescar;

	public PestañaSacrificios() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		lblTitulo = new JLabel("Crías listas para sacrificar", SwingConstants.CENTER);
		lblTitulo.setFont(Roboto.BLACK.deriveFont(28f));
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));
		lblTitulo.setBounds(50, 20, 380, 50);

		hazTabla();

		scrollTabla = new JScrollPane(tabla);
		scrollTabla.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollTabla.setBounds(35, 100, 650, 300);

		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setBounds(670, 100, 80, 80);

		add(lblTitulo);
		add(scrollTabla);
		add(btnRefrescar);
	}

	public void setControlador(ControladorSacrificios controlador) {
		ButtonColumn buttonColumn = new ButtonColumn(tabla, controlador, 5);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		btnRefrescar.addActionListener(controlador);
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage(this);
		if (error)
			toast.setInfo(msg, MaterialColors.RED_400);
		else
			toast.setInfo(msg, MaterialColors.GREEN_600);
		toast.showToast();
	}

	private void hazTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);
		defineColumnas();
		defineAnchoColumnas();
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
			
			String fecha = objetoCria.get(i).get(4) + "";
			nuevaCria.add(fecha.equals("null") ? "-" : fecha); // ÚLTIMA VEZ CUARENTENA
			nuevaCria.add("CUELLO");

			modeloTabla.addRow(nuevaCria);
		}
	}

	private void defineColumnas() {
		modeloTabla.addColumn("<html><h2>ID Cría");
		modeloTabla.addColumn("<html><h4>Clasificación");
		modeloTabla.addColumn("<html><h4>Fecha de entrada");
		modeloTabla.addColumn("<html><h4>Veces en cuarentena");
		modeloTabla.addColumn("<html><h4>Última vez en cuarentena");
		modeloTabla.addColumn("Sacrificar");
	}

	private void defineAnchoColumnas() {
		TableColumnModel columnas = tabla.getColumnModel();
		columnas.getColumn(0).setMaxWidth(70);
		columnas.getColumn(1).setMaxWidth(120);
		columnas.getColumn(2).setMaxWidth(100);
		columnas.getColumn(3).setMaxWidth(80);
		columnas.getColumn(4).setMaxWidth(215);
		columnas.getColumn(5).setMaxWidth(90);
	}

	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
	}

}
