package com.neocotic.wal.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class UndecoratedUtils {

	private static final int EAST = 8;

	private static final Insets INSETS = new Insets(5, 5, 5, 5);

	private static final int NORTH = 1;

	private static final Dimension SNAP_SIZE = new Dimension(1, 1);

	private static final int SOUTH = 4;

	private static final int WEST = 2;

	private static final Map<Component, CachedComponent> cachedComponent = new HashMap<Component, CachedComponent>();

	private static final Map<Integer, Integer> cursors = new HashMap<Integer, Integer>();
	static {
		cursors.put(1, Cursor.N_RESIZE_CURSOR);
		cursors.put(2, Cursor.W_RESIZE_CURSOR);
		cursors.put(4, Cursor.S_RESIZE_CURSOR);
		cursors.put(8, Cursor.E_RESIZE_CURSOR);
		cursors.put(3, Cursor.NW_RESIZE_CURSOR);
		cursors.put(9, Cursor.NE_RESIZE_CURSOR);
		cursors.put(6, Cursor.SW_RESIZE_CURSOR);
		cursors.put(12, Cursor.SE_RESIZE_CURSOR);
	}

	private static CachedComponent getCachedComponent(Component component) {
		CachedComponent cached = cachedComponent.get(component);

		if (cached == null) {
			cached = new CachedComponent(component);

			cachedComponent.put(component, cached);
		}

		return cached;
	}

	private static boolean isMaximized(Frame frame) {
		return Frame.MAXIMIZED_BOTH == frame.getExtendedState();
	}

	public static void registerMoveComponent(Component component) {
		if (component == null) {
			return;
		}

		CachedComponent cached = getCachedComponent(component);

		if (cached.moveMouseListener == null) {
			cached.moveMouseListener = new MoveMouseListener(cached);

			component.addMouseListener(cached.moveMouseListener);
			component.addMouseMotionListener(cached.moveMouseListener);
		}
	}

	public static void registerResizeComponent(Component component) {
		if (component == null) {
			return;
		}

		CachedComponent cached = getCachedComponent(component);

		if (cached.resizeMouseListener == null) {
			cached.resizeMouseListener = new ResizeMouseListener(cached);

			component.addMouseListener(cached.resizeMouseListener);
			component.addMouseMotionListener(cached.resizeMouseListener);
		}
	}

	public static void registerTitleComponent(Component component) {
		if (component == null) {
			return;
		}

		CachedComponent cached = getCachedComponent(component);

		if (cached.titleMouseListener == null) {
			cached.titleMouseListener = new TitleMouseListener(cached);

			component.addMouseListener(cached.titleMouseListener);
			component.addMouseMotionListener(cached.titleMouseListener);
		}
	}

	public static void unregisterMoveComponent(Component component) {
		if (component == null) {
			return;
		}

		CachedComponent cached = getCachedComponent(component);

		if (cached.moveMouseListener != null) {
			component.removeMouseListener(cached.moveMouseListener);
			component.removeMouseMotionListener(cached.moveMouseListener);

			cached.moveMouseListener = null;
		}
	}

	public static void unregisterResizeComponent(Component component) {
		if (component == null) {
			return;
		}

		CachedComponent cached = getCachedComponent(component);

		if (cached.resizeMouseListener != null) {
			component.removeMouseListener(cached.resizeMouseListener);
			component.removeMouseMotionListener(cached.resizeMouseListener);

			cached.resizeMouseListener = null;
		}
	}

	public static void unregisterTitleComponent(Component component) {
		if (component == null) {
			return;
		}

		CachedComponent cached = getCachedComponent(component);

		if (cached.titleMouseListener != null) {
			component.removeMouseListener(cached.titleMouseListener);
			component.removeMouseMotionListener(cached.titleMouseListener);

			cached.titleMouseListener = null;
		}
	}

	private static class CachedComponent {

		private Component component;

		private Frame frame;

		private MouseAdapter moveMouseListener;

		private MouseAdapter resizeMouseListener;

		private MouseAdapter titleMouseListener;

		private CachedComponent(Component component) {
			if (component == null) {
				throw new IllegalArgumentException("null component");
			}

			this.component = component;

			if (component instanceof Frame) {
				frame = (Frame) component;
			} else {
				frame = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, component);
			}
		}
	}

	private static class MoveMouseListener extends MouseAdapter {

		private boolean draggable;

		private Frame frame;

		private Point point = new Point();

		private MoveMouseListener(CachedComponent cached) {
			frame = cached.frame;
		}

		/*
		 * @see MouseAdapter#mouseDragged(MouseEvent)
		 */
		public void mouseDragged(MouseEvent e) {
			if (isMaximized(frame) || !draggable) {
				return;
			}

			Point current = e.getLocationOnScreen();

			if (current.x > point.x) {
				frame.setLocation(frame.getX() + (current.x - point.x), frame.getY());
			} else if (current.x < point.x) {
				frame.setLocation(frame.getX() - (point.x - current.x), frame.getY());
			}

			if (current.y > point.y) {
				frame.setLocation(frame.getX(), frame.getY() + (current.y - point.y));
			} else if (current.y < point.y) {
				frame.setLocation(frame.getX(), frame.getY() - (point.y - current.y));
			}

			point.x = current.x;
			point.y = current.y;
		}

		/*
		 * @see MouseAdapter#mousePressed(MouseEvent)
		 */
		public void mousePressed(MouseEvent e) {
			if (MouseEvent.BUTTON1 == e.getButton()) {
				draggable = true;
				point = e.getLocationOnScreen();
			}
		}

		/*
		 * @see MouseAdapter#mouseReleased(MouseEvent)
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			if (MouseEvent.BUTTON1 == e.getButton()) {
				draggable = false;
			}
		}
	}

	private static class ResizeMouseListener extends MouseAdapter {

		private boolean autoscrolls;

		private Rectangle bounds;

		private Component component;

		private Cursor cursor;

		private int direction;

		private Frame frame;

		private Point pressed;

		private boolean resizing;

		private ResizeMouseListener(CachedComponent cached) {
			component = cached.component;
			frame = cached.frame;
		}

		private Dimension getBoundingSize(Component component) {
			if (component instanceof Frame) {
				Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

				return new Dimension(bounds.width, bounds.height);
			}

			return component.getParent().getSize();
		}

		private int getDragBounded(int drag, int snapSize, int dimension, int minimum, int maximum) {
			while (dimension + drag < minimum) {
				drag += snapSize;
			}

			while (dimension + drag > maximum) {
				drag -= snapSize;
			}

			return drag;
		}

		private int getDragDistance(int larger, int smaller, int snapSize) {
			int halfway = snapSize / 2;
			int drag = larger - smaller;
			drag += (drag < 0) ? -halfway : halfway;

			return (drag / snapSize) * snapSize;
		}

		private boolean isResizable() {
			return !isMaximized(frame) && frame.isResizable();
		}

		/*
		 * @see MouseAdapter#mouseDragged(MouseEvent)
		 */
		public void mouseDragged(MouseEvent e) {
			if (!isResizable() || !resizing) {
				return;
			}

			Point current = e.getLocationOnScreen();
			Dimension maximumSize = frame.getMaximumSize();
			Dimension minimumSize = frame.getMinimumSize();

			int drag, maximum;
			int width = bounds.width, height = bounds.height;
			int x = bounds.x, y = bounds.y;

			if (WEST == (direction & WEST)) {
				drag = getDragDistance(pressed.x, current.x, SNAP_SIZE.width);
				maximum = Math.min(width + x, maximumSize.width);
				drag = getDragBounded(drag, SNAP_SIZE.width, width, minimumSize.width, maximum);

				x -= drag;
				width += drag;
			}

			if (NORTH == (direction & NORTH)) {
				drag = getDragDistance(pressed.y, current.y, SNAP_SIZE.height);
				maximum = Math.min(height + y, maximumSize.height);
				drag = getDragBounded(drag, SNAP_SIZE.height, height, minimumSize.height, maximum);

				y -= drag;
				height += drag;
			}

			if (EAST == (direction & EAST)) {
				drag = getDragDistance(current.x, pressed.x, SNAP_SIZE.width);
				maximum = Math.min(getBoundingSize(component).width - x, maximumSize.width);
				drag = getDragBounded(drag, SNAP_SIZE.width, width, minimumSize.width, maximum);

				width += drag;
			}

			if (SOUTH == (direction & SOUTH)) {
				drag = getDragDistance(current.y, pressed.y, SNAP_SIZE.height);
				maximum = Math.min(getBoundingSize(component).height - y, maximumSize.height);
				drag = getDragBounded(drag, SNAP_SIZE.height, height, minimumSize.height, maximum);

				height += drag;
			}

			component.setBounds(x, y, width, height);
			component.validate();
		}

		/*
		 * @see MouseAdapter#mouseEntered(MouseEvent)
		 */
		public void mouseEntered(MouseEvent e) {
			if (isResizable() && !resizing) {
				cursor = component.getCursor();
			}
		}

		/*
		 * @see MouseAdapter#mouseExited(MouseEvent)
		 */
		public void mouseExited(MouseEvent e) {
			if (isResizable() && !resizing) {
				component.setCursor(cursor);
			}
		}

		/*
		 * @see MouseAdapter#mouseMoved(MouseEvent)
		 */
		public void mouseMoved(MouseEvent e) {
			if (!isResizable()) {
				return;
			}

			Point current = e.getPoint();

			direction = 0;

			if (current.x < INSETS.left) {
				direction += WEST;
			}

			if (current.x > component.getWidth() - INSETS.right - 1) {
				direction += EAST;
			}

			if (current.y < INSETS.top) {
				direction += NORTH;
			}

			if (current.y > component.getHeight() - INSETS.bottom - 1) {
				direction += SOUTH;
			}

			if (direction == 0) {
				component.setCursor(cursor);
			} else {
				component.setCursor(Cursor.getPredefinedCursor(cursors.get(direction)));
			}
		}

		/*
		 * @see MouseAdapter#mousePressed(MouseEvent)
		 */
		public void mousePressed(MouseEvent e) {
			if (!isResizable() || direction == 0) {
				return;
			}

			bounds = component.getBounds();
			pressed = e.getLocationOnScreen();
			resizing = true;

			if (component instanceof JComponent) {
				JComponent jComponent = (JComponent) component;
				autoscrolls = jComponent.getAutoscrolls();
				jComponent.setAutoscrolls(false);
			}
		}

		/*
		 * @see MouseAdapter#mouseReleased(MouseEvent)
		 */
		public void mouseReleased(MouseEvent e) {
			if (!isResizable()) {
				return;
			}

			component.setCursor(cursor);

			resizing = false;

			if (component instanceof JComponent) {
				((JComponent) component).setAutoscrolls(autoscrolls);
			}
		}
	}

	private static class TitleMouseListener extends MouseAdapter {

		private Frame frame;

		private TitleMouseListener(CachedComponent cached) {
			frame = cached.frame;
		}

		/*
		 * @see MouseAdapter#mouseClicked(MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) {
			if (MouseEvent.BUTTON1 == e.getButton() && 2 == e.getClickCount()) {
				if (isMaximized(frame)) {
					frame.setExtendedState(Frame.NORMAL);
				} else {
					frame.setExtendedState(Frame.MAXIMIZED_BOTH);
				}
			}
		}
	}
}