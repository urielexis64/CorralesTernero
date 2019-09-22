package Vista;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PestañaClasifcacion extends JPanel  {
	private JTextField txt;
	private JButton btn;
	
	public PestañaClasifcacion() {
		hazInterfaz();
	}
	
	private void hazInterfaz() {
		setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(50, 15, 300, 40);

		btn = new JButton();
		btn.setText("CLASIFICAR CRÍA");
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBounds(125, 220, 100, 30);

		add(btn);
		add(txt);

	}
}
