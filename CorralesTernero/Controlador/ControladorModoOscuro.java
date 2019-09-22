package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;
import javax.swing.UnsupportedLookAndFeelException;

import Vista.VentanaPrincipal;

public class ControladorModoOscuro implements ActionListener {
	VentanaPrincipal vista;

	public ControladorModoOscuro(VentanaPrincipal vista) {
		this.vista=vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton btn = (JToggleButton) e.getSource();
		try {
			vista.modoOscuro(btn.isSelected());
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
	}

}
