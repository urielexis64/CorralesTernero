package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import EjecutarApp.Ejecutar;
import Modelo.ModeloCorreoElectronico;
import Modelo.ModeloLog;
import Modelo.ModeloSensores;
import Vista.VentanaPrincipal;
import material.extras.AccionComponente;
import mdlaf.utils.MaterialColors;

public class ControladorSensores implements ActionListener {

	private VentanaPrincipal vista;
	private ModeloSensores modelo;
	private boolean simulando;

	public ControladorSensores(VentanaPrincipal vista, ModeloSensores modelo) {
		this.vista = vista;
		this.modelo = modelo;
		simulando = false;
		llenaTabla();
		modelo.randomSensores();
		Vector<String> ids = modelo.getIdsSensores();
		if (ids != null)
			vista.sensores.asignacion.llenaCombo(ids);
	}

	private void llenaTabla() {
		vista.sensores.estado.setTabla(modelo.getInfoSensores());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.sensores.estado.btnRefrescar) {
			llenaTabla();
			return;
		}
		if (e.getSource() == vista.sensores.estado.btnEnviarCorreo) {
			if (JOptionPane.showConfirmDialog(vista, "¿Desea enviar un correo electrónico?") == 0)
				procesoEnviarCorreo();
			return;
		}

		if (e.getSource() == vista.sensores.estado.timer) {
			modelo.randomSensores();
			vista.sensores.estado.btnRefrescar.doClick();
			return;
		}

		if (e.getSource() == vista.sensores.asignacion.btnRefrescar) {
			vista.sensores.asignacion.llenaCombo(modelo.getIdsSensores());
			return;
		}

		if (e.getSource() == vista.sensores.asignacion.btnAsignar) {
			if (vista.sensores.asignacion.comboSensores.getSelectedIndex() == 0) {
				if (vista.sensores.asignacion.comboSensores.getItemCount() == 1)
					vista.sensores.asignacion.showMessage("No hay sensores disponibles para asignar", true);
				else
					vista.sensores.asignacion.showMessage("Seleccione un sensor para continuar", true);
				return;
			}

			if (vista.sensores.asignacion.txtIdCria.getText().trim().equals("")) {
				vista.sensores.asignacion.showMessage("Ingrese el id de la cría", true);
				vista.sensores.asignacion.txtIdCria.requestFocus();
				return;
			}
			int idCria = 0;

			try {
				idCria = Integer.parseInt(vista.sensores.asignacion.txtIdCria.getText());
			} catch (Exception ex) {
				vista.sensores.asignacion.showMessage("Ingrese un valor válido", true);
				vista.sensores.asignacion.txtIdCria.selectAll();
				return;
			}

			String idSensor = vista.sensores.asignacion.comboSensores.getSelectedItem().toString();
			int respuesta = modelo.asignarSensor(idCria, idSensor);

			switch (respuesta) {
			case 0:
				vista.sensores.asignacion.showMessage("Sensor asignado exitosamente", false);
				vista.sensores.asignacion.btnRefrescar.doClick();
				ModeloLog.registraMovimiento(idCria, "Asignó sensor #" + idSensor, Ejecutar.NOMBRE_USUARIO);
				break;
			case 1:
				vista.sensores.asignacion.showMessage("La cría con el id: " + idCria + " no existe", true);
				break;
			case 2:
				vista.sensores.asignacion
						.showMessage("Solo se les puede asignar sensor a crías finas o crías finas sin sensor", true);
				break;
			default:
				vista.sensores.asignacion.showMessage("Hubo un error al asignar el sensor", true);
			}

			return;
		}

		if (e.getSource() == vista.sensores.registro.btnRegistrar) {
			if (vista.sensores.registro.comboEstados.getSelectedIndex() == 0) {
				vista.sensores.asignacion.showMessage("Seleccione un código de estado para continuar", true);
				vista.sensores.registro.comboEstados.requestFocus();
				return;
			}

			String idSensor = vista.sensores.registro.comboEstados.getSelectedItem().toString() + "-"
					+ vista.sensores.registro.txtIdSensor.getText();
			if (vista.sensores.registro.txtIdSensor.getText().length() != 6) {
				vista.sensores.asignacion.showMessage("El número de sensor debe estar compuesto por 6 dígitos", true);
				vista.sensores.registro.txtIdSensor.selectAll();
				AccionComponente.sacudir(vista.sensores.registro.txtIdSensor, 500, 10);
				return;
			}

			int respuesta = modelo.registrarSensor(idSensor);
			switch (respuesta) {
			case 0:
				vista.sensores.asignacion.showMessage("Sensor registrado con éxito", false);
				vista.sensores.asignacion.comboSensores.setSelectedIndex(0);
				vista.sensores.asignacion.txtIdCria.setText("");
				break;
			case 1:
				vista.sensores.asignacion.showMessage("Ya existe un sensor registrado con ese id", true);
				vista.sensores.asignacion.txtIdCria.selectAll();
				break;
			default:
				vista.sensores.asignacion.showMessage("Error al registrar...", true);
			}

			return;
		}

		if (!simulando) {
			vista.sensores.estado.timer.start();
			vista.sensores.estado.btnSimular.setBackground(MaterialColors.RED_300);
			simulando = true;
		} else {
			vista.sensores.estado.timer.stop();
			vista.sensores.estado.btnSimular.setBackground(MaterialColors.GREEN_300);
			simulando = false;
		}

	}

	private void procesoEnviarCorreo() {
		int tamanoTabla = vista.sensores.estado.tabla.getRowCount();
		if (tamanoTabla == 0) {
			vista.sensores.estado.showMessage("No se han asignado sensores aún", true);
			return;
		}
		Vector<Vector<String>> objetoSensor = new Vector<Vector<String>>();

		for (int i = 0; i < tamanoTabla; i++) {
			Vector<String> sensorActual = new Vector<String>();

			int temperatura = Integer.parseInt((vista.sensores.estado.tabla.getValueAt(i, 1) + "").split(" ")[0]);

			if (temperatura >= 45) {
				String id = vista.sensores.estado.tabla.getValueAt(i, 0) + "";
				String presionArterial = vista.sensores.estado.tabla.getValueAt(i, 2) + "";
				String ubicacion = vista.sensores.estado.tabla.getValueAt(i, 3) + "";

				sensorActual.add(id);
				sensorActual.add(temperatura + "");
				sensorActual.add(presionArterial);
				sensorActual.add(ubicacion);
				objetoSensor.add(sensorActual);
			}
		}

		if (objetoSensor.isEmpty()) {
			vista.sensores.estado.showMessage("Actualmente no hay crías propensas a enfermarse.", true);
			return;
		}

		String asunto = "SE REPORTARON CRÍAS PROPENSAS A ENFERMARSE";
		StringBuffer cuerpo = new StringBuffer();

		for (int i = 0; i < objetoSensor.size(); i++) {
			cuerpo.append("ID CRIA: " + objetoSensor.get(i).get(0) + "\nTemperatura: " + objetoSensor.get(i).get(1)
					+ " ºC\nPresión arterterial: " + objetoSensor.get(i).get(2) + "\nUbicación: "
					+ objetoSensor.get(i).get(3) + "\n---------------------------------------------------------\n");
		}
		new Thread(() -> {
			vista.sensores.estado.showMessage("Enviando...", false);
			vista.sensores.estado.bar.setVisible(true);
			try {
				ModeloCorreoElectronico.enviarCorreos(asunto, cuerpo.toString());
				vista.sensores.estado.showMessage("Correo enviado con éxito", false);
			} catch (Exception e) {
				vista.sensores.estado.showMessage("Error al enviar correo: " + e.getMessage(), true);
			}
			vista.sensores.estado.bar.setVisible(false);
		}).start();

	}

}
