package Vista;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSensores;
import extras.ModeloTabla;
import material.componentes.MaterialButton;
import material.componentes.MaterialColor;
import material.extras.ToastMessage;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaSensores extends JPanel {

	private JLabel lblTitulo;
	public JTable tabla;
	private ModeloTabla modeloTabla;
	private JScrollPane scrollPane;
	public MaterialButton btnEnviarCorreo, btnRefrescar, btnSimular;
	public JProgressBar bar;
	public Timer timer;
	
	public PestañaSensores() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		
		lblTitulo = new JLabel("Sensores de crías finas", SwingConstants.CENTER);
		lblTitulo.setFont(Roboto.BLACK.deriveFont(24f));
		lblTitulo.setBounds(50, 20, 300, 50);
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));

		hazTabla();

		scrollPane = new JScrollPane(tabla);
		scrollPane.setBorder(new DropShadowBorder(MaterialColors.WHITE, 2, 15, .5f, 10, true, true, true, true));
		scrollPane.setBounds(35, 100, 600, 500);

		btnEnviarCorreo = new MaterialButton();
		btnEnviarCorreo.setFont(MaterialIcons.ICON_FONT.deriveFont(35f));
		btnEnviarCorreo.setText(String.valueOf(MaterialIcons.EMAIL));
		btnEnviarCorreo.setBounds(630, 105, 80, 80);

		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setBorder(new DropShadowBorder());
		btnRefrescar.setBounds(630, 160, 80, 80);

		btnSimular = new MaterialButton();
		btnSimular.setText(String.valueOf(MaterialIcons.GRAPHIC_EQ));
		btnSimular.setFont(MaterialIcons.ICON_FONT.deriveFont(35f));
		btnSimular.setBackground(MaterialColor.GREEN_300);
		btnSimular.setBounds(630, 215, 80, 80);

		bar = new JProgressBar();
		bar.setBounds(0, 0, 780, 5);
		bar.setIndeterminate(true);
		bar.setVisible(false);

		add(bar);
		add(lblTitulo);
		add(scrollPane);
		add(btnEnviarCorreo);
		add(btnRefrescar);
		add(btnSimular);
	}

	private void hazTabla() {
		modeloTabla = new ModeloTabla();
		tabla = new JTable(modeloTabla);

		tabla.setDefaultRenderer(Object.class, modeloTabla);

		modeloTabla.addColumn("ID SENSOR");
		modeloTabla.addColumn("Temperatura");
		modeloTabla.addColumn("Presión arterial");
		modeloTabla.addColumn("Ubicación");
	}

	public void setTabla(Vector<Vector<Object>> objetoCria) {
		limpiarTabla();

		Vector<Object> nuevoSensor;
		for (int i = 0; i < objetoCria.size(); i++) {
			nuevoSensor = new Vector<Object>();

			nuevoSensor.add(objetoCria.get(i).get(0)); // ID SENSOR
			nuevoSensor.add(objetoCria.get(i).get(1) + " ºC"); // Temperatura
			nuevoSensor.add(objetoCria.get(i).get(2) + " mm Hg"); // Presión arterial
			nuevoSensor.add(objetoCria.get(i).get(3)); // Ubicación

			modeloTabla.addRow(nuevoSensor);
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

	public void setControladorSensores(ControladorSensores controlador) {
		btnEnviarCorreo.addActionListener(controlador);
		btnRefrescar.addActionListener(controlador);
		btnSimular.addActionListener(controlador);
		timer= new Timer(4000, controlador);
	}
}