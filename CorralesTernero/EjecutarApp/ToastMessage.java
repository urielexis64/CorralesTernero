package EjecutarApp;

import javax.swing.*;

import java.awt.*;

public class ToastMessage extends JFrame {
	private String text;
	private JWindow w;
	private Color bg;
	private JPanel p;
	private int wid = 200, hei = 30;

	public ToastMessage() {
		w = new JWindow();
		w.setBackground(new Color(0, 0, 0, 0));
		w.setSize(wid + 20, hei + 20);
		w.setLocationRelativeTo(Ejecutar.getInstance());
	}

	public void setInfo(String text, Color bg) {
		this.text = text;
		this.bg = bg;
		createPanel();

	}

	private void createPanel() {
		p = new JPanel() {
			public void paintComponent(Graphics g) {
				FontMetrics metrics = g.getFontMetrics();
				// Coordenada x del texto
				int x = 60 + (100 - metrics.stringWidth(text)) / 2;
				// Coordenada y del texto
				int y = 10 + ((30 - metrics.getHeight()) / 2) + metrics.getAscent();

				g.setColor(bg);
				g.fillRoundRect(10, 10, wid, hei, 30, 30);
				g.setColor(bg);
				g.drawRoundRect(10, 10, wid, hei, 30, 30);

				g.setColor(new Color(255, 255, 255, 240));
				g.drawString(text, x, y);
				int t = 250;

				for (int i = 0; i < 4; i++) {
					t -= 60;
					g.setColor(new Color(222, 218, 210, t));
					g.drawRoundRect(10 - i, 10 - i, wid, hei, 30, 30);
				}
			}
		};
		w.add(p);
	}

	public void showToast() {
		new Thread() {
			public void run() {
				try {
					w.setOpacity(0);

					w.setVisible(true);

					for (double d = .1; d < 1; d += 0.1) {
						sleep(100);
						w.setOpacity((float) d);
					}

					sleep(2000);

					for (double d = 1.0; d > 0.2; d -= 0.1) {
						sleep(100);
						w.setOpacity((float) d);
					}

					w.setVisible(false);
				} catch (Exception e) {
				}
			}
		}.start();
	}

}
