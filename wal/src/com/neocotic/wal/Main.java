package com.neocotic.wal;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.neocotic.wal.ui.Frame;
import com.neocotic.wal.ui.StyleFactory;
import com.neocotic.wal.view.ViewManager;

/**
 * TODO
 * 
 * @author Alasdair Mercer
 */
public class Main {

	/**
	 * TODO
	 * 
	 * @param args
	 *            any command line parameters
	 * @throws Exception
	 *             If an error occurs.
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

					StyleFactory.create();

					Frame frame = new Frame();
					frame.setVisible(true);

					ViewManager vm = ViewManager.getInstance();
					vm.setFrame(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}