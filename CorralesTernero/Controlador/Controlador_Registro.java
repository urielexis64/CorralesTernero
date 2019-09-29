package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import Modelo.ModeloRegistro;
import Vista.VentanaPrincipal;
import mdlaf.utils.MaterialColors;
import misHerramientas.Rutinas;

public class Controlador_Registro implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloRegistro modelo;

	private String colores[] = { "Azul", "Verde", "Rojo", "Amarillo", "Morado", "Naranja", "Gris", "Café", "Blanco",
			"Rosa" };

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

			int año = Rutinas.nextInt(2000, 2020);
			int mes = Rutinas.nextInt(1, 12);
			int dia = mes == 2 ? Rutinas.nextInt(1, 28)
					: mes == 4 || mes == 6 || mes == 9 || mes == 11 ? Rutinas.nextInt(1, 30) : Rutinas.nextInt(1, 31);
			vista.registro.calendario.setDate(LocalDate.of(año, mes, dia));
			return;
		}

		if (!verificarCampos()) {
			vista.registro.showMessage("Llene todos los campos.", true);
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
					String fecha_entrada = vista.registro.calendario.getText();

					boolean estado = modelo.registrarCria(id, peso, colorMusculo, porcentajeGrasa, fecha_entrada);

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

	private boolean verificarCampos() {
		boolean estado = true;
		if (vista.registro.txtIdCria.getText().equals("")) {
			estado = false;
			vista.registro.txtIdCria.requestFocus();
		} else if (vista.registro.txtPesoCria.getText().equals("")) {
			estado = false;
			vista.registro.txtPesoCria.requestFocus();
		} else if (vista.registro.txtColorCria.getText().trim().equals("")) {
			estado = false;
			vista.registro.txtColorCria.requestFocus();
		} else if (vista.registro.txtGrasaCria.getText().equals("")) {
			estado = false;
			vista.registro.txtGrasaCria.requestFocus();
		} else if (vista.registro.calendario.getText().equals("")) {
			estado = false;
			vista.registro.calendario.getComponentDateTextField().setBackground(MaterialColors.RED_400);
		}
		return estado;
	}
}
