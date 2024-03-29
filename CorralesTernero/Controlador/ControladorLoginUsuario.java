package Controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import EjecutarApp.Ejecutar;
import Modelo.ModeloLoginUsuario;
import Vista.LoginUsuario;
import material.extras.AccionComponente;

public class ControladorLoginUsuario implements ActionListener {

	private LoginUsuario vista;
	private ModeloLoginUsuario modelo;
	private boolean ojoCerrado;
	private char echoChar;

	public ControladorLoginUsuario(LoginUsuario vista, ModeloLoginUsuario modelo) {
		this.vista = vista;
		this.modelo = modelo;
		this.ojoCerrado = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.btnCerrar)
			System.exit(0);

		if (e.getSource() == vista.btnMinimizar) {
			vista.setState(JFrame.ICONIFIED);
			return;
		}

		if (e.getSource() == vista.btnOjo) {
			if (echoChar == '\u0000')
				echoChar = vista.txtContra.getEchoChar();

			if (ojoCerrado) {
				vista.btnOjo.setIcon(vista.imgOjo);
				vista.txtContra.setEchoChar((char) 0);
			} else {
				vista.btnOjo.setIcon(vista.imgOjoCerrado);
				vista.txtContra.setEchoChar(echoChar);
			}
			ojoCerrado = !ojoCerrado;
			return;
		}

		if (e.getSource() == vista.txtCorreo) {
			vista.txtContra.requestFocus();
			return;
		}

		new Thread(() -> {
			vista.bar.setVisible(true);

			String correo = vista.txtCorreo.getText();
			String contra = vista.txtContra.getText();

			Object datosUsuario[] = modelo.iniciarSesion(correo, contra);

			if (datosUsuario != null) {
				if(Integer.parseInt(datosUsuario[0].toString())==-1) {
					AccionComponente.sacudir(vista.txtCorreo, 500, 50);
					AccionComponente.sacudir(vista.txtContra, 500, 50);
					vista.showMessage("Ya hay una sesi�n activa con esa cuenta.", true);
					vista.bar.setVisible(false);
					return;
				}
				
				AccionComponente.mover(vista.txtCorreo, new Point(-400, vista.txtCorreo.getY()), 60, 6);
				AccionComponente.mover(vista.txtContra, new Point(700, vista.txtContra.getY()), 60, 6);
				AccionComponente.mover(vista.btnOjo, new Point(700, vista.txtContra.getY()), 60, 6);
				AccionComponente.mover(vista.lblFoto, new Point(vista.lblFoto.getX(), 115), 90, 6);
				AccionComponente.transparencia(vista.btnEntrar, 1300, 7);
				try {
					Thread.sleep(1300);
				} catch (InterruptedException e1) {
				}
				vista.showMessage("Bienvenido, " + datosUsuario[1], false);
				Ejecutar.iniciaAplicacion(Integer.parseInt(datosUsuario[0].toString()), datosUsuario[1].toString(),
						datosUsuario[2].toString());

			} else {
				AccionComponente.sacudir(vista.txtCorreo, 500, 50);
				AccionComponente.sacudir(vista.txtContra, 500, 50);
				vista.showMessage("Correo y/o contrase�a incorrectos.", true);
			}
			vista.bar.setVisible(false);
		}).start();
	}
}
