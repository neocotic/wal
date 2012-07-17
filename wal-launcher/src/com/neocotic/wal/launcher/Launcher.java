package com.neocotic.wal.launcher;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.LogManager;

import com.neocotic.wal.util.Strings;
import com.neocotic.wal.util.logging.Logger;

/**
 * Entry point for the application.
 * 
 * @author Alasdair Mercer
 */
public class Launcher
{

    public static final int LAUNCH_GUI = 0;

    public static final int LAUNCH_TERMINAL = 1;

    public static final String LOGGING_CONFIG = "META-INF/logging.properties";

    private static final String OPTION_PREFIX = "-";

    private static final String OPT_LANGUAGE = OPTION_PREFIX + "language";

    private static final String OPT_TERMINAL = OPTION_PREFIX + "terminal";

    private static Logger logger;

    /**
     * TODO: Document
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        initLogging();

        Launcher launcher = new Launcher();
        launcher.start(args);
    }

    /**
     * TODO: Document
     */
    // TODO: Fix configuration to ensure custom Logger is used
    private static void initLogging()
    {
        LogManager manager = LogManager.getLogManager();
        InputStream input = null;

        try
        {
            input = ClassLoader.getSystemResourceAsStream(LOGGING_CONFIG);
            if (input != null)
            {
                manager.readConfiguration(input);
            }
        }
        catch (IOException e)
        {}
        finally
        {
            try
            {
                input.close();
            }
            catch (Exception e)
            {}
        }

        Logger root = Logger.getLogger("com.neocotic.wal");
        root.setUseParentHandlers(false);

        logger = Logger.getLogger(Launcher.class);
        logger.info("Logging initialised at level '" + root.getLevel() + "'");
    }

    /**
     * Constructs a new {@link Launcher}.
     */
    Launcher()
    {
    }

    /**
     * TODO: Document
     * 
     * @param type
     * @param path
     * @param language
     * @throws Exception
     */
    private void launch(int type, String path, String language) throws Exception
    {
        if (type == LAUNCH_GUI && GraphicsEnvironment.isHeadless())
        {
            type = LAUNCH_TERMINAL;
        }

        // TODO: Remove test
        logger.throwing(null);

        switch (type)
        {
        case LAUNCH_GUI:
            LauncherGui.run();
            break;
        case LAUNCH_TERMINAL:
            launchTerminal(path, language);
            break;
        }
    }

    /**
     * TODO: Document
     * 
     * @param path
     * @param language
     */
    private void launchTerminal(String path, String language)
    {
        // TODO: Implement terminal access
        throw new UnsupportedOperationException("Terminal access not supported");
    }

    /**
     * TODO: Document
     * 
     * @param args
     */
    private void start(String[] args)
    {
        logger.info("Command line parameters: " + Strings.join(args, " "));

        logger.throwing(null);

        try
        {
            Iterator<String> iterator = Arrays.asList(args).iterator();
            String language = null;
            String path = null;
            int type = LAUNCH_GUI;

            while (iterator.hasNext())
            {
                String arg = iterator.next().trim();

                try
                {
                    if (OPT_TERMINAL.equalsIgnoreCase(arg))
                    {
                        type = LAUNCH_TERMINAL;
                    }
                    else if (OPT_LANGUAGE.equalsIgnoreCase(arg))
                    {
                        language = iterator.next().trim();
                    }
                    else
                    {
                        path = arg;
                    }
                }
                catch (NoSuchElementException e)
                {
                    logger.severe("Option \"" + arg + "\" requires an argument", e);
                    System.exit(1);
                }
            }

            launch(type, path, language);
        }
        catch (Exception e)
        {
            logger.severe(e);
            System.exit(1);
        }
    }
}