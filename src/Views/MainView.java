package Views;

import javax.swing.*;

public class MainView extends JFrame{

	private JTabbedPane mainMenu;
	private InsertView addToCorralView;
	
	private MainView() {
		super("Corrales");
		setSize(600,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainMenu = new JTabbedPane();
		addToCorralView = new InsertView();
		mainMenu.addTab("Alta de Crias",addToCorralView);
		add(mainMenu);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainView();
	}

}
