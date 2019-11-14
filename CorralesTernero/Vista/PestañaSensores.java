package Vista;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSensores;
import EjecutarApp.ModeloTabla;
import material.componentes.MaterialButton;
import material.componentes.MaterialProgressSpinner;
import material.extras.ToastMessage;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.utils.MaterialColors;

public class PestañaSensores extends JPanel {
	
	private JLabel lblTitulo;
	public JTable tabla;
	private ModeloTabla modeloTabla;
	private JScrollPane scrollPane;
	public MaterialButton btnEnviarCorreo, btnRefrescar;
	public MaterialProgressSpinner bar;
	
	public PestañaSensores() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		
		lblTitulo = new JLabel("Sensores de crías finas (Cobertura 2)");
		lblTitulo.setFont(Roboto.BLACK.deriveFont(24f));
		lblTitulo.setBounds(50, 20, 450, 50);
		
		bar = new MaterialProgressSpinner();
		bar.setBounds(300, 200, 100, 100);
		bar.setVisible(false);
		
		hazTabla();
		
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(50, 100, 600, 500);
		
		btnEnviarCorreo = new MaterialButton();
		btnEnviarCorreo.setFont(MaterialIcons.ICON_FONT.deriveFont(35f));
		btnEnviarCorreo.setText(String.valueOf(MaterialIcons.EMAIL));
		btnEnviarCorreo.setBounds(650, 90, 80, 80);
		
		btnRefrescar = new MaterialButton();
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(30f));
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setBorder(new DropShadowBorder());
		btnRefrescar.setBounds(650, 150, 80, 80);
		btnRefrescar.doClick();
		
		add(lblTitulo);
		add(scrollPane);
		add(btnEnviarCorreo);
		add(btnRefrescar);
		add(bar);
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
			nuevoSensor.add(objetoCria.get(i).get(2)+" mm Hg"); // Presión arterial
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
	}
}