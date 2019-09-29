package Components;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class JMultiDataBox extends JTextField implements FocusListener,KeyListener{
	public static final String EMAIL_REGEX = "[A-Za-z0-9-_.]{1,20}[@]{1}[A-Za-z]{1,15}[.]{1}[A-Za-z]{2,3}";
	public static final String TEL_REGEX = "[(]{1}[0-9]{3}[)]{1}[-]{1}[0-9]{7}";
	public static final String RFC_REGEX = "[A-Z]{4}[0-9]{6}[A-Z0-9]{3}";
	private static final int TEL_LENGTH = 11;
	private static final int RFC_LENGTH = 13;
	private Color successColor,errorColor;
	private String currentRegex;
	
	public  JMultiDataBox(String regEx,Color success, Color error) {
		super(30);
		successColor = success;
		errorColor = error;
		currentRegex = regEx;
		setListener();
	}
	
	public  JMultiDataBox(String regEx) {
		this(regEx,Color.GREEN,Color.RED);
		setListener();
	}
	
	public void setListener() {
		this.addFocusListener(this);
		this.addKeyListener(this);
	}
	
	public boolean matches() {
		return getText().matches(currentRegex);
	}

	public String getCurrentRegex() {
		return currentRegex;
	}

	public void setCurrentRegex(String currentRegex) {
		this.currentRegex = currentRegex;
	}

	@Override
	public void focusGained(FocusEvent arg0) {}

	@Override
	public void focusLost(FocusEvent evt) {
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {}

	@Override
	public void keyReleased(KeyEvent evt) {
		if(matches()) {
			this.setBorder(BorderFactory.createLineBorder(successColor));
			return;
		}
		
		this.setBorder(BorderFactory.createLineBorder(errorColor));
	}

	@Override
	public void keyTyped(KeyEvent evt) {

	}
	

	public Color getSuccessColor() {
		return successColor;
	}

	public void setSuccessColor(Color successColor) {
		this.successColor = successColor;
	}

	public Color getErrorColor() {
		return errorColor;
	}

	public void setErrorColor(Color errorColor) {
		this.errorColor = errorColor;
	}
}
