package extras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Modelo.ConexionBDSingleton;
import Modelo.ModeloCorreoElectronico;

public class ServicioCorreo extends JFrame implements ActionListener {
	private Timer t;
	private Connection conexion;

	public ServicioCorreo() {
		login();

		conexion = ConexionBDSingleton.getConexion();
		t = new Timer(3_600_000, this);
		t.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
	}

	private void login() {
		ConexionBDSingleton.host = "LOCALHOST";
		ConexionBDSingleton.port = "1433";
		ConexionBDSingleton.databaseName = "CORRALESTERNERO";
		ConexionBDSingleton.user = "sa";
		ConexionBDSingleton.pwd = "123";

		if (ConexionBDSingleton.getConexion() == null) {
			JOptionPane.showMessageDialog(null, "ERROR AL CONECTARSE A LA BASE DE DATOS", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Statement st = null;
		try {
			st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM [Vw_Promedio_Temperatura]");

			String asunto = "SE REPORTARON CRÍAS PROPENSAS A ENFERMARSE";
			StringBuffer cuerpo = new StringBuffer();

			while (rs.next()) {
				cuerpo.append("ID CRIA: " + rs.getInt(1) + "\nPromedio de temperatura en las últimas 4 horas: "
						+ rs.getInt(2) + "\n-----------------------------------------------------------------------------------\n");
			}
			if (!cuerpo.toString().equals("")) {
				ModeloCorreoElectronico.enviarCorreos(asunto, cuerpo.toString());
				JOptionPane.showMessageDialog(null, "Correo enviado con éxito");
			}else {
				JOptionPane.showMessageDialog(null, "Correo no enviado (No hay propensas)");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERROR AL ENVIAR");
		}
	}

	public static void main(String[] args) {
		new ServicioCorreo();
	}
}
