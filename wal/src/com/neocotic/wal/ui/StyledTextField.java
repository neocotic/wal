package com.neocotic.wal.ui;

import javax.swing.JTextField;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class StyledTextField extends JTextField implements StyledComponent {

	private Style style = new Style("textField");

	public StyledTextField() {
		super();
	}

	public StyledTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
	}

	public StyledTextField(int columns) {
		super(columns);
	}

	public StyledTextField(String text, int columns) {
		super(text, columns);
	}

	public StyledTextField(String text) {
		super(text);
	}

	public Style getStyle() {
		return style;
	}

	public void restyle() {
		setBackground(style.getColor("background"));
		setForeground(style.getColor("foreground"));

		if (style.hasBoolean("focusable")) {
			setFocusable(style.getBoolean("focusable"));
		}

		if (style.hasCursor("cursor")) {
			setCursor(style.getCursor("cursor"));
		}

		if (style.hasFont("font")) {
			setFont(style.getFont("font"));
		}

		if (style.hasSize("preferredSize")) {
			setPreferredSize(style.getSize("preferredSize"));
		}
	}

	public void setStyle(String style) {
		setStyle(StyleManager.get(style));
	}

	public void setStyle(Style style) {
		if (style == null) {
			style = new Style();
		}

		this.style = style;

		restyle();
	}
}