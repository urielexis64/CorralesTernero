package Vista;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Controlador.ControladorSensores;
import EjecutarApp.Ejecutar;
import material.componentes.MaterialButton;
import material.componentes.MaterialComboBox;
import material.componentes.MaterialButton.Type;
import material.extras.ToastMessage;
import material.fonts.MaterialIcons;
import material.fonts.Roboto;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaAsignacionSensores extends JPanel {

	private JLabel lblTitulo, lblCodigoSensor, lblIdCria;
	public MaterialComboBox<String> comboSensores;
	public JButton btnAsignar;
	public MaterialButton btnRefrescar;
	public JTextField txtIdCria;

	public PestañaAsignacionSensores() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		lblTitulo = new JLabel("Asignación de sensores", SwingConstants.CENTER);
		lblTitulo.setFont(Roboto.BLACK.deriveFont(24f));
		lblTitulo.setBounds(50, 10, 280, 50);
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));

		lblCodigoSensor = new JLabel("Código de sensor", SwingConstants.LEFT);
		lblCodigoSensor.setFont(Roboto.BLACK.deriveFont(16f));
		lblCodigoSensor.setBounds(50, 80, 150, 50);
		
		comboSensores = new MaterialComboBox<>();
		comboSensores.setBounds(220, 80, 180, 45);
		comboSensores.setAccent(MaterialColors.GREEN_300);
		comboSensores.addItem("Seleccione...");
		SwingUtilities.updateComponentTreeUI(comboSensores);
		
		lblIdCria = new JLabel("ID cría", SwingConstants.LEFT);
		lblIdCria.setFont(Roboto.BLACK.deriveFont(16f));
		lblIdCria.setBounds(50, 140, 150, 30);
		
		txtIdCria= new JTextField();
		txtIdCria.setBounds(220, 140, 200, 30);
		txtIdCria.setBorder(new RoundedCornerBorder());
		
		btnAsignar = new JButton("Asignar sensor");
		btnAsignar.setBounds(220, 180, 200, 30);
		
		btnRefrescar= new MaterialButton();
		btnRefrescar.setText(String.valueOf(MaterialIcons.REFRESH));
		btnRefrescar.setFont(MaterialIcons.ICON_FONT.deriveFont(20f));
		btnRefrescar.setType(Type.FLAT);
		btnRefrescar.setBounds(420, 80, 60,60);

		add(lblTitulo);
		add(lblCodigoSensor);
		add(lblIdCria);
		add(txtIdCria);
		add(btnAsignar);
		add(btnRefrescar);
		add(comboSensores);
	}

	public void setControlador(ControladorSensores controlador) {
		btnAsignar.addActionListener(controlador);
		btnRefrescar.addActionListener(controlador);
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage(Ejecutar.getInstance());
		if (error)
			toast.setInfo(msg, MaterialColors.RED_400);
		else {
			toast.setInfo(msg, MaterialColors.GREEN_600);
			toast.setLocation(toast.getLocation().x, toast.getLocation().y + 40);
		}
		toast.showToast();
	}

	public void llenaCombo(Vector<String> codigos) {
		comboSensores.removeAllItems();
		comboSensores.addItem("Seleccione...");
		
		for(int i=0;i<codigos.size();i++)
			comboSensores.addItem(codigos.get(i));

		comboSensores.setSelectedIndex(0);
	}

}
