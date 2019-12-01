package Vista;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorCuidados;
import extras.JCheckBoxColumn;
import material.componentes.MaterialButton;
import material.componentes.MaterialButton.Type;
import material.extras.ToastMessage;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaCuidados extends JPanel {
	private JLabel lblTitulo;
	public JTable tabla;
	private JCheckBoxColumn modeloTabla;
	private JScrollPane scrollPane;
	public MaterialButton btnGuardar, btnRefrescar;
	
	public PestañaCuidados() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		
		lblTitulo = new JLabel("Estado de salud de las crías", SwingConstants.CENTER);
		lblTitulo.setFont(Roboto.BLACK.deriveFont(24f));
		lblTitulo.setBounds(65, 20, 320, 50);
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));

		hazTabla();
		
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollPane.setBounds(50, 100, 400, 400);
		
		btnGuardar = new MaterialButton();
		btnGuardar.setFont(MaterialIcons.ICON_FONT.deriveFont(35f));
		btnGuardar.setText(String.valueOf(MaterialIcons.SAVE));
		btnGuardar.setBounds(455, 100, 80, 80);
		
		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setType(Type.RAISED);
		btnRefrescar.setBorder(new DropShadowBorder());
		btnRefrescar.setBounds(455, 160, 80, 80);
		
		add(lblTitulo);
		add(scrollPane);
		add(btnGuardar);
		add(btnRefrescar);
	}

	private void hazTabla() {
		modeloTabla = new JCheckBoxColumn();
		tabla = new JTable(modeloTabla);

		modeloTabla.addColumn("ID CRIA");
		modeloTabla.addColumn("ENFERMA");
	}

	public void setTabla(Vector<Vector<Object>> objetoCria) {

		limpiarTabla();

		Vector<Object> nuevaCria;
		for (int i = 0; i < objetoCria.size(); i++) {
			nuevaCria = new Vector<Object>();

			nuevaCria.add(objetoCria.get(i).get(0)); // ID
			nuevaCria.add(objetoCria.get(i).get(1).equals("1") ? false : true);

			modeloTabla.addRow(nuevaCria);
		}

	}
	

	private void limpiarTabla() {
		((DefaultTableModel) tabla.getModel()).setNumRows(0);
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage(this);
		if (error)
			toast.setInfo(msg, MaterialColors.RED_400);
		else
			toast.setInfo(msg, MaterialColors.BLUE_400);
		toast.showToast();
	}
	
	public void setControladorCuidados(ControladorCuidados controlador) {
		btnGuardar.addActionListener(controlador);
		btnRefrescar.addActionListener(controlador);
	}
}
