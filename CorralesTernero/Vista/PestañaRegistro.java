package Vista;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;

import Controlador.ControladorRegistro;
import material.componentes.*;
import material.componentes.MaterialButton.Type;
import material.extras.AccionComponente;
import material.extras.Rutinas;
import material.extras.ToastMessage;
import material.fonts.Roboto;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;

public class PestañaRegistro extends JPanel {

	public MaterialTextField txtPesoCria, txtColorCria, txtGrasaCria;
	public MaterialButton btnRegistrarCria, btnLimpiar, btnAleatorio;
	private MaterialPanel panel;
	public MaterialProgressSpinner bar;

	private DatePickerSettings dateSettings;
	public DatePicker calendario;

	public PestañaRegistro() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		setBackground(new Color(40, 65, 91, 0));

		panel = new MaterialPanel();
		panel.setElevation(2);
		panel.setLayout(null);
		panel.setBounds(40, 25, 700, 600);

		txtPesoCria = new MaterialTextField();
		txtPesoCria.setBounds(100, 30, 500, 70);
		txtPesoCria.setLabel("Peso de la cría");
		txtPesoCria.setUpperFilter(true);
		txtPesoCria.setAccent(MaterialColors.YELLOW_300);
		txtPesoCria.setForeground(MaterialColors.WHITE);
		txtPesoCria.setBackground(MaterialColor.TRANSPARENT);
		txtPesoCria.setCaretColor(Color.WHITE);
		txtPesoCria.setRegex(MaterialTextField.REGEX_DECIMAL);

		txtColorCria = new MaterialTextField();
		txtColorCria.setBounds(100, 130, 500, 70);
		txtColorCria.setLabel("Color de músculo");
		txtColorCria.setUpperFilter(true);
		txtColorCria.setAccent(MaterialColors.YELLOW_300);
		txtColorCria.setForeground(MaterialColors.WHITE);
		txtColorCria.setBackground(MaterialColor.TRANSPARENT);
		txtColorCria.setCaretColor(Color.WHITE);

		txtGrasaCria = new MaterialTextField();
		txtGrasaCria.setBounds(100, 230, 500, 70);
		txtGrasaCria.setLabel("Porcentaje de grasa");
		txtGrasaCria.setUpperFilter(true);
		txtGrasaCria.setAccent(MaterialColors.YELLOW_300);
		txtGrasaCria.setForeground(MaterialColors.WHITE);
		txtGrasaCria.setBackground(MaterialColor.TRANSPARENT);
		txtGrasaCria.setCaretColor(Color.WHITE);
		txtGrasaCria.setRegex(MaterialTextField.REGEX_ENTERO);

		Font fuente = Roboto.REGULAR.deriveFont(16f);

		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dateSettings.setFormatForDatesCommonEra("dd-MM-uuuu");
		dateSettings.setColor(DateArea.TextFieldBackgroundValidDate, MaterialColors.DARKLY_STRONG_BLUE);
		dateSettings.setColor(DateArea.TextTodayLabel, MaterialColors.DARKLY_STRONG_BLUE);
		dateSettings.setColor(DateArea.TextMonthAndYearMenuLabels, MaterialColors.DARKLY_STRONG_BLUE);
		dateSettings.setColor(DateArea.TextClearLabel, MaterialColors.DARKLY_STRONG_BLUE);
		dateSettings.setColor(DateArea.DatePickerTextValidDate, MaterialColors.WHITE);
		dateSettings.setFontMonthAndYearMenuLabels(fuente);
		dateSettings.setFontMonthAndYearNavigationButtons(fuente);
		dateSettings.setFontTodayLabel(fuente);
		dateSettings.setFontClearLabel(fuente);
		dateSettings.setFontCalendarDateLabels(fuente);
		dateSettings.setFontValidDate(fuente);

		JLabel lblFechaEntrada = new JLabel("Fecha de entrada");
		lblFechaEntrada.setFont(Roboto.BOLD.deriveFont(14f));
		lblFechaEntrada.setForeground(MaterialColors.DARKLY_GRAY);
		lblFechaEntrada.setBounds(100, 335, 150, 20);

		calendario = new DatePicker(dateSettings);
		calendario.getComponentToggleCalendarButton().setText("");
		calendario.getComponentToggleCalendarButton().setIcon(Rutinas.AjustarImagen("Resources\\calendar.png", 20, 20));
		calendario.setBounds(100, 360, 150, 40);
		calendario.getComponentDateTextField().setBorder(new RoundedCornerBorder(Color.WHITE));

		btnRegistrarCria = new MaterialButton();
		btnRegistrarCria.setText("REGISTRAR CRÍA");
		btnRegistrarCria.setRippleColor(MaterialColor.GREEN_500);
		btnRegistrarCria.setBackground(MaterialColors.GREEN_300);
		btnRegistrarCria.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegistrarCria.setBorderRadius(6);
		btnRegistrarCria.setType(Type.RAISED);
		btnRegistrarCria.setBounds(250, 470, 200, 70);

		btnLimpiar = new MaterialButton(Rutinas.AjustarImagen("Resources\\clean.png", 30, 30).getImage());
		btnLimpiar.setRippleColor(MaterialColor.GREEN_500);
		btnLimpiar.setBackground(MaterialColor.TRANSPARENT);
		btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBorderRadius(20);
		btnLimpiar.setType(Type.FLAT);
		btnLimpiar.setBounds(540, 470, 80, 70);

		btnAleatorio = new MaterialButton(Rutinas.AjustarImagen("Resources\\random.png", 30, 30).getImage());
		btnAleatorio.setRippleColor(MaterialColor.BLUE_200);
		btnAleatorio.setBackground(MaterialColor.TRANSPARENT);
		btnAleatorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAleatorio.setBorderRadius(20);
		btnAleatorio.setType(Type.FLAT);
		btnAleatorio.setBounds(80, 470, 80, 70);

		bar = new MaterialProgressSpinner();
		bar.setBounds(300, 200, 100, 100);
		bar.setVisible(false);

		panel.add(bar);
		panel.add(btnRegistrarCria);
		panel.add(btnLimpiar);
		panel.add(btnAleatorio);
		panel.add(txtPesoCria);
		panel.add(txtColorCria);
		panel.add(txtGrasaCria);
		panel.add(lblFechaEntrada);
		panel.add(calendario);

		add(panel);
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage(this);
		if (error)
			toast.setInfo(msg, MaterialColors.RED_400);
		else {
			toast.setInfo(msg, MaterialColors.GREEN_600);
			toast.setLocation(toast.getLocation().x, toast.getLocation().y + 40);
		}
		toast.showToast();
	}

	public void limpiar() {
		txtPesoCria.setText("");
		txtColorCria.setText("");
		txtGrasaCria.setText("");
		calendario.setText("");
		txtPesoCria.requestFocus();
	}

	public boolean verificarCampos() {
		boolean estado = true;
		ArrayList<JComponent> componentes = new ArrayList<JComponent>();

		if (txtPesoCria.getText().equals("")) {
			estado = false;
			componentes.add(txtPesoCria);
		}
		if (txtColorCria.getText().trim().equals("")) {
			estado = false;
			componentes.add(txtColorCria);
		}
		if (txtGrasaCria.getText().equals("")) {
			estado = false;
			componentes.add(txtGrasaCria);
		}
		if (calendario.getText().equals("")) {
			estado = false;
			calendario.getComponentDateTextField().setBackground(MaterialColors.RED_400);
			componentes.add(calendario);
		}
		if (!estado) {
			componentes.get(0).requestFocus();
			for (int i = 0; i < componentes.size(); i++)
				AccionComponente.sacudir(componentes.get(i), 300, 10);
		}
		return estado;
	}

	public void procesoAleatorio() {
		txtPesoCria.setText(String.format("%.2f", Rutinas.nextFloat(20, 200)));
		txtColorCria.setText(Rutinas.nextColor());
		txtGrasaCria.setText(Rutinas.nextInt(1, 100) + "");

		int año = Rutinas.nextInt(2000, 2020);
		int mes = Rutinas.nextInt(1, 12);
		int dia = mes == 2 ? Rutinas.nextInt(1, 28)
				: mes == 4 || mes == 6 || mes == 9 || mes == 11 ? Rutinas.nextInt(1, 30) : Rutinas.nextInt(1, 31);
		calendario.setDate(LocalDate.of(año, mes, dia));
	}

	public void setControlador(ControladorRegistro controlador) {
		btnRegistrarCria.addActionListener(controlador);
		btnLimpiar.addActionListener(controlador);
		btnAleatorio.addActionListener(controlador);
		txtGrasaCria.addActionListener(controlador);
	}

}
