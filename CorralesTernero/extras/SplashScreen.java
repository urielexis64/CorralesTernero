package extras;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SplashScreen {
	private final JWindow window;

	public SplashScreen() {

		window = new JWindow();
		ImageIcon image = new ImageIcon("resources\\splashscreen.gif");

		window.add(new JLabel("", image, SwingConstants.CENTER));

		JProgressBar bar = new JProgressBar();
		bar.setIndeterminate(true);
		window.add(bar, BorderLayout.SOUTH);
		window.setSize(image.getIconWidth(), image.getIconHeight() + 40);
		window.setLocationRelativeTo(null);
		window.setShape(new RoundRectangle2D.Double(0, 0, image.getIconWidth(), image.getIconHeight() + 40, 50, 50));
	}

	public void show() {
		window.setVisible(true);
	}

	public void hide() {
//		new Thread() {
//			float n = 1f;
//
//			public void run() {
//				while (n > 0f) {
//					try {
//						sleep(50);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					window.setOpacity(n);
//					n -= 0.05;
//				}
//				window.setVisible(false);
//			}
//		}.start();
		window.setVisible(false);
	}
}