package com.neocotic.wal.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.neocotic.wal.ui.Frame;
import com.neocotic.wal.util.Classes;
import com.neocotic.wal.util.Filter;
import com.neocotic.wal.view.event.ViewChangeListener;
import com.neocotic.wal.view.event.ViewEvent;

/*
 * TODO: When modifying details, pre-check whether or not instance is active and throw exception accordingly.
 */
public class ViewManager {

	private static ViewManager instance;

	public static ViewManager getInstance() {
		if (instance == null) {
			instance = new ViewManager();
		}

		return instance;
	}

	private final Map<String, List<ViewChangeListener>> changeListeners = new HashMap<String, List<ViewChangeListener>>();

	private boolean classPathChecked;

	private Frame frame;

	private final Stack<String> stack = new Stack<String>();

	private final Map<String, ViewDetails> viewMap = new HashMap<String, ViewDetails>();

	private ViewManager() {
		registerAllInClassPath();
	}

	public void addChangeListener(Class<? extends View> viewClass, ViewChangeListener listener) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		} else if (listener == null) {
			return;
		}

		String[] names = getViewNames(viewClass);

		if (names.length > 0) {
			for (String name : names) {
				addChangeListener(name, listener);
			}
		} else {
			ViewDetails details = getDetails(viewClass);

			if (details == null) {
				throw new ViewNotFoundException(viewClass);
			}

			addChangeListener(details.getName(), listener);
		}
	}

	public void addChangeListener(String name, ViewChangeListener listener) {
		if (name == null) {
			throw new IllegalArgumentException("null name");
		} else if (listener == null) {
			return;
		}

		List<ViewChangeListener> listeners = changeListeners.get(name);

		if (listeners == null) {
			listeners = new ArrayList<ViewChangeListener>();

			changeListeners.put(name, listeners);
		}

		listeners.add(listener);
	}

	public void addToStack(Class<? extends View> viewClass) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		}

		ViewDetails details = null;
		String[] names = getViewNames(viewClass);

		if (names.length > 0) {
			details = getDetails(names[0]);
		} else {
			details = getDetails(viewClass);
		}

		if (details == null) {
			throw new ViewNotFoundException(viewClass);
		}

		addToStack(details);
	}

	public void addToStack(String name) {
		if (name == null) {
			throw new IllegalArgumentException("null name");
		}

		ViewDetails details = getDetails(name);

		if (details == null) {
			throw new ViewNotFoundException(name);
		}

		addToStack(details);
	}

	private void addToStack(ViewDetails details) {
		if (details.getInstance().isNavigationRecorded()) {
			String lastView = peekFromStackSafely();

			if (lastView == null || !lastView.equals(details.getName())) {
				stack.push(details.getName());
			}
		}
	}

	public void back() {
		String currentView = popFromStackSafely();
		String previousView = peekFromStackSafely();

		if (currentView == null) {
			throw new IllegalStateException("no recorded views found");
		} else if (previousView == null) {
			throw new IllegalStateException("no previous view found");
		}

		ViewDetails details = getDetails(previousView);

		if (details == null) {
			throw new ViewNotFoundException(previousView);
		}

		details.getInstance().setNavigationRecorded(false);

		change(details, null);
	}

	public void change(Class<? extends View> viewClass) {
		change(viewClass, null);
	}

	public void change(String name) {
		change(name, null);
	}

	public void change(Class<? extends View> viewClass, String action) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		}

		ViewDetails details = null;
		String[] names = getViewNames(viewClass);

		if (names.length > 0) {
			details = getDetails(names[0]);
		} else {
			details = getDetails(viewClass);
		}

		if (details == null) {
			throw new ViewNotFoundException(viewClass);
		}

		change(details, action);
	}

	public void change(String name, String action) {
		if (name == null) {
			throw new IllegalArgumentException("null name");
		}

		ViewDetails details = getDetails(name);

		if (details == null) {
			throw new ViewNotFoundException(name);
		}

		change(name, action);
	}

	private void change(ViewDetails details, String action) {
		if (frame == null) {
			throw new IllegalStateException("null frame");
		}

		String lastView = peekFromStackSafely();
		ViewDetails oldDetails = getDetails(lastView);

		if (!details.getName().equals(lastView)) {
			if (oldDetails != null) {
				oldDetails.getInstance().unload();
			}

			fireChangeEvent(details.getName(), ViewEvent.VIEW_CHANGED);
		}

		if (details.getInstance().isNavigationRecorded()) {
			addToStack(details.getName());
		}

		// TODO: Remainder of logic in a separate thread?
		details.getInstance().load(action);

		frame.setView(details.getInstance());
	}

	public void clearStack() {
		stack.clear();
	}

	public Stack<String> copyStack() {
		Stack<String> copy = new Stack<String>();
		copy.addAll(stack);

		return copy;
	}

	protected void fireChangeEvent(Class<? extends View> viewClass, int eventId) {
		for (ViewDetails details : getAllDetails(viewClass)) {
			fireChangeEvent(details, eventId);
		}
	}

	protected void fireChangeEvent(String name, int eventId) {
		fireChangeEvent(getDetails(name), eventId);
	}

	private void fireChangeEvent(ViewDetails details, int eventId) {
		if (details != null && details.hasInstance()) {
			String lastView = peekFromStackSafely();

			details.getInstance().dispatchViewChangeEvent(new ViewEvent(lastView, details.getName(), stack, eventId));
		}
	}

	private ViewDetails[] getAllDetails(Class<? extends View> viewClass) {
		List<ViewDetails> allDetails = new ArrayList<ViewDetails>();

		for (ViewDetails details : viewMap.values()) {
			if (details.getViewClass().equals(viewClass)) {
				allDetails.add(details);
			}
		}

		return allDetails.toArray(new ViewDetails[0]);
	}

	public ViewChangeListener[] getChangeListeners(String name) {
		if (name == null) {
			throw new IllegalArgumentException("null name");
		}

		ViewChangeListener[] array = new ViewChangeListener[0];
		List<ViewChangeListener> listeners = changeListeners.get(name);

		return (listeners == null) ? array : listeners.toArray(array);
	}

	private ViewDetails getDetails(Class<? extends View> viewClass) {
		for (ViewDetails details : viewMap.values()) {
			if (details.getViewClass().equals(viewClass)) {
				return details;
			}
		}

		return null;
	}

	private ViewDetails getDetails(String name) {
		return viewMap.get(name);
	}

	public Frame getFrame() {
		return frame;
	}

	public Stack<String> getStack() {
		return stack;
	}

	public int getStackSize() {
		return stack.size();
	}

	public String[] getViewNames(Class<? extends View> viewClass) {
		ViewInfo info = viewClass.getAnnotation(ViewInfo.class);

		if (isAnnotationValid(info)) {
			return info.value();
		}

		return new String[0];
	}

	public boolean hasValidAnnotation(Class<? extends View> viewClass) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		}

		return isAnnotationValid(viewClass.getAnnotation(ViewInfo.class));
	}

	public boolean isActive(Class<? extends View> viewClass) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		}

		ViewDetails details = null;
		String[] names = getViewNames(viewClass);

		if (names.length > 0) {
			details = getDetails(names[0]);
		} else {
			details = getDetails(viewClass);
		}

		if (details == null) {
			throw new ViewNotFoundException(viewClass);
		}

		return isActive(details);
	}

	public boolean isActive(String name) {
		if (name == null) {
			throw new IllegalArgumentException("null name");
		}

		return name.equals(peekFromStackSafely());
	}

	private boolean isActive(ViewDetails details) {
		return details.getInstance().getName().equals(peekFromStackSafely());
	}

	public boolean isAnnotated(Class<? extends View> viewClass) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		}

		return viewClass.getAnnotation(ViewInfo.class) == null;
	}

	private boolean isAnnotationValid(ViewInfo info) {
		return info != null && info.value().length > 0;
	}

	public boolean isRegistered(Class<? extends View> viewClass) {
		return getDetails(viewClass) == null;
	}

	public boolean isRegistered(String name) {
		return viewMap.containsKey(name);
	}

	public String peekFromStack() {
		return stack.peek();
	}

	public String peekFromStackSafely() {
		return (stack.isEmpty()) ? null : stack.peek();
	}

	public String popFromStack() {
		return stack.pop();
	}

	public String popFromStackSafely() {
		return (stack.isEmpty()) ? null : stack.pop();
	}

	public void register(Class<? extends View> viewClass) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		} else if (!hasValidAnnotation(viewClass)) {
			throw new IllegalArgumentException("null or empty ViewInfo annotation for viewClass");
		}

		for (String name : getViewNames(viewClass)) {
			register(name, viewClass);
		}
	}

	public void register(String name, Class<? extends View> viewClass) {
		if (name == null) {
			throw new IllegalArgumentException("null name");
		} else if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		} else if (isActive(name)) {
			throw new ViewException("cannot unregister active view: " + name);
		}

		ViewDetails details = getDetails(name);

		if (details == null) {
			details = new ViewDetails(name, viewClass);
		} else if (details != null && !viewClass.equals(details.getViewClass())) {
			details.destroyInstance();

			details = new ViewDetails(name, viewClass);
		}

		setDetails(name, details);
	}

	@SuppressWarnings("unchecked")
	private void registerAllInClassPath() {
		if (classPathChecked) {
			throw new IllegalStateException("class path already checked");
		}

		try {
			List<Class<?>> classes = Classes.getPackageClasses(getClass().getPackage(), true, new Filter<Class<?>>() {
				public boolean include(Class<?> cls) {
					return Classes.isExtensionOf(cls, View.class) && cls.getAnnotation(ViewInfo.class) != null;
				}
			});

			for (Class<?> cls : classes) {
				register((Class<? extends View>) cls);
			}
		} catch (Exception e) {
			throw new ViewException("unable to find View classes", e);
		} finally {
			classPathChecked = true;
		}
	}

	public void reload() {
		reload(null);
	}

	public void reload(String action) {
		String view = peekFromStackSafely();

		if (view == null) {
			throw new IllegalStateException("null view");
		}

		ViewDetails details = getDetails(view);

		if (details == null) {
			throw new ViewNotFoundException(view);
		}

		details.getInstance().setNavigationRecorded(false);

		change(details, action);
	}

	public void removeChangeListener(Class<? extends View> viewClass, ViewChangeListener listener) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		} else if (listener == null) {
			return;
		}

		String[] names = getViewNames(viewClass);

		if (names.length > 0) {
			for (String name : names) {
				removeChangeListener(name, listener);
			}
		} else {
			ViewDetails details = getDetails(viewClass);

			if (details == null) {
				throw new ViewNotFoundException(viewClass);
			}

			removeChangeListener(details.getName(), listener);
		}
	}

	public void removeChangeListener(String name, ViewChangeListener listener) {
		if (name == null) {
			throw new IllegalArgumentException("null name");
		} else if (listener == null) {
			return;
		}

		List<ViewChangeListener> listeners = changeListeners.get(name);

		if (listeners != null) {
			listeners.remove(listener);

			if (listeners.isEmpty()) {
				changeListeners.remove(name);
			}
		}
	}

	private void setDetails(String name, ViewDetails details) {
		if (details == null) {
			viewMap.remove(name);
		} else {
			viewMap.put(name, details);
		}
	}

	public void setFrame(Frame frame) {
		if (frame == null) {
			throw new IllegalArgumentException("null frame");
		} else if (this.frame != null) {
			throw new IllegalStateException("frame has already been set");
		}

		this.frame = frame;
	}

	public void unregister(Class<? extends View> viewClass) {
		if (viewClass == null) {
			throw new IllegalArgumentException("null viewClass");
		}

		String[] names = getViewNames(viewClass);

		if (names.length > 0) {
			for (String name : names) {
				unregister(getDetails(name));
			}
		} else {
			unregister(getDetails(viewClass));
		}
	}

	public void unregister(String name) {
		if (name == null) {
			throw new IllegalArgumentException("null name");
		}

		unregister(getDetails(name));
	}

	private void unregister(ViewDetails details) {
		if (details != null) {
			String name = details.getName();

			if (isActive(details)) {
				throw new ViewException("cannot unregister active view: " + details.getName());
			}

			details.destroyInstance();

			setDetails(name, null);
		}
	}
}