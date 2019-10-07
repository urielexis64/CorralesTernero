package Controlador;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
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

		int grasa = Integer.parseInt(vista.registro.txtGrasaCria.getText());
		if (grasa > 100) {
			vista.registro.showMessage("El porcentaje de grasa no puede ser mayor al 100 %", true);
			vista.registro.txtGrasaCria.requestFocus();
			vista.registro.txtGrasaCria.selectAll();
			return;
		}

		new Thread(() -> { // Inicia proceso de registro
			vista.registro.bar.setVisible(true);

			float peso = Float.parseFloat(vista.registro.txtPesoCria.getText());
			String colorMusculo = vista.registro.txtColorCria.getText();
			int porcentajeGrasa = Integer.parseInt(vista.registro.txtGrasaCria.getText());
			String fecha_entrada = vista.registro.calendario.getText();

			int idGenerado = modelo.registrarCria(peso, colorMusculo, porcentajeGrasa, fecha_entrada);

			if (idGenerado != -1) {
				JButton btnCopiar = new JButton("Copiar ID");
				btnCopiar.addActionListener(evt -> {
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(idGenerado + ""),
							null);
					vista.registro.showMessage("Texto copiado", false);
				});
				
				vista.registro.bar.setVisible(false);
				vista.registro.showMessage("¡Cría insertada con éxito!", false);
				vista.consulta.btnRefrescar.doClick(); // Actualizar tabla de consultas

				JOptionPane.showOptionDialog(vista, "ID generado: " + idGenerado, "Mensaje", 0, 0, null,
						new Object[] { btnCopiar, "Cerrar" }, "Cerrar");
			} else {
				vista.registro.bar.setVisible(false);
				vista.registro.showMessage("Hubo un error...", true);
			}

			vista.registro.limpiar();
		}).start();
	}
}
