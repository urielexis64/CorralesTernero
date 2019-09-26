package Vista;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import Controlador.ControladorModoOscuro;
import Controlador.ControladorTitleBar;
import de.craften.ui.swingmaterial.MaterialColor;
import de.craften.ui.swingmaterial.MaterialPanel;
import mdlaf.*;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import misHerramientas.Rutinas;

public class VentanaPrincipal extends JFrame {

	private JTabbedPane pesta�as;
	private JToggleButton btnModoOscuro;
	private JButton btnCerrar, btnMinimizar, btnInfo;
	private MaterialPanel panelSuperior;
	private JLabel lblTitulo;

	public Pesta�aRegistro registro;
	public Pesta�aClasifcacion clasificacion;
	public Pesta�aConsulta consulta;

	public VentanaPrincipal() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		panelSuperior = new MaterialPanel();
		panelSuperior.setBounds(0, 0, 800, 75);
		panelSuperior.setLayout(null);

		lblTitulo = new JLabel("Corrales Ternero", JLabel.RIGHT);
		lblTitulo.setFont(new Font("Forte", Font.PLAIN, 20));
		lblTitulo.setIcon(Rutinas.AjustarImagen("Resources\\cow.png", 30, 30));
		lblTitulo.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTitulo.setBounds(25, 12, 190, 40);
		lblTitulo.setBorder(new RoundedCornerBorder(MaterialColor.BLUEGREY_500));

		btnInfo = new JButton(Rutinas.AjustarImagen("Resources\\info.png", 20, 20));
		btnInfo.setBounds(660, 15, 35, 35);
		btnInfo.setActionCommand("info");
		btnCerrar = new JButton(Rutinas.AjustarImagen("Resources\\close.png", 20, 20));
		btnCerrar.setBounds(740, 15, 35, 35);
		btnCerrar.setActionCommand("cerrar");
		btnMinimizar = new JButton(Rutinas.AjustarImagen("Resources\\minimize.png", 20, 20));
		btnMinimizar.setBounds(700, 15, 35, 35);
		btnMinimizar.setActionCommand("minimizar");

		pesta�as = new JTabbedPane();
		pesta�as.setBounds(10, 50, 780, 630);

		registro = new Pesta�aRegistro();
		clasificacion = new Pesta�aClasifcacion();
		consulta = new Pesta�aConsulta();

		pesta�as.addTab("Registro", Rutinas.AjustarImagen("Resources\\registro_icon[nuevo].png", 20, 20), registro);
		pesta�as.addTab("Clasificaci�n", Rutinas.AjustarImagen("Resources\\clasificacion_icon.png", 18, 18),
				clasificacion);
		pesta�as.addTab("Consulta", Rutinas.AjustarImagen("Resources\\consulta_icon.png", 22, 22), consulta);
		pesta�as.setSelectedIndex(2); //Empieza en el tab establecido
		
		btnModoOscuro = new JToggleButton("Modo claro (BETA)");
		btnModoOscuro.setSelected(true);
		btnModoOscuro.setBounds(300, 640, 200, 50);

		panelSuperior.add(lblTitulo);
		panelSuperior.add(btnInfo);
		panelSuperior.add(btnCerrar);
		panelSuperior.add(btnMinimizar);

		hazFrame();
	}

	private void hazFrame() {
		setSize(800, 700);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
		setIconImage(Rutinas.AjustarImagen("Resources\\cow.png", 100, 100).getImage());
		setLayout(null);

		add(btnModoOscuro);
		add(pesta�as);
		add(panelSuperior);
	}

	public void setControlador(ControladorTitleBar controladorTitle) {
		btnModoOscuro.addActionListener(new ControladorModoOscuro(this));
		btnInfo.addActionListener(controladorTitle);
		btnCerrar.addActionListener(controladorTitle);
		btnMinimizar.addActionListener(controladorTitle);

		FrameDragListener frameDragListener = new FrameDragListener(this);
		addMouseListener(frameDragListener);
		addMouseMotionListener(frameDragListener);
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	public void modoOscuro(boolean oscuro) throws UnsupportedLookAndFeelException {
		if (oscuro) {
			UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
			btnModoOscuro.setText("Modo claro (BETA)");
		} else {
			UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
			btnModoOscuro.setText("Modo oscuro (BETA)");
		}
		SwingUtilities.updateComponentTreeUI(this);
		actualizaColores();
	}

	private void actualizaColores() {
		registro.actualizar();
	}

	public static class FrameDragListener extends MouseAdapter {

		private final JFrame frame;
		private Point mouseDownCompCoords = null;

		public FrameDragListener(JFrame frame) {
			this.frame = frame;
		}

		public void mouseReleased(MouseEvent e) {
			mouseDownCompCoords = null;
		}

		public void mousePressed(MouseEvent e) {
			mouseDownCompCoords = e.getPoint();
		}

		public void mouseDragged(MouseEvent e) {
			Point currCoords = e.getLocationOnScreen();
			frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
		}
	}

}
