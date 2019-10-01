package Vista;

import java.awt.Color;
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
import herramientas.Rutinas;
import mdlaf.*;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;

public class VentanaPrincipal extends JFrame {

	private JTabbedPane pestañas;
	private JToggleButton btnModoOscuro;
	private JButton btnCerrar, btnMinimizar, btnInfo;
	private MaterialPanel panelSuperior;
	private JLabel lblTitulo;

	public PestañaRegistro registro;
	public PestañaClasifcacion clasificacion;
	public PestañaConsulta consulta;

	public VentanaPrincipal() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		panelSuperior = new MaterialPanel();
		panelSuperior.setBounds(0, 0, 800, 75);
		panelSuperior.setLayout(null);

		lblTitulo = new JLabel("Corrales Ternero");
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

		pestañas = new JTabbedPane();

		pestañas.setBounds(10, 50, 780, 730);

		registro = new PestañaRegistro();
		clasificacion = new PestañaClasifcacion();
		consulta = new PestañaConsulta();

		pestañas.addTab("Registro", Rutinas.AjustarImagen("Resources\\registro_icon[nuevo].png", 20, 20), registro);
		pestañas.addTab("Clasificación", Rutinas.AjustarImagen("Resources\\clasificacion_icon.png", 18, 18),
				clasificacion);
		pestañas.addTab("Consulta", Rutinas.AjustarImagen("Resources\\consulta_icon.png", 22, 22), consulta);
//		pestañas.setSelectedIndex(2); //Empieza en el tab establecido

		btnModoOscuro = new JToggleButton("Modo claro (BETA)");
		btnModoOscuro.setSelected(true);
		btnModoOscuro.setBackground(new Color(40, 65, 91, 0));
		btnModoOscuro.setBounds(300, 740, 200, 50);

		panelSuperior.add(lblTitulo);
		panelSuperior.add(btnInfo);
		panelSuperior.add(btnCerrar);
		panelSuperior.add(btnMinimizar);

		hazFrame();
	}

	private void hazFrame() {
		setSize(800, 800);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBackground(new Color(40, 65, 91, 240));

		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
		setIconImage(Rutinas.AjustarImagen("Resources\\cow.png", 100, 100).getImage());
		setLayout(null);

		add(btnModoOscuro);
		add(pestañas);
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

	public void modoOscuro(boolean oscuro) throws UnsupportedLookAndFeelException {
		if (oscuro) {
			UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
			btnModoOscuro.setText("Modo claro (BETA)");
		} else {
			UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
			btnModoOscuro.setText("Modo oscuro (BETA)");
		}
		SwingUtilities.updateComponentTreeUI(this);
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
