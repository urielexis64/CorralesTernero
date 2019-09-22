package Vista;

import java.awt.BorderLayout;

import javax.swing.*;

import Controlador.ControladorModoOscuro;
import mdlaf.*;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import misHerramientas.Rutinas;

public class VentanaPrincipal extends JFrame {

	private JTabbedPane pestañas;
	private JToggleButton btnModoOscuro;

	public PestañaRegistro registro;
	public PestañaClasifcacion clasificacion;
	public PestañaConsulta consulta;

	public BarraMenu barraMenu;

	public VentanaPrincipal() {
		hazInterfaz();
		hazEscuchas();
	}

	private void hazInterfaz() {
		pestañas = new JTabbedPane();

		registro = new PestañaRegistro();
		clasificacion = new PestañaClasifcacion();
		consulta = new PestañaConsulta();

		pestañas.addTab("Registro", Rutinas.AjustarImagen("Resources\\registro_icon.png", 22, 22), registro);
		pestañas.addTab("Clasificación", Rutinas.AjustarImagen("Resources\\clasificacion_icon.png", 18, 18),
				clasificacion);
		pestañas.addTab("Consulta", Rutinas.AjustarImagen("Resources\\consulta_icon.png", 22, 22), consulta);
		barraMenu = new BarraMenu(this);

		btnModoOscuro = new JToggleButton("Modo oscuro (BETA)");

		hazFrame();
	}

	private void hazFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Corrales Ternero");
		setSize(800, 800);
		setResizable(true);
		setLocationRelativeTo(null);

		add(btnModoOscuro, BorderLayout.SOUTH);
		add(pestañas, BorderLayout.CENTER);
		add(barraMenu, BorderLayout.NORTH);
	}
	
	private void hazEscuchas() {
		btnModoOscuro.addActionListener(new ControladorModoOscuro(this));
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	public void modoOscuro(boolean oscuro) throws UnsupportedLookAndFeelException {
		if (!oscuro) {
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
}
