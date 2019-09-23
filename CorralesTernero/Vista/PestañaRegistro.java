package Vista;

import java.awt.*;
import javax.swing.*;

import Controlador.Controlador_Registro;
import EjecutarApp.ToastMessage;
import de.craften.ui.swingmaterial.*;
import de.craften.ui.swingmaterial.MaterialButton.Type;
import mdlaf.utils.MaterialColors;

public class PestañaRegistro extends JPanel {

	public MaterialTextField txtIdCria, txtPesoCria, txtColorCria, txtGrasaCria;
	private MaterialButton btnRegistrarCria;
	private MaterialPanel panel;
	public MaterialProgressSpinner bar;
	public ToastMessage mensaje;

	public PestañaRegistro() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);

		panel = new MaterialPanel();
		panel.setElevation(2);
		panel.setLayout(null);
		panel.setBounds(40, 25, 700, 500);

		txtIdCria = new MaterialTextField();
		txtIdCria.setBounds(100, 20, 500, 80);
		txtIdCria.setLabel("ID");
		txtIdCria.setAccent(MaterialColors.YELLOW_300);
		txtIdCria.setForeground(MaterialColors.WHITE);
		txtIdCria.setBackground(MaterialColor.TRANSPARENT);
		txtIdCria.setCaretColor(Color.WHITE);
		
		txtPesoCria = new MaterialTextField();
		txtPesoCria.setBounds(100, 110, 500, 80);
		txtPesoCria.setLabel("Peso de la cría");
		txtPesoCria.setAccent(MaterialColors.YELLOW_300);
		txtPesoCria.setForeground(MaterialColors.WHITE);
		txtPesoCria.setBackground(MaterialColor.TRANSPARENT);
		txtPesoCria.setCaretColor(Color.WHITE);

		txtColorCria = new MaterialTextField();
		txtColorCria.setBounds(100, 200, 500, 80);
		txtColorCria.setLabel("Color de músculo");
		txtColorCria.setAccent(MaterialColors.YELLOW_300);
		txtColorCria.setForeground(MaterialColors.WHITE);
		txtColorCria.setBackground(MaterialColor.TRANSPARENT);
		txtColorCria.setCaretColor(Color.WHITE);

		txtGrasaCria = new MaterialTextField();
		txtGrasaCria.setBounds(100, 290, 500, 80);
		txtGrasaCria.setLabel("Porcentaje de grasa");
		txtGrasaCria.setAccent(MaterialColors.YELLOW_300);
		txtGrasaCria.setForeground(MaterialColors.WHITE);
		txtGrasaCria.setBackground(MaterialColor.TRANSPARENT);
		txtGrasaCria.setCaretColor(Color.WHITE);

		btnRegistrarCria = new MaterialButton();
		btnRegistrarCria.setText("REGISTRAR CRÍA");
		btnRegistrarCria.setRippleColor(MaterialColor.GREEN_800);
		btnRegistrarCria.setBackground(MaterialColors.GREEN_300);
		btnRegistrarCria.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegistrarCria.setBorderRadius(6);
		btnRegistrarCria.setType(Type.RAISED);
		btnRegistrarCria.setBounds(250, 380, 200, 70);

		bar = new MaterialProgressSpinner();
		bar.setBounds(300, 200, 100, 100);
		bar.setVisible(false);

		panel.add(bar);
		panel.add(btnRegistrarCria);
		panel.add(txtIdCria);
		panel.add(txtPesoCria);
		panel.add(txtColorCria);
		panel.add(txtGrasaCria);

		add(panel);
	}

	public void mensaje(String msg, boolean error) {
		mensaje = new ToastMessage(getLocationOnScreen().x + 275, getLocationOnScreen().y + 200);
		if (error)
			mensaje.setInfo(msg, MaterialColors.RED_500);
		else
			mensaje.setInfo(msg, MaterialColors.GREEN_600);

		mensaje.showtoast();
	}

	public void actualizar() {
		txtIdCria.setAccent(MaterialColors.YELLOW_300);
		txtIdCria.setForeground(MaterialColors.WHITE);
		txtIdCria.setBackground(MaterialColor.TRANSPARENT);

		txtPesoCria.setAccent(MaterialColors.GREEN_600);
		txtPesoCria.setForeground(MaterialColors.BLACK);
		txtPesoCria.setBackground(MaterialColor.TRANSPARENT);

		txtColorCria.setAccent(MaterialColors.GREEN_600);
		txtColorCria.setForeground(MaterialColors.BLACK);
		txtColorCria.setBackground(MaterialColor.TRANSPARENT);

		txtGrasaCria.setAccent(MaterialColors.GREEN_600);
		txtGrasaCria.setForeground(MaterialColors.BLACK);
		txtGrasaCria.setBackground(MaterialColor.TRANSPARENT);
	}

	public void setControlador(Controlador_Registro controlador) {
		btnRegistrarCria.addActionListener(controlador);
	}
}
