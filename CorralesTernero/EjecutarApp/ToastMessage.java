package EjecutarApp;

import javax.swing.*;
import java.awt.*;

public class ToastMessage extends JFrame {
	private String text;
	private JWindow w;
	private Color bg;
	private JPanel p;
	private int wid = 200, hei = 30;

	public void setInfo(String text, Color bg) {
		this.bg = bg;
		this.text = text;
		w.remove(p);
		w.repaint();
		createPanel();
	}

	public ToastMessage(int x, int y) {
		w = new JWindow();

		w.setBackground(new Color(0, 0, 0, 0));

		createPanel();

		w.add(p);
		w.setLocation(x, y);
		w.setSize(wid + 20, hei + 20);
	}

	private void createPanel() {
		p = new JPanel() {
			public void paintComponent(Graphics g) {
//				int wid = g.getFontMetrics().stringWidth(text);
//				int hei = g.getFontMetrics().getHeight();

				FontMetrics metrics = g.getFontMetrics();
				// Determine the X coordinate for the text
				int x = 60 + (100 - metrics.stringWidth(text)) / 2;
				// Determine the Y coordinate for the text (note we add the ascent, as in java
				// 2d 0 is top of the screen)
				int y = 10 + ((30 - metrics.getHeight()) / 2) + metrics.getAscent();
				// Set the font

				g.setColor(bg);
//				g.fillRoundRect(10, 10, wid + 30, hei + 10, 30, 30);
				g.fillRoundRect(10, 10, wid, hei, 30, 30);
				g.setColor(bg);
//				g.drawRoundRect(10, 10, wid + 30, hei + 10,30, 30);
				g.drawRoundRect(10, 10, wid, hei, 30, 30);

				// set the color of text
				g.setColor(new Color(255, 255, 255, 240));
				g.drawString(text, x, y);
				int t = 250;

				for (int i = 0; i < 4; i++) {
					t -= 60;
					g.setColor(new Color(222, 218, 210, t));
//					g.drawRoundRect(10 - i, 10 - i, wid + 30 + i * 2, hei + 10 + i * 2, 30, 30);
					g.drawRoundRect(10 - i, 10 - i, wid, hei, 30, 30);
				}
			}
		};
		w.add(p);
	}

	// function to pop up the toast
	public void showtoast() {

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
					System.out.println(e.getMessage());
				}
			}
		}.start();

	}
}