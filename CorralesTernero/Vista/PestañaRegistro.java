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

public class Pesta�aRegistro extends JPanel {

	public MaterialTextField txtPesoCria, txtColorCria, txtGrasaCria;
	public MaterialButton btnRegistrarCria, btnLimpiar, btnAleatorio;
	private MaterialPanel panel;
	public MaterialProgressSpinner bar;
	
	public JLabel lblTitulo;

	private DatePickerSettings dateSettings;
	public DatePicker calendario;

	private String[] coloresMusculo = { "Rojo Vivo", "Rojo Purpura", "Rojo Pardo" };

	public Pesta�aRegistro() {
		hazInterfaz();
	}

	private void hazInterfaz() {
		setLayout(null);
		setBackground(new Color(40, 65, 91, 0));

		panel = new MaterialPanel();
		panel.setElevation(2);
		panel.setLayout(null);
		panel.setBounds(40, 10, 700, 620);

		lblTitulo= new JLabel("Registro");
		lblTitulo.setFont(Roboto.BLACK.deriveFont(26f));
		lblTitulo.setBounds(290, 10, 100, 40);
		
		txtPesoCria = new MaterialTextField();
		txtPesoCria.setBounds(100, 40, 500, 70);
		txtPesoCria.setLabel("Peso de la cr�a");
		txtPesoCria.setUpperFilter(true);
		txtPesoCria.setAccent(MaterialColors.YELLOW_300);
		txtPesoCria.setForeground(MaterialColors.WHITE);
		txtPesoCria.setBackground(MaterialColor.TRANSPARENT);
		txtPesoCria.setCaretColor(Color.WHITE);
		txtPesoCria.setRegex(MaterialTextField.REGEX_DECIMAL);

		txtColorCria = new MaterialTextField();
		txtColorCria.setBounds(100, 140, 500, 70);
		txtColorCria.setLabel("Color de m�sculo");
		txtColorCria.setUpperFilter(true);
		txtColorCria.setAccent(MaterialColors.YELLOW_300);
		txtColorCria.setForeground(MaterialColors.WHITE);
		txtColorCria.setBackground(MaterialColor.TRANSPARENT);
		txtColorCria.setCaretColor(Color.WHITE);

		txtGrasaCria = new MaterialTextField();
		txtGrasaCria.setBounds(100, 240, 500, 70);
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
		lblFechaEntrada.setBounds(100, 345, 150, 20);

		calendario = new DatePicker(dateSettings);
		calendario.getComponentToggleCalendarButton().setText("");
		calendario.getComponentToggleCalendarButton().setIcon(Rutinas.AjustarImagen("Resources\\calendar.png", 20, 20));
		calendario.setBounds(100, 370, 150, 40);
		calendario.getComponentDateTextField().setBorder(new RoundedCornerBorder(Color.WHITE));

		btnRegistrarCria = new MaterialButton();
		btnRegistrarCria.setText("REGISTRAR CR�A");
		btnRegistrarCria.setRippleColor(MaterialColor.GREEN_500);
		btnRegistrarCria.setBackground(MaterialColors.GREEN_300);
		btnRegistrarCria.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegistrarCria.setBorderRadius(6);
		btnRegistrarCria.setType(Type.RAISED);
		btnRegistrarCria.setBounds(250, 470, 200, 70);
		btnRegistrarCria.setAnimado(true);

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

		panel.add(lblTitulo);
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
		txtColorCria.setText(coloresMusculo[Rutinas.nextInt(0, 2)]);
		txtGrasaCria.setText(Rutinas.nextInt(1, 100) + "");

		int a�o = 2019;
		int mes = Rutinas.nextInt(1, 10);
		int dia = mes == 2 ? Rutinas.nextInt(1, 28)
				: mes == 4 || mes == 6 || mes == 9 || mes == 11 ? Rutinas.nextInt(1, 30) : Rutinas.nextInt(1, 31);
		calendario.setDate(LocalDate.of(a�o, mes, dia));
	}

	public void setControlador(ControladorRegistro controlador) {
		btnRegistrarCria.addActionListener(controlador);
		btnLimpiar.addActionListener(controlador);
		btnAleatorio.addActionListener(controlador);
		txtGrasaCria.addActionListener(controlador);
	}

}
