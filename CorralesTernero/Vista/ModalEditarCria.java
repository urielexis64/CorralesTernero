package Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import EjecutarApp.Ejecutar;
import EjecutarApp.ToastMessage;
import Modelo.ModeloActualiza;
import de.craften.ui.swingmaterial.*;
import mdlaf.utils.MaterialColors;

public class ModalEditarCria extends JDialog implements ActionListener {
	public MaterialTextField txtPesoCria, txtColorCria, txtGrasaCria;
	private MaterialButton btnLimpiar, btnActualizar;
	private ModeloActualiza modelo;
	private JLabel lblInfo;
	private int idActual;

	public ModalEditarCria() {
		hazInterfaz();
		hazEscuchas();
	}

	public void hazInterfaz() {
		setSize(480, 400);
		setLocationRelativeTo(Ejecutar.getInstance());
		setResizable(false);
		setModal(true);
		setLayout(null);

		lblInfo = new JLabel();
		lblInfo.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, -20, 480, 80);

		txtPesoCria = new MaterialTextField();
		txtPesoCria.setBounds(50, 60, 380, 70);
		txtPesoCria.setLabel("Peso de la cría");
		txtPesoCria.setAccent(MaterialColors.YELLOW_300);
		txtPesoCria.setForeground(MaterialColors.WHITE);
		txtPesoCria.setBackground(MaterialColor.TRANSPARENT);
		txtPesoCria.setCaretColor(Color.WHITE);
		
		txtColorCria = new MaterialTextField();
		txtColorCria.setBounds(50, 140, 380, 70);
		txtColorCria.setLabel("Color de músculo");
		txtColorCria.setAccent(MaterialColors.YELLOW_300);
		txtColorCria.setForeground(MaterialColors.WHITE);
		txtColorCria.setBackground(MaterialColor.TRANSPARENT);
		txtColorCria.setCaretColor(Color.WHITE);

		txtGrasaCria = new MaterialTextField();
		txtGrasaCria.setBounds(50, 220, 380, 70);
		txtGrasaCria.setLabel("Porcentaje de grasa");
		txtGrasaCria.setAccent(MaterialColors.YELLOW_300);
		txtGrasaCria.setForeground(MaterialColors.WHITE);
		txtGrasaCria.setBackground(MaterialColor.TRANSPARENT);
		txtGrasaCria.setCaretColor(Color.WHITE);

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

	private void hazEscuchas() {
		btnLimpiar.addActionListener(this);
		btnActualizar.addActionListener(this);
		modelo = new ModeloActualiza();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			limpiar();
			return;
		}

		ToastMessage toast = new ToastMessage();

		int peso = Integer.parseInt(txtPesoCria.getText());
		String color = txtColorCria.getText();
		int grasa = Integer.parseInt(txtGrasaCria.getText());

		if (modelo.actualizaCria(idActual, peso, color, grasa)) {
			toast.setInfo("Actualizado con éxito", MaterialColors.BLUE_400);
			setVisible(false);
		} else {
			toast.setInfo("Hubo un error...", MaterialColors.RED_400);
		}
		toast.showToast();

	}

	private void limpiar() {
		txtPesoCria.setText("");
		txtColorCria.setText("");
		txtGrasaCria.setText("");
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(MaterialColors.DARKLY_BLUE);
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(0, 75, 480, 75);
	}
}
