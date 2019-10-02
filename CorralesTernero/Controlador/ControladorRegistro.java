package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.ModeloRegistro;
import Vista.VentanaPrincipal;

public class ControladorRegistro implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloRegistro modelo;

	public ControladorRegistro(VentanaPrincipal vista, ModeloRegistro modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.registro.btnLimpiar) { // Botón limpiar
			vista.registro.limpiar();
			return;
		}

		if (e.getSource() == vista.registro.btnAleatorio) { // Botón Aleatorio
			vista.registro.procesoAleatorio();
			return;
		}

		procesoRegistrarCria(); // Botón Registrar
	}

	private void procesoRegistrarCria() {
		if (!vista.registro.verificarCampos()) { // Verificamos que todos los campos tengan información
			vista.registro.showMessage("Llene todos los campos.", true);
			return;
		}
		
		new Thread(() -> { //Inicia proceso de registro
			vista.registro.bar.setVisible(true);

			int id = Integer.parseInt(vista.registro.txtIdCria.getText());
			int peso = Integer.parseInt(vista.registro.txtPesoCria.getText());
			String colorMusculo = vista.registro.txtColorCria.getText();
			int porcentajeGrasa = Integer.parseInt(vista.registro.txtGrasaCria.getText());
			String fecha_entrada = vista.registro.calendario.getText();

			boolean estado = modelo.registrarCria(id, peso, colorMusculo, porcentajeGrasa, fecha_entrada);

			if (estado) {
				vista.registro.showMessage("¡Cría insertada con éxito!", false);
				vista.consulta.btnRefrescar.doClick(); // Actualizar tabla de consultas
			} else {
				vista.registro.showMessage("Inserción de ID duplicada", true);
			}
			vista.registro.bar.setVisible(false);
			vista.registro.limpiar();
		}).start();
	}
}
