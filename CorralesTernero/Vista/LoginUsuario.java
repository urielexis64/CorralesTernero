package Vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controlador.ControladorLoginUsuario;
import Vista.VentanaPrincipal.FrameDragListener;
import material.componentes.MaterialColor;
import material.componentes.MaterialPasswordField;
import material.componentes.MaterialProgressSpinner;
import material.componentes.MaterialTextField;
import material.extras.Rutinas;
import material.extras.ToastMessage;
import material.fonts.Roboto;
import mdlaf.utils.MaterialColors;

public class LoginUsuario extends JFrame {

	private PanelGradiente fondo;
	public JLabel lblFoto;
	public MaterialTextField txtCorreo;
	public MaterialPasswordField txtContra;
	public JButton btnEntrar, btnCerrar;
	public MaterialProgressSpinner bar;

	public LoginUsuario() {
		hazInterfaz();
		setVisible(true);
	}

	private void hazInterfaz() {
		setSize(400, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));

		fondo = new PanelGradiente();
		fondo.setBounds(0, 0, 400, 600);

		lblFoto = new JLabel(Rutinas.AjustarImagen("Resources\\user_icon.png", 150, 150));
		lblFoto.setBounds(125, 20, 150, 150);
		lblFoto.setBackground(MaterialColor.TRANSPARENT);

		btnCerrar = new JButton(Rutinas.AjustarImagen("Resources\\close.png", 25, 25));
		btnCerrar.setBounds(355, 8, 25, 25);

		txtCorreo = new MaterialTextField();
		txtCorreo.setLabel("Correo electrónico");
		txtCorreo.setBounds(50, 190, 300, 70);
		txtCorreo.setFont(Roboto.REGULAR.deriveFont(14f));
		txtCorreo.setBackground(MaterialColor.TRANSPARENT);
		txtCorreo.setAccent(Color.decode("#00DBAD"));

		txtContra = new MaterialPasswordField();
		txtContra.setLabel("Contraseña");
		txtContra.setBounds(50, 265, 300, 70);
		txtContra.setFont(Roboto.REGULAR.deriveFont(14f));
		txtContra.setBackground(MaterialColor.TRANSPARENT);
		txtContra.setAccent(Color.decode("#00DBAD"));

		btnEntrar = new JButton("Iniciar sesión", Rutinas.AjustarImagen("Resources\\enter_icon.png", 30, 30));
		btnEntrar.setHorizontalTextPosition(SwingConstants.LEFT);
		btnEntrar.setIconTextGap(20);
		btnEntrar.setBounds(50, 380, 300, 50);

		bar = new MaterialProgressSpinner();
		bar.setBounds(162, 290, 75, 75);
		bar.setVisible(false);

		add(bar);
		add(lblFoto);
		add(btnCerrar);
		add(txtCorreo);
		add(txtContra);
		add(btnEntrar);
		add(fondo);
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage(this);
		if (error)
			toast.setInfo(msg, MaterialColors.RED_400);
		else {
			toast.setInfo(msg, MaterialColors.GREEN_600);
			toast.showToast();
			setVisible(false);
		}
		toast.showToast();
	}

	public void setControlador(ControladorLoginUsuario controlador) {
		btnEntrar.addActionListener(controlador);
		btnCerrar.addActionListener(controlador);
		FrameDragListener frameDragListener = new FrameDragListener(this);
		addMouseListener(frameDragListener);
		addMouseMotionListener(frameDragListener);
	}

	class PanelGradiente extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setPaint(new GradientPaint(0, 200, Color.decode("#28415B"), 200, 500, Color.decode("#22417A")));
			g2.fillRect(0, 0, getWidth(), getHeight());
		}
	}

}
