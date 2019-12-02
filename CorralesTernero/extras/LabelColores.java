package extras;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import material.componentes.MaterialColor;
import material.extras.Rutinas;
import mdlaf.shadows.RoundedCornerBorder;

public class LabelColores extends JLabel implements ActionListener {

	private Timer t;
	private int i, f;
	private boolean asc, fuente = false;
	private Color[] colores = { MaterialColor.BLUEGREY_100, MaterialColor.BLUEGREY_200, MaterialColor.BLUEGREY_300,
			MaterialColor.BLUEGREY_400, MaterialColor.BLUEGREY_500, MaterialColor.BLUEGREY_600,
			MaterialColor.BLUEGREY_700, MaterialColor.BLUEGREY_800, MaterialColor.BLUEGREY_900 };
	private String fuentes[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	public LabelColores(String s) {
		super(s);
		t = new Timer(200, this);
		i = 0;
		f = 0;
		asc = true;
	}

	public LabelColores(String s, int align) {
		super(s, align);
		t = new Timer(350, this);
	}

	public void setAnimado(boolean b) {
		if (b) {
			t.start();
		} else {
			t.stop();
		}
	}

	public void setFuente() {
		this.fuente = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!fuente) {
			if (i == 8)
				asc = false;
			else if (i == 0)
				asc = true;

			if (asc)
				setBorder(new RoundedCornerBorder(colores[++i]));
			else
				setBorder(new RoundedCornerBorder(colores[--i]));
			return;
		}

		if (f == fuentes.length - 1)
			f = 0;
		setFont(new Font(fuentes[f++], Font.BOLD, getFont().getSize()));
		setForeground(new Color(Rutinas.nextInt(0, 255), Rutinas.nextInt(0, 255), Rutinas.nextInt(0, 255)));

	}
}
