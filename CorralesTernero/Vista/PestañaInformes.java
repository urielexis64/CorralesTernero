package Vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.ControladorInformes;
import material.extras.ToastMessage;
import material.fonts.Roboto;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaInformes extends JPanel {
	public JButton btnGenerar, btnUbicacion;
	public JLabel lblTitulo;
	public JTextField txtUbicacion;

	public PestañaInformes() {
		setLayout(null);

		lblTitulo = new JLabel("Generar informe", SwingConstants.CENTER);
		lblTitulo.setFont(Roboto.BLACK.deriveFont(28f));
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));
		lblTitulo.setBounds(50, 20, 240, 50);

		txtUbicacion = new JTextField();
		txtUbicacion.setBounds(50, 100, 500, 30);
		txtUbicacion.setBorder(new RoundedCornerBorder());
		txtUbicacion.setEnabled(false);

		btnUbicacion = new JButton();
		btnUbicacion.setText("...");
		btnUbicacion.setBounds(560, 100, 30, 30);

		btnGenerar = new JButton();
		btnGenerar.setText("Generar");
		btnGenerar.setBounds(610, 100, 120, 30);

		add(lblTitulo);	
		add(txtUbicacion);
		add(btnUbicacion);
		add(btnGenerar);
	}

	public void setControlador(ControladorInformes controlador) {
		btnUbicacion.addActionListener(controlador);
		btnGenerar.addActionListener(controlador);
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage(this);
		if (error)
			toast.setInfo(msg, MaterialColors.RED_500);
		else
			toast.setInfo(msg, MaterialColors.BLUE_500);

		toast.showToast();
	}
}
