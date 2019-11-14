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
import material.componentes.MaterialColor;
import material.componentes.MaterialPanel;
import material.extras.Rutinas;
import mdlaf.*;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;

public class VentanaPrincipal extends JFrame {

	private JTabbedPane pesta�as;
	private JToggleButton btnModoOscuro;
	private JButton btnCerrar, btnMinimizar, btnInfo;
	private MaterialPanel panelSuperior;
	public JLabel lblTitulo;

	public Pesta�aRegistro registro;
	public Pesta�aClasifcacion clasificacion;
	public Pesta�aConsulta consulta;
	public Pesta�aSacrificios sacrificios;
	public Pesta�aCuidados cuidados;
	public Pesta�aSensores sensores;

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

		pesta�as = new JTabbedPane();

		pesta�as.setBounds(10, 50, 780, 730);

		registro = new Pesta�aRegistro();
		clasificacion = new Pesta�aClasifcacion();
		consulta = new Pesta�aConsulta();
		sacrificios= new Pesta�aSacrificios();
		cuidados = new Pesta�aCuidados();
		sensores= new Pesta�aSensores();

		pesta�as.addTab("", Rutinas.AjustarImagen("Resources\\registro_icon[nuevo].png", 20, 20), registro);
		pesta�as.addTab("", Rutinas.AjustarImagen("Resources\\clasificacion_icon.png", 18, 18),
				clasificacion);
		pesta�as.addTab("", Rutinas.AjustarImagen("Resources\\consulta_icon.png", 22, 22), consulta);
		pesta�as.addTab("", Rutinas.AjustarImagen("Resources\\cuidados_icon.png", 22, 22), cuidados);
		pesta�as.addTab("", Rutinas.AjustarImagen("Resources\\sacrificio_icon.png", 26, 26), sacrificios);
		pesta�as.addTab("", Rutinas.AjustarImagen("Resources\\sensor_icon.png", 26, 26), sensores);
		pesta�as.setToolTipTextAt(0, "Registro");
		pesta�as.setToolTipTextAt(1, "Clasificaci�n");
		pesta�as.setToolTipTextAt(2, "Consulta");
		pesta�as.setToolTipTextAt(3, "Cuidados");
		pesta�as.setToolTipTextAt(4, "Sacrificios");
		pesta�as.setToolTipTextAt(5, "Sensores");
		
//		pesta�as.setSelectedIndex(2); //Empieza en el tab establecido
		
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
//		setAlwaysOnTop(true);
		setBackground(new Color(40, 65, 91, 240));

		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
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
