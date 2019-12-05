package Vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Controlador.ControladorRegistroUsuarios;
import EjecutarApp.Ejecutar;
import material.componentes.MaterialColor;
import material.componentes.MaterialPasswordField;
import material.componentes.MaterialTextField;
import material.extras.AccionComponente;
import material.extras.ToastMessage;
import material.fonts.Roboto;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaRegistroUsuarios extends JPanel {

	public JButton btnRegistrar, btnLimpiar;
	private JLabel lblTitulo;
	public JCheckBox checkRegistro, checkConsulta, checkClasificacion,checkSacrificios, checkCuidados, checkSensores, checkInforme, checkLog, checkSigProceso;
	public JPanel panelPermisos;
	public MaterialTextField txtNombreUsuario, txtCorreoUsuario;
	public MaterialPasswordField txtContraUsuario, txtConfirmaContra;

	public PestañaRegistroUsuarios() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		lblTitulo = new JLabel("Registro de usuarios", SwingConstants.CENTER);
		lblTitulo.setBorder(new RoundedCornerBorder(new Color(193, 244, 56)));
		lblTitulo.setFont(Roboto.BLACK.deriveFont(30f));
		lblTitulo.setBounds(45, 10, 350, 50);

		btnRegistrar = new JButton("Registrar usuario");
		btnRegistrar.setBounds(45, 400, 170, 50);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(275, 400, 120, 50);

		txtNombreUsuario = new MaterialTextField();
		txtNombreUsuario.setLabel("Nombre");
		txtNombreUsuario.setAccent(new Color(193, 244, 56));
		txtNombreUsuario.setForeground(MaterialColors.WHITE);
		txtNombreUsuario.setBackground(MaterialColor.TRANSPARENT);
		txtNombreUsuario.setBounds(45, 100, 350, 70);

		txtCorreoUsuario = new MaterialTextField();
		txtCorreoUsuario.setLabel("Correo electrónico");
		txtCorreoUsuario.setAccent(new Color(193, 244, 56));
		txtCorreoUsuario.setForeground(MaterialColors.WHITE);
		txtCorreoUsuario.setBackground(MaterialColor.TRANSPARENT);
		txtCorreoUsuario.setBounds(45, 170, 350, 70);

		txtContraUsuario = new MaterialPasswordField();
		txtContraUsuario.setLabel("Contraseña");
		txtContraUsuario.setAccent(new Color(193, 244, 56));
		txtContraUsuario.setForeground(MaterialColors.WHITE);
		txtContraUsuario.setBackground(MaterialColor.TRANSPARENT);
		txtContraUsuario.setBounds(45, 240, 350, 70);

		txtConfirmaContra = new MaterialPasswordField();
		txtConfirmaContra.setLabel("Confirmar contraseña");
		txtConfirmaContra.setAccent(new Color(193, 244, 56));
		txtConfirmaContra.setForeground(MaterialColors.WHITE);
		txtConfirmaContra.setBackground(MaterialColor.TRANSPARENT);
		txtConfirmaContra.setBounds(45, 310, 350, 70);

		checkRegistro = new JCheckBox("Registrar crías");
		checkConsulta = new JCheckBox("Consultar crías");
		checkClasificacion = new JCheckBox("Ver clasificación");
		checkCuidados = new JCheckBox("Salud crías");
		checkSacrificios = new JCheckBox("Sacrificar crías");
		checkSensores = new JCheckBox("Sensores");
		checkInforme = new JCheckBox("Generar informe");
		checkLog = new JCheckBox("Ver LOG");
		checkSigProceso = new JCheckBox("Pasar a siguiente proceso");

		panelPermisos = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelPermisos.setBounds(500, 100, 230, 350);
		panelPermisos.setBorder(BorderFactory.createTitledBorder(new RoundedCornerBorder(), "Permisos",
				TitledBorder.CENTER, TitledBorder.TOP, Roboto.BOLD.deriveFont(20f), Color.WHITE));

		panelPermisos.add(checkRegistro);
		panelPermisos.add(checkClasificacion);
		panelPermisos.add(checkConsulta);
		panelPermisos.add(checkCuidados);
		panelPermisos.add(checkSacrificios);
		panelPermisos.add(checkSensores);
		panelPermisos.add(checkLog);
		panelPermisos.add(checkInforme);
		panelPermisos.add(checkSigProceso);

		add(txtNombreUsuario);
		add(txtCorreoUsuario);
		add(txtContraUsuario);
		add(txtConfirmaContra);

		add(panelPermisos);
		add(lblTitulo);
		add(btnRegistrar);
		add(btnLimpiar);
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

	@SuppressWarnings("deprecation")
	public boolean verificarCampos() {
		boolean estado = true;
		ArrayList<JComponent> componentes = new ArrayList<JComponent>();

		if (txtNombreUsuario.getText().equals("")) {
			estado = false;
			componentes.add(txtNombreUsuario);
		}
		if (txtCorreoUsuario.getText().trim().equals("")) {
			estado = false;
			componentes.add(txtCorreoUsuario);
		}
		if (txtContraUsuario.getText().equals("")) {
			estado = false;
			componentes.add(txtContraUsuario);
		}
		if (txtConfirmaContra.getText().equals("")) {
			estado = false;
			componentes.add(txtConfirmaContra);
		}
		if (!estado) {
			componentes.get(0).requestFocus();
			for (int i = 0; i < componentes.size(); i++)
				AccionComponente.sacudir(componentes.get(i), 300, 10);
		}
		return estado;
	}

	public void limpiar() {
		txtNombreUsuario.setText("");
		txtCorreoUsuario.setText("");
		txtContraUsuario.setText("");
		txtConfirmaContra.setText("");
		checkRegistro.setSelected(false);
		checkConsulta.setSelected(false);
		checkClasificacion.setSelected(false);
		checkCuidados .setSelected(false);
		checkSacrificios .setSelected(false);
		checkSensores.setSelected(false);
		checkInforme.setSelected(false);
		checkLog .setSelected(false);
		checkSigProceso.setSelected(false);
	}

	@SuppressWarnings("deprecation")
	public boolean confirmaContraseña() {
		return txtContraUsuario.getText().equals(txtConfirmaContra.getText());
	}

	public void setControlador(ControladorRegistroUsuarios controlador) {
		btnRegistrar.addActionListener(controlador);
		btnLimpiar.addActionListener(controlador);
	}

}
