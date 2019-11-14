package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import Modelo.ModeloCorreoElectronico;
import Modelo.ModeloSensores;
import Vista.VentanaPrincipal;

public class ControladorSensores implements ActionListener {

	private VentanaPrincipal vista;
	private ModeloSensores modelo;

	public ControladorSensores(VentanaPrincipal vista, ModeloSensores modelo) {
		this.vista = vista;
		this.modelo = modelo;
		llenaTabla();
		procesoAleatorioSensores();
	}

	private void procesoAleatorioSensores() {
		for (int i = 0; i < vista.sensores.tabla.getRowCount(); i++) {
			int idCria = Integer.parseInt(vista.sensores.tabla.getValueAt(i, 0) + "");
			modelo.randomSensores(idCria);
		}
	}

	private void llenaTabla() {
		vista.sensores.setTabla(modelo.getInfoSensores());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.sensores.btnRefrescar) {
			llenaTabla();
			return;
		}
		if (JOptionPane.showConfirmDialog(vista, "¿Desea enviar un correo electrónico?") == 0)
			procesoEnviarCorreo();
	}

	private void procesoEnviarCorreo() {
		int tamanoTabla = vista.sensores.tabla.getRowCount();
		if (tamanoTabla == 0)
			return;

		Vector<Vector<String>> objetoSensor = new Vector<Vector<String>>();

		for (int i = 0; i < tamanoTabla; i++) {
			Vector<String> sensorActual = new Vector<String>();

			int temperatura = Integer.parseInt((vista.sensores.tabla.getValueAt(i, 1) + "").split(" ")[0]);

			if (temperatura >= 45) {
				String id = vista.sensores.tabla.getValueAt(i, 0) + "";
				String presionArterial = vista.sensores.tabla.getValueAt(i, 2) + "";
				String ubicacion = vista.sensores.tabla.getValueAt(i, 3) + "";

				sensorActual.add(id);
				sensorActual.add(temperatura + "");
				sensorActual.add(presionArterial);
				sensorActual.add(ubicacion);
				objetoSensor.add(sensorActual);
			}
		}

		if (objetoSensor.size() == 0) {
			vista.sensores.showMessage("Actualmente no hay crías propensas a enfermarse.", true);
			return;
		}

		String asunto = "SE REPORTARON CRÍAS PROPENSAS A ENFERMARSE";
		StringBuffer cuerpo = new StringBuffer();

		for (int i = 0; i < objetoSensor.size(); i++) {
			cuerpo.append("ID CRIA: " + objetoSensor.get(i).get(0) + "\nTemperatura: " + objetoSensor.get(i).get(1)
					+ " ºC\nPresión arterterial: " + objetoSensor.get(i).get(2) + "\nUbicación"
					+ objetoSensor.get(i).get(3) + "\n-------------------------------------------------------\n");
		}
		new Thread(() -> {
			vista.sensores.bar.setVisible(true);
			try {
				ModeloCorreoElectronico.enviarCorreos(asunto, cuerpo.toString());
				vista.sensores.showMessage("Correo enviado con éxito", false);
			} catch (Exception e) {
				vista.sensores.showMessage("Error al enviar correo: " + e.getMessage(), true);
			}
			vista.sensores.bar.setVisible(false);
		}).start();

	}

}
