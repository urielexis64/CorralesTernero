package Vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Controlador.ControladorLoginUsuario;
import Vista.VentanaPrincipal.FrameDragListener;
import extras.Autocomplete;
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
	public JButton btnEntrar, btnCerrar, btnMinimizar, btnOjo;
	public MaterialProgressSpinner bar;
	public ImageIcon imgOjo, imgOjoCerrado;

	public LoginUsuario() {
		hazInterfaz();
		setVisible(true);
	}

	private void hazInterfaz() {
		setSize(400, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		setUndecorated(true);
		setIconImage(Rutinas.AjustarImagen("Resources\\cow.png", 50, 50).getImage());
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));

		imgOjo = Rutinas.AjustarImagen("Resources\\ojo.png", 25, 25);
		imgOjoCerrado = Rutinas.AjustarImagen("Resources\\ojoCerrado.png", 25, 25);

		btnOjo = new JButton(imgOjoCerrado);
		btnOjo.setBounds(320, 295, 25, 25);
		btnOjo.setContentAreaFilled(false);
		btnOjo.setFocusable(false);

		fondo = new PanelGradiente();
		fondo.setBackground(new Color(255,255,255,100));
		fondo.setBounds(0, 0, 400, 600);

		lblFoto = new JLabel(Rutinas.AjustarImagen("Resources\\user_icon.png", 150, 150));
		lblFoto.setBounds(125, 20, 150, 150);
		lblFoto.setBackground(MaterialColor.TRANSPARENT);

		btnCerrar = new JButton(Rutinas.AjustarImagen("Resources\\close.png", 25, 25));
		btnCerrar.setBounds(355, 8, 25, 25);
		btnCerrar.setFocusable(false);

		btnMinimizar = new JButton(Rutinas.AjustarImagen("Resources\\minimize.png", 25, 25));
		btnMinimizar.setBounds(325, 8, 25, 25);
		btnMinimizar.setFocusable(false);

		txtCorreo = new MaterialTextField();
		txtCorreo.setLabel("Correo electrónico");
		txtCorreo.setBounds(50, 190, 300, 70);
		txtCorreo.setFont(Roboto.REGULAR.deriveFont(14f));
		txtCorreo.setBackground(MaterialColor.TRANSPARENT);
		txtCorreo.setAccent(Color.decode("#00DBAD"));
		txtCorreo.setFocusTraversalKeysEnabled(false);

		ArrayList<String> keywords = new ArrayList<String>(5);
		keywords.add("corralesternero@ternero.com");
		keywords.add("urielalexis64@ternero.com");
		Autocomplete autoComplete = new Autocomplete(txtCorreo, keywords);
		txtCorreo.getDocument().addDocumentListener(autoComplete);
		txtCorreo.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "commit");
		txtCorreo.getActionMap().put("commit", autoComplete.new CommitAction());

		txtContra = new MaterialPasswordField();
		txtContra.setLabel("Contraseña");
		txtContra.setBounds(50, 265, 300, 70);
		txtContra.setFont(Roboto.REGULAR.deriveFont(14f));
		txtContra.setBackground(MaterialColor.TRANSPARENT);
		txtContra.setAccent(Color.decode("#00DBAD"));

		btnEntrar = new JButton("Iniciar sesión", Rutinas.AjustarImagen("Resources\\enter_icon.png", 30, 30));
		btnEntrar.setHorizontalTextPosition(SwingConstants.LEFT);
		btnEntrar.setIconTextGap(20);
		btnEntrar.setFocusable(false);
		btnEntrar.setBounds(50, 380, 300, 50);

		bar = new MaterialProgressSpinner(Color.decode("#77DBAD"));
		bar.setBounds(162, 290, 75, 75);
		bar.setVisible(false);
		
		add(bar);
		add(lblFoto);
		add(btnCerrar);
		add(btnMinimizar);
		add(txtCorreo);
		add(btnOjo);
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
		btnMinimizar.addActionListener(controlador);
		btnOjo.addActionListener(controlador);
		txtCorreo.addActionListener(controlador);
		txtContra.addActionListener(controlador);
		FrameDragListener frameDragListener = new FrameDragListener(this);
		addMouseListener(frameDragListener);
		addMouseMotionListener(frameDragListener);
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
		new Thread(() -> {
			int w = 0, h = 0;

			while (w < getWidth()) {
				setShape(new RoundRectangle2D.Double(getWidth() / 2 - w / 2 - 20, getHeight() / 2 - h / 2 - 25, w += 40,
						h += 50, 30, 30));
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}).start();
	}

	class PanelGradiente extends JPanel {
		private static final long serialVersionUID = 1L;
//		private Timer t;
//		private int x1, y1, x2, y2;
//		
//		public PanelGradiente() {
//			x1=200;
//			y1=0;
//			x2=200;
//			y2=500;}
//			
//			t = new Timer(1000, evt -> {
//				if(x1>0 &&y1!=500) 
//					x1-=5;
//					
//				if(x1==0)
//					y1+=5;
//				
//				if(y1==500)
//					x1+=5;
//				
//				if(x1==500)
//					y1-=5;
//				
//				if(x2<500 &&y2!=0) 
//					x2+=5;
//					
//				if(x2==500)
//					y2-=5;
//				
//				if(y2==0)
//					x2-=5;
//				
//				if(x2==0)
//					y2+=5;
//				
//				repaint();			
//			});
//			t.start();
//		}

		@Override
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setPaint(new GradientPaint(200, 0, Color.decode("#2F415B"), 200, 500, Color.decode("#21357A")));
			g2.fillRect(0, 0, getWidth(), getHeight());			
		}
	}

}
