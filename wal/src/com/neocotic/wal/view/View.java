package com.neocotic.wal.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.neocotic.wal.view.event.ViewChangeListener;
import com.neocotic.wal.view.event.ViewEvent;

@SuppressWarnings("serial")
public abstract class View extends JPanel {

	private JPanel contentPane = new JPanel();

	private JPanel controlsPane = new JPanel();

	private boolean navigationRecorded = true;

	private String viewName;

	public View(String viewName) {
		super();

		if (viewName == null) {
			throw new IllegalArgumentException("viewName");
		}

		this.viewName = viewName;

		// TODO: Setup/style/layout contentPane and controlsPane

		add(contentPane, BorderLayout.CENTER);
		add(controlsPane, BorderLayout.SOUTH);
	}

	public void addViewChangeListener(ViewChangeListener listener) {
		ViewManager.getInstance().addChangeListener(viewName, listener);
	}

	protected void dispatchViewChangeEvent(ViewEvent e) {
		if (e == null) {
			throw new IllegalArgumentException("e");
		}

		for (ViewChangeListener listener : ViewManager.getInstance().getChangeListeners(viewName)) {
			switch (e.getId()) {
			case ViewEvent.VIEW_ENTERED:
				listener.viewEntered(e);
				break;
			case ViewEvent.VIEW_CHANGED:
				listener.viewEntered(e);
			case ViewEvent.VIEW_EXITED:
				listener.viewExited(e);
				break;
			}
		}
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JPanel getControlsPane() {
		return controlsPane;
	}

	public abstract String getHeader();

	public ViewChangeListener[] getViewChangeListeners() {
		return ViewManager.getInstance().getChangeListeners(viewName);
	}

	public String getViewName() {
		return viewName;
	}

	public boolean isNavigationRecorded() {
		return navigationRecorded;
	}

	protected void load(String action) {
		// TODO: Update header visible using #getHeader()
	}

	public void removeViewChangeListener(ViewChangeListener listener) {
		ViewManager.getInstance().removeChangeListener(viewName, listener);
	}

	public void setNavigationRecorded(boolean navigationRecorded) {
		boolean oldNavigationRecorded = this.navigationRecorded;
		this.navigationRecorded = navigationRecorded;

		firePropertyChange("navigationRecorded", oldNavigationRecorded, navigationRecorded);
	}

	protected void unload() {
	}
}