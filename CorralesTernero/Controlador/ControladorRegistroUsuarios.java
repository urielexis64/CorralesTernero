package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.ModeloRegistroUsuarios;
import Vista.VentanaPrincipal;
import material.extras.AccionComponente;

public class ControladorRegistroUsuarios implements ActionListener {
	private VentanaPrincipal vista;
	private ModeloRegistroUsuarios modelo;

	public ControladorRegistroUsuarios(VentanaPrincipal vista, ModeloRegistroUsuarios modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.registroUsuarios.btnLimpiar) {
			vista.registroUsuarios.limpiar();
			return;
		}

		if (vista.registroUsuarios.verificarCampos()) {
			if (!vista.registroUsuarios.confirmaContraseña()) {
				vista.registroUsuarios.showMessage("La confirmación de contraseña no coincide.", true);
				vista.registroUsuarios.txtConfirmaContra.selectAll();
				return;
			}
			StringBuffer permisos = new StringBuffer();
			permisos.append((vista.registroUsuarios.checkRegistro.isSelected() ? 1 : 0) + "");
			permisos.append((vista.registroUsuarios.checkClasificacion.isSelected() ? 1 : 0) + "");
			permisos.append((vista.registroUsuarios.checkConsulta.isSelected() ? 1 : 0) + "");
			permisos.append((vista.registroUsuarios.checkCuidados.isSelected() ? 1 : 0) + "");
			permisos.append((vista.registroUsuarios.checkSacrificios.isSelected() ? 1 : 0) + "");
			permisos.append((vista.registroUsuarios.checkSensores.isSelected() ? 1 : 0) + "");
			permisos.append((vista.registroUsuarios.checkInforme.isSelected() ? 1 : 0) + "");
			permisos.append((vista.registroUsuarios.checkLog.isSelected() ? 1 : 0) + "");
			permisos.append((vista.registroUsuarios.checkSigProceso.isSelected() ? 1 : 0) + "");

			if(permisos.toString().equals("000000000")) {
				vista.registroUsuarios.showMessage("Debe activar al menos un permiso para continuar", true);
				AccionComponente.sacudir(vista.registroUsuarios.panelPermisos, 500, 50);
				return;
			}
			
			String nombre = vista.registroUsuarios.txtNombreUsuario.getText().trim();
			String correo = vista.registroUsuarios.txtCorreoUsuario.getText().trim();
			String contra = vista.registroUsuarios.txtContraUsuario.getText().trim();

			if (modelo.registrarUsuario(nombre, correo, contra, permisos.toString())) {
				vista.registroUsuarios.showMessage("Usuario registrado exitosamente", false);
				vista.registroUsuarios.limpiar();
			}else
				vista.registroUsuarios.showMessage("Error al registrar usuario", true);

		}

	}

}
