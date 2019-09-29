package Vista;

import java.awt.*;
import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;

import Controlador.Controlador_Registro;
import EjecutarApp.ToastMessage;
import de.craften.ui.swingmaterial.*;
import de.craften.ui.swingmaterial.MaterialButton.Type;
import de.craften.ui.swingmaterial.fonts.Roboto;
import mdlaf.shadows.RoundedCornerBorder;
import mdlaf.utils.MaterialColors;
import misHerramientas.Rutinas;

public class PestañaRegistro extends JPanel {

	public MaterialTextField txtIdCria, txtPesoCria, txtColorCria, txtGrasaCria;
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

		txtIdCria = new MaterialTextField();
		txtIdCria.setBounds(100, 20, 500, 70);
		txtIdCria.setLabel("ID");
		txtIdCria.setUpperFilter(true);
		txtIdCria.setAccent(MaterialColors.YELLOW_300);
		txtIdCria.setForeground(MaterialColors.WHITE);
		txtIdCria.setBackground(MaterialColor.TRANSPARENT);
		txtIdCria.setCaretColor(Color.WHITE);
		txtIdCria.setEnabledRegex(true);

		txtPesoCria = new MaterialTextField();
		txtPesoCria.setBounds(100, 100, 500, 70);
		txtPesoCria.setLabel("Peso de la cría");
		txtPesoCria.setUpperFilter(true);
		txtPesoCria.setAccent(MaterialColors.YELLOW_300);
		txtPesoCria.setForeground(MaterialColors.WHITE);
		txtPesoCria.setBackground(MaterialColor.TRANSPARENT);
		txtPesoCria.setCaretColor(Color.WHITE);
		txtPesoCria.setEnabledRegex(true);

		txtColorCria = new MaterialTextField();
		txtColorCria.setBounds(100, 180, 500, 70);
		txtColorCria.setLabel("Color de músculo");
		txtColorCria.setUpperFilter(true);
		txtColorCria.setAccent(MaterialColors.YELLOW_300);
		txtColorCria.setForeground(MaterialColors.WHITE);
		txtColorCria.setBackground(MaterialColor.TRANSPARENT);
		txtColorCria.setCaretColor(Color.WHITE);

		txtGrasaCria = new MaterialTextField();
		txtGrasaCria.setBounds(100, 260, 500, 70);
		txtGrasaCria.setLabel("Porcentaje de grasa");
		txtGrasaCria.setUpperFilter(true);
		txtGrasaCria.setAccent(MaterialColors.YELLOW_300);
		txtGrasaCria.setForeground(MaterialColors.WHITE);
		txtGrasaCria.setBackground(MaterialColor.TRANSPARENT);
		txtGrasaCria.setCaretColor(Color.WHITE);
		txtGrasaCria.setEnabledRegex(true);

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
		panel.add(txtIdCria);
		panel.add(txtPesoCria);
		panel.add(txtColorCria);
		panel.add(txtGrasaCria);
		panel.add(lblFechaEntrada);
		panel.add(calendario);
		
		add(panel);
	}

	public void showMessage(String msg, boolean error) {
		ToastMessage toast = new ToastMessage();
		if (error)
			toast.setInfo(msg, MaterialColors.RED_500);
		else
			toast.setInfo(msg, MaterialColors.GREEN_600);

		toast.showToast();
	}

	public void limpiar() {
		txtIdCria.setText("");
		txtPesoCria.setText("");
		txtColorCria.setText("");
		txtGrasaCria.setText("");
		calendario.setText("");
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
		btnLimpiar.addActionListener(controlador);
		btnAleatorio.addActionListener(controlador);
	}

}
