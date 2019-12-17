package Vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Controlador.ControladorSensores;
import material.componentes.MaterialComboBox;
import material.fonts.Roboto;
import mdlaf.shadows.RoundedCornerBorder;

public class PestañaRegistroSensores extends JPanel {

	private JLabel lblTitulo, lblIdSensor, lblGuion;
	public JButton btnRegistrar;
	public JTextField txtIdSensor;
	public MaterialComboBox<String> comboEstados;

	public PestañaRegistroSensores() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		lblTitulo = new JLabel("Registro de sensores", SwingConstants.CENTER);
		lblTitulo.setFont(Roboto.BLACK.deriveFont(24f));
		lblTitulo.setBounds(50, 10, 280, 50);
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));

		lblIdSensor = new JLabel("Código del sensor", SwingConstants.LEFT);
		lblIdSensor.setFont(Roboto.BLACK.deriveFont(16f));
		lblIdSensor.setBounds(50, 100, 150, 50);

		lblGuion = new JLabel("-");
		lblGuion.setFont(Roboto.BLACK.deriveFont(16f));
		lblGuion.setBounds(330, 100, 20, 50);

		comboEstados = new MaterialComboBox<String>();
		comboEstados.setBounds(210, 100, 110, 50);
		llenaComboEstados();
		SwingUtilities.updateComponentTreeUI(comboEstados);

		txtIdSensor = new JTextField();
		txtIdSensor.setBounds(345, 104, 100, 40);
		txtIdSensor.setBackground(Color.decode("#28415B"));

		btnRegistrar = new JButton("Registrar sensor");
		btnRegistrar.setBounds(150, 180, 200, 30);

		add(lblTitulo);
		add(lblIdSensor);
		add(lblGuion);
		add(comboEstados);
		add(txtIdSensor);
		add(btnRegistrar);
	}

	public void setControlador(ControladorSensores controlador) {
		btnRegistrar.addActionListener(controlador);
	}

	private void llenaComboEstados() {
		comboEstados.addItem("Seleccione...");
		comboEstados.addItem("AGU");
		comboEstados.addItem("BCN");
		comboEstados.addItem("BCS");
		comboEstados.addItem("CAM");
		comboEstados.addItem("CHP");
		comboEstados.addItem("CHH");
		comboEstados.addItem("COA");
		comboEstados.addItem("COL");
		comboEstados.addItem("CMX");
		comboEstados.addItem("DUR");
		comboEstados.addItem("GUA");
		comboEstados.addItem("GRO");
		comboEstados.addItem("HID");
		comboEstados.addItem("JAL");
		comboEstados.addItem("MEX");
		comboEstados.addItem("MIC");
		comboEstados.addItem("MOR");
		comboEstados.addItem("NAY");
		comboEstados.addItem("NLE");
		comboEstados.addItem("OAX");
		comboEstados.addItem("PUE");
		comboEstados.addItem("QUE");
		comboEstados.addItem("ROO");
		comboEstados.addItem("SLP");
		comboEstados.addItem("SIN");
		comboEstados.addItem("SON");
		comboEstados.addItem("TAB");
		comboEstados.addItem("TAM");
		comboEstados.addItem("TLA");
		comboEstados.addItem("VER");
		comboEstados.addItem("YUC");
		comboEstados.addItem("ZAC");
	}

}
