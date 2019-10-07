package Vista;

import java.awt.*;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Controlador.ControladorModal;
import EjecutarApp.Ejecutar;
import de.craften.ui.swingmaterial.*;
import herramientas.ToastMessage;
import mdlaf.utils.MaterialColors;

public class ModalActualiza extends JDialog {
	public MaterialTextField txtPesoCria, txtColorCria, txtGrasaCria;
	public MaterialButton btnLimpiar, btnActualizar;
	private JLabel lblInfo;
	public int idActual;

	public ModalActualiza() {
		hazInterfaz();
	}

	public void hazInterfaz() {
		setSize(480, 400);
		setLocationRelativeTo(Ejecutar.getInstance());
		setResizable(false);
		setModal(true);
		setTitle("Editar cría");
		setLayout(null);

		lblInfo = new JLabel();
		lblInfo.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, -20, 480, 80);

		txtPesoCria = new MaterialTextField();
		txtPesoCria.setBounds(50, 60, 380, 70);
		txtPesoCria.setLabel("Peso de la cría");
		txtPesoCria.setUpperFilter(true);
		txtPesoCria.setAccent(MaterialColors.YELLOW_300);
		txtPesoCria.setForeground(MaterialColors.WHITE);
		txtPesoCria.setBackground(MaterialColor.TRANSPARENT);
		txtPesoCria.setCaretColor(Color.WHITE);
		txtPesoCria.setRegex(2);

		txtColorCria = new MaterialTextField();
		txtColorCria.setBounds(50, 140, 380, 70);
		txtColorCria.setLabel("Color de músculo");
		txtColorCria.setUpperFilter(true);
		txtColorCria.setAccent(MaterialColors.YELLOW_300);
		txtColorCria.setForeground(MaterialColors.WHITE);
		txtColorCria.setBackground(MaterialColor.TRANSPARENT);
		txtColorCria.setCaretColor(Color.WHITE);

		txtGrasaCria = new MaterialTextField();
		txtGrasaCria.setBounds(50, 220, 380, 70);
		txtGrasaCria.setLabel("Porcentaje de grasa");
		txtGrasaCria.setUpperFilter(true);
		txtGrasaCria.setAccent(MaterialColors.YELLOW_300);
		txtGrasaCria.setForeground(MaterialColors.WHITE);
		txtGrasaCria.setBackground(MaterialColor.TRANSPARENT);
		txtGrasaCria.setCaretColor(Color.WHITE);
		txtGrasaCria.setRegex(1);

		btnLimpiar = new MaterialButton();
		btnLimpiar.setText("Limpiar");
		btnLimpiar.setBounds(30, 300, 200, 60);
		btnLimpiar.setRippleColor(MaterialColor.GREEN_800);
		btnLimpiar.setBackground(MaterialColors.GREEN_300);
		btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBorderRadius(6);
		btnLimpiar.setType(MaterialButton.Type.RAISED);

		btnActualizar = new MaterialButton();
		btnActualizar.setText("Actualizar cría");
		btnActualizar.setBounds(250, 300, 200, 60);
		btnActualizar.setRippleColor(MaterialColor.GREEN_800);
		btnActualizar.setBackground(MaterialColors.GREEN_300);
		btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnActualizar.setBorderRadius(6);
		btnActualizar.setType(MaterialButton.Type.RAISED);

		add(lblInfo);
		add(txtPesoCria);
		add(txtColorCria);
		add(txtGrasaCria);
		add(btnActualizar);
		add(btnLimpiar);
	}

	public void setInfo(int id, String peso, String color, String grasa) {
		this.idActual = id;
		txtPesoCria.setText(peso);
		txtColorCria.setText(color);
		txtGrasaCria.setText(grasa);
		lblInfo.setText("Información de la cría #" + idActual);
	}

	public void setControlador(ControladorModal controlador) {
		btnLimpiar.addActionListener(controlador);
		btnActualizar.addActionListener(controlador);
	}

	public void limpiar() {
		txtPesoCria.setText("");
		txtColorCria.setText("");
		txtGrasaCria.setText("");
		txtPesoCria.requestFocus();
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage(this);
		if (error)
			toast.setInfo(msg, MaterialColors.RED_400);
		else
			toast.setInfo(msg, MaterialColors.BLUE_400);
		toast.showToast();
	}

	public boolean verificarCampos() {
		boolean estado = true;
		if (txtPesoCria.getText().equals("")) {
			estado = false;
			txtPesoCria.requestFocus();
		} else if (txtColorCria.getText().trim().equals("")) {
			estado = false;
			txtColorCria.requestFocus();
		} else if (txtGrasaCria.getText().equals("")) {
			estado = false;
			txtGrasaCria.requestFocus();
		}
		return estado;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(MaterialColors.DARKLY_BLUE);
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(0, 75, 480, 75);
	}
}
