package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import Controlador.ControladorModoOscuro;
import Controlador.ControladorTitleBar;
import extras.LabelColores;
import material.componentes.MaterialColor;
import material.componentes.MaterialPanel;
import material.extras.Rutinas;
import mdlaf.*;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;

public class VentanaPrincipal extends JFrame {

	private JTabbedPane pestañas;
	private JToggleButton btnModoOscuro;
	private JButton btnCerrar, btnMinimizar, btnInfo, btnConfig;
	private MaterialPanel panelSuperior;
	private LabelColores lblTitulo;

	public PestañaRegistro registro;
	public PestañaClasifcacion clasificacion;
	public PestañaConsulta consulta;
	public PestañaSacrificios sacrificios;
	public PestañaCuidados cuidados;
	public PestañaSensores sensores;
	public PestañaLog log;
	public PestañaSigProceso sigProceso;
	public PestañaInformes informes;

	public VentanaPrincipal() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		panelSuperior = new MaterialPanel();
		panelSuperior.setBounds(0, 0, 800, 75);
		panelSuperior.setLayout(null);

		lblTitulo = new LabelColores("Corrales Ternero");
		lblTitulo.setFont(new Font("Forte", Font.PLAIN, 20));
		lblTitulo.setIcon(Rutinas.AjustarImagen("Resources\\cow.png", 30, 30));
		lblTitulo.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTitulo.setBounds(25, 12, 190, 40);
		lblTitulo.setAnimado(true);

		btnInfo = new JButton(Rutinas.AjustarImagen("Resources\\info.png", 20, 20));
		btnInfo.setBounds(620, 15, 35, 35);
		btnInfo.setActionCommand("info");
		btnInfo.setToolTipText("Acerca de...");
		btnConfig = new JButton(Rutinas.AjustarImagen("Resources\\settings.png", 25, 25));
		btnConfig.setBounds(660, 15, 35, 35);
		btnConfig.setActionCommand("config");
		btnConfig.setToolTipText("Ajustes");
		btnMinimizar = new JButton(Rutinas.AjustarImagen("Resources\\minimize.png", 20, 20));
		btnMinimizar.setBounds(700, 15, 35, 35);
		btnMinimizar.setActionCommand("minimizar");
		btnMinimizar.setToolTipText("Minimizar");
		btnCerrar = new JButton(Rutinas.AjustarImagen("Resources\\close.png", 20, 20));
		btnCerrar.setBounds(740, 15, 35, 35);
		btnCerrar.setActionCommand("cerrar");
		btnCerrar.setToolTipText("Cerrar");

		pestañas = new JTabbedPane();
		pestañas.setBounds(10, 50, 780, 730);

		registro = new PestañaRegistro();
		clasificacion = new PestañaClasifcacion();
		consulta = new PestañaConsulta();
		sacrificios = new PestañaSacrificios();
		cuidados = new PestañaCuidados();
		sensores = new PestañaSensores();
		log = new PestañaLog();
		sigProceso= new PestañaSigProceso();
		informes= new PestañaInformes();

		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\registro_icon[nuevo].png", 20, 20), registro);
		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\clasificacion_icon.png", 18, 18), clasificacion);
		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\consulta_icon.png", 22, 22), consulta);
		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\cuidados_icon.png", 22, 22), cuidados);
		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\sacrificio_icon.png", 26, 26), sacrificios);
		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\sensor_icon.png", 26, 26), sensores);
		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\log_icon.png", 26, 26), log);
		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\informes_icon.png", 30, 30), informes);
		pestañas.addTab("", Rutinas.AjustarImagen("Resources\\sigProceso_icon.png", 30, 30), sigProceso);
		pestañas.setToolTipTextAt(0, "Registro");
		pestañas.setToolTipTextAt(1, "Clasificación");
		pestañas.setToolTipTextAt(2, "Consulta");
		pestañas.setToolTipTextAt(3, "Cuidados");
		pestañas.setToolTipTextAt(4, "Sacrificios");
		pestañas.setToolTipTextAt(5, "Sensores");
		pestañas.setToolTipTextAt(6, "Log de acciones");
		pestañas.setToolTipTextAt(7, "Informes");
		pestañas.setToolTipTextAt(8, "Siguiente proceso");

//		pestañas.setSelectedIndex(2); //Empieza en el tab establecido

		btnModoOscuro = new JToggleButton("Modo claro (BETA)");
		btnModoOscuro.setSelected(true);
		btnModoOscuro.setBackground(new Color(40, 65, 91, 0));
		btnModoOscuro.setBounds(300, 740, 200, 50);

		panelSuperior.add(lblTitulo);
		panelSuperior.add(btnInfo);
		panelSuperior.add(btnConfig);
		panelSuperior.add(btnCerrar);
		panelSuperior.add(btnMinimizar);

		hazFrame();
	}

	private void hazFrame() {
		setSize(800, 800);
		setResizable(false);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(getWidth() / 2, getHeight() / 2, 1, 1, 30, 30));
		setLocationRelativeTo(null);
//		setAlwaysOnTop(true);
		setBackground(new Color(40, 65, 91, 240));

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
		pestañas.addChangeListener(controladorTitle);
		
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

	public void setVisible(boolean b) {
		super.setVisible(true);
		new Thread() {
			int w = 0, h = 0;
			public void run() {
				while (w < getWidth()) {
					setShape(new RoundRectangle2D.Double(getWidth() / 2 - w / 2 - 20, getHeight() / 2 - h / 2 - 20,
							w += 40, h += 40, 30, 30));
					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
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

