package com.neocotic.wal.view;

@SuppressWarnings("serial")
public class ViewNotFoundException extends ViewException {

	public ViewNotFoundException() {
		super();
	}

	public ViewNotFoundException(Class<? extends View> viewClass) {
		this(viewClass.getName());
	}

	public ViewNotFoundException(String name) {
		super((name == null) ? null : "cannot find view: " + name);
	}
}