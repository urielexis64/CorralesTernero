package Controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import EjecutarApp.Ejecutar;
import Modelo.ModeloLoginUsuario;
import Vista.LoginUsuario;
import material.extras.AccionComponente;

public class ControladorLoginUsuario implements ActionListener {

	private LoginUsuario vista;
	private ModeloLoginUsuario modelo;

	public ControladorLoginUsuario(LoginUsuario vista, ModeloLoginUsuario modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.btnCerrar)
			System.exit(0);

		new Thread(() -> {
			vista.bar.setVisible(true);

			String correo = vista.txtCorreo.getText();
			String contra = vista.txtContra.getText();

			String nombreUsuario = modelo.iniciarSesion(correo, contra);

			if (nombreUsuario != null) {
				AccionComponente.mover(vista.txtCorreo, new Point(-400, vista.txtCorreo.getY()), 60, 6);
				AccionComponente.mover(vista.txtContra, new Point(700, vista.txtContra.getY()), 60, 6);
				AccionComponente.mover(vista.lblFoto, new Point(vista.lblFoto.getX(), 115), 90, 6);

				try {
					Thread.sleep(1300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				vista.showMessage("Bienvenido, " + nombreUsuario, false);
				Ejecutar.iniciaAplicacion(nombreUsuario);
			} else {
				AccionComponente.sacudir(vista.txtCorreo, 500, 50);
				AccionComponente.sacudir(vista.txtContra, 500, 50);
				vista.showMessage("Correo y/o contraseña incorrectos.", true);
			}
			vista.bar.setVisible(false);
		}).start();
	}
}
