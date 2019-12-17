package Vista;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import material.componentes.MaterialColor;
import material.extras.Rutinas;

public class PestañaSensores extends JTabbedPane {

	private JLabel lblTabAsignacion, lblTabEstado, lblRegistro;
	public PestañaEstadoSensores estado;
	public PestañaAsignacionSensores asignacion;
	public PestañaRegistroSensores registro;
	
	public PestañaSensores() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		estado= new PestañaEstadoSensores();
		asignacion= new PestañaAsignacionSensores();
		registro= new PestañaRegistroSensores();		
		
		lblTabAsignacion= new JLabel("Asignación", Rutinas.AjustarImagen("Resources\\asignacion_sensores.png", 30, 30), SwingConstants.LEFT);
		lblTabAsignacion.setPreferredSize(new Dimension(110, 50));
		lblTabAsignacion.setBackground(MaterialColor.TRANSPARENT);
		lblTabEstado= new JLabel("Estado", Rutinas.AjustarImagen("Resources\\estado_sensores.png", 30, 30), SwingConstants.LEFT);
		lblTabEstado.setPreferredSize(new Dimension(110, 50));
		lblTabEstado.setBackground(MaterialColor.TRANSPARENT);
		lblRegistro= new JLabel("Registro", Rutinas.AjustarImagen("Resources\\plus.png", 30, 30), SwingConstants.LEFT);
		lblRegistro.setPreferredSize(new Dimension(110, 50));
		lblRegistro.setBackground(MaterialColor.TRANSPARENT);

		
		setTabPlacement(JTabbedPane.LEFT);
		addTab("", asignacion);
		addTab("", estado);
		addTab("", registro);
		setTabComponentAt(0, lblTabAsignacion);
		setTabComponentAt(1, lblTabEstado);
		setTabComponentAt(2, lblRegistro);
	}

	
}