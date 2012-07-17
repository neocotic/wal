package com.neocotic.wal.launcher;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.neocotic.wal.exception.WalException;
import com.neocotic.wal.ui.Frame;
import com.neocotic.wal.ui.StyleFactory;
import com.neocotic.wal.view.ViewManager;

/**
 * Initialiser of the UI aspects of wal.
 * 
 * @author Alasdair Mercer
 */
public class LauncherGui
{

    /**
     * TODO: Complete documentation
     * 
     * @throws Exception If an error occurs.
     */
    public static void run() throws Exception
    {
        SwingUtilities.invokeLater(new Runnable()
        {

            /*
             * @see Runnable#run()
             */
            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

                    StyleFactory.create();

                    Frame frame = new Frame();
                    frame.setVisible(true);

                    ViewManager vm = ViewManager.getInstance();
                    vm.setFrame(frame);
                }
                catch (Exception e)
                {
                    throw new WalException(e);
                }
            }
        });
    }
}