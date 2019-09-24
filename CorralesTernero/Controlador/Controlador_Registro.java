package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.ModeloRegistro;
import Vista.VentanaPrincipal;

public class Controlador_Registro implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloRegistro modelo;

	public Controlador_Registro(VentanaPrincipal vista, ModeloRegistro modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread() {
			public void run() {
				try {
					vista.registro.bar.setVisible(true);

					int id = Integer.parseInt(vista.registro.txtIdCria.getText());
					int peso = Integer.parseInt(vista.registro.txtPesoCria.getText());
					String colorMusculo = vista.registro.txtColorCria.getText();
					int porcentajeGrasa = Integer.parseInt(vista.registro.txtGrasaCria.getText());

					boolean estado = modelo.registrarCria(id, peso, colorMusculo, porcentajeGrasa);

					if (estado) {
						vista.registro.mensaje("¡Cría insertada con éxito!", false);
						vista.consulta.btnRefrescar.doClick(); // Actualizar tabla de consultas
					} else {
						vista.registro.mensaje("Inserción de ID duplicada", true);
					}
				} catch (Exception e2) {
					vista.registro.mensaje("Hubo un error...", true);
				}
				vista.registro.bar.setVisible(false);
			}
		}.start();
	}
}
