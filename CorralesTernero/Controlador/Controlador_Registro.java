package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.ModeloRegistro;
import Vista.VentanaPrincipal;
import misHerramientas.Rutinas;

public class Controlador_Registro implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloRegistro modelo;

	private String colores[] = { "Azul", "Verde", "Rojo", "Amarillo", "Morado", "Naranja", "Gris", "Café", "Blanco" };

	public Controlador_Registro(VentanaPrincipal vista, ModeloRegistro modelo) {
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
			vista.registro.txtIdCria.setText(Rutinas.nextInt(1, 1000000) + "");
			vista.registro.txtPesoCria.setText(Rutinas.nextInt(20, 200) + "");
			vista.registro.txtColorCria.setText(colores[Rutinas.nextInt(0, 8)]);
			vista.registro.txtGrasaCria.setText(Rutinas.nextInt(1, 100) + "");
			return;
		}

		new Thread() { // Botón registrar
			public void run() {
				try {
					vista.registro.bar.setVisible(true);

					int id = Integer.parseInt(vista.registro.txtIdCria.getText());
					int peso = Integer.parseInt(vista.registro.txtPesoCria.getText());
					String colorMusculo = vista.registro.txtColorCria.getText();
					int porcentajeGrasa = Integer.parseInt(vista.registro.txtGrasaCria.getText());

					boolean estado = modelo.registrarCria(id, peso, colorMusculo, porcentajeGrasa);

					if (estado) {
						vista.registro.showMessage("¡Cría insertada con éxito!", false);
						vista.consulta.btnRefrescar.doClick(); // Actualizar tabla de consultas
					} else {
						vista.registro.showMessage("Inserción de ID duplicada", true);
					}
				} catch (Exception e2) {
					vista.registro.showMessage("Hubo un error...", true);
				}
				vista.registro.bar.setVisible(false);
				vista.registro.limpiar();
			}
		}.start();

	}
}
