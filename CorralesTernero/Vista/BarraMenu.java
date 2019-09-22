package Vista;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import Controlador.ControladorBarraMenu;
import mdlaf.shadows.DropShadowBorder;
import mdlaf.utils.MaterialColors;
import misHerramientas.Rutinas;

public class BarraMenu extends JMenuBar {

	private JMenu menu1, menu2;
	private JMenuItem item1, item2;
	private Component component;

	public BarraMenu(Component component) {
		this.component = component;
		hazInterfaz();
	}

	private void hazInterfaz() {
		setBorder(new DropShadowBorder(MaterialColors.COSMO_LIGTH_GRAY, 2, 6));
		menu1 = new JMenu("Configuración");
		menu2 = new JMenu("Ayuda");

		item1 = new JMenuItem("Cambiar tema");
		item2 = new JMenuItem("Acerca de...", Rutinas.AjustarImagen("Resources\\info.png", 20, 20));

//		menu1.addSeparator();

		menu1.add(item1);
		menu2.add(item2);

		add(menu1);
		add(menu2);
	}

	public void setControlador(ControladorBarraMenu controlador) {
		item2.addActionListener(controlador);
	}

	public void showInfoMessage() {
		JOptionPane.showMessageDialog(component, "<html>" + "<center>Realizado por: </center>"
				+ "<div style='color: #fffff0; h2 {text-align: center;}'><h2>Uriel Alexis Aispuro Sánchez</h2></div><br> "
				+ "<h3><center>21/09/2019</center></h3>" + "</html>", "Acerca de...", JOptionPane.INFORMATION_MESSAGE);
	}
}
