package Vista;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import material.componentes.MaterialColor;
import material.extras.Rutinas;

public class Pesta�aSensores extends JTabbedPane {

	private JLabel lblTabAsignacion, lblTabEstado, lblRegistro;
	public Pesta�aEstadoSensores estado;
	public Pesta�aAsignacionSensores asignacion;
	public Pesta�aRegistroSensores registro;
	
	public Pesta�aSensores() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		estado= new Pesta�aEstadoSensores();
		asignacion= new Pesta�aAsignacionSensores();
		registro= new Pesta�aRegistroSensores();		
		
		lblTabAsignacion= new JLabel("Asignaci�n", Rutinas.AjustarImagen("Resources\\asignacion_sensores.png", 30, 30), SwingConstants.LEFT);
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