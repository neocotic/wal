package com.neocotic.wal.util.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * Wrapper for {@link java.util.logging.Logger} which allows for cleaner and simpler usage.
 * 
 * @author Alasdair Mercer
 */
public class Logger extends java.util.logging.Logger
{

    /** Cache of classes used to name loggers. */
    private static final Map<String, Class<?>> classCache = new HashMap<String, Class<?>>();

    /**
     * @see java.util.logging.Logger#getAnonymousLogger()
     */
    public static Logger getAnonymousLogger()
    {
        return new Logger(java.util.logging.Logger.getAnonymousLogger());
    }

    /**
     * @see java.util.logging.Logger#getAnonymousLogger(String)
     */
    public static Logger getAnonymousLogger(String resourceBundleName)
    {
        return new Logger(java.util.logging.Logger.getAnonymousLogger(resourceBundleName));
    }

    /**
     * Find or create a logger for a {@link Class}.
     * 
     * The name of <tt>cls</tt> will be used as the name of the logger.
     * 
     * @param cls class for the logger
     * @return A suitable logger.
     * @throws NullPointerException If <tt>cls</tt> is <tt>null</tt>.
     */
    public static Logger getLogger(Class<?> cls)
    {
        String name = null;
        if (cls != null)
        {
            name = cls.getName();
            classCache.put(name, cls);
        }

        return new Logger(java.util.logging.Logger.getLogger(name));
    }

    /**
     * @see java.util.logging.Logger#getLogger(String)
     */
    public static Logger getLogger(String name)
    {
        return new Logger(java.util.logging.Logger.getLogger(name));
    }

    /**
     * @see java.util.logging.Logger#getLogger(String, String)
     */
    public static Logger getLogger(String name, String resourceBundleName)
    {
        return new Logger(java.util.logging.Logger.getLogger(name, resourceBundleName));
    }

    /**
     * Constructs a new {@link Logger} wrapper for the specified logger.
     * 
     * @param logger logger to be wrapped
     */
    protected Logger(java.util.logging.Logger logger)
    {
        super(logger.getName(), logger.getResourceBundleName());
    }

    /**
     * Constructs a new {@link Logger} for a named subsystem.
     * 
     * @param name name for the logger
     * @param resourceBundleName name of {@link ResourceBundle} to be used for localising messages for this logger
     */
    protected Logger(String name, String resourceBundleName)
    {
        super(name, resourceBundleName);
    }

    /**
     * Log a <tt>CONFIG</tt> message, with one object parameter.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param param parameter to the message
     */
    public void config(String msg, Object param)
    {
        super.log(Level.CONFIG, msg, param);
    }

    /**
     * Log a <tt>CONFIG</tt> message, with an array of object parameters.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param params array of parameters to the message
     */
    public void config(String msg, Object[] params)
    {
        super.log(Level.CONFIG, msg, params);
    }

    /**
     * Log a <tt>CONFIG</tt> message, with associated {@link Throwable} information.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param thrown throwable associated with log message
     */
    public void config(String msg, Throwable thrown)
    {
        super.log(Level.CONFIG, msg, thrown);
    }

    /**
     * Log a <tt>c</tt> message using the {@link Throwable} information.
     * 
     * @param thrown throwable used to create log message
     */
    public void config(Throwable thrown)
    {
        super.log(Level.CONFIG, thrown.getMessage(), thrown);
    }

    /**
     * Log a <tt>FINE</tt> message, with one object parameter.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param param parameter to the message
     */
    public void fine(String msg, Object param)
    {
        super.log(Level.FINE, msg, param);
    }

    /**
     * Log a <tt>FINE</tt> message, with an array of object parameters.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param params array of parameters to the message
     */
    public void fine(String msg, Object[] params)
    {
        super.log(Level.FINE, msg, params);
    }

    /**
     * Log a <tt>FINE</tt> message, with associated {@link Throwable} information.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param thrown throwable associated with log message
     */
    public void fine(String msg, Throwable thrown)
    {
        super.log(Level.FINE, msg, thrown);
    }

    /**
     * Log a <tt>FINE</tt> message using the {@link Throwable} information.
     * 
     * @param thrown throwable used to create log message
     */
    public void fine(Throwable thrown)
    {
        super.log(Level.FINE, thrown.getMessage(), thrown);
    }

    /**
     * Log a <tt>FINER</tt> message, with one object parameter.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param param parameter to the message
     */
    public void finer(String msg, Object param)
    {
        super.log(Level.FINER, msg, param);
    }

    /**
     * Log a <tt>FINER</tt> message, with an array of object parameters.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param params array of parameters to the message
     */
    public void finer(String msg, Object[] params)
    {
        super.log(Level.FINER, msg, params);
    }

    /**
     * Log a <tt>FINER</tt> message, with associated {@link Throwable} information.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param thrown throwable associated with log message
     */
    public void finer(String msg, Throwable thrown)
    {
        super.log(Level.FINER, msg, thrown);
    }

    /**
     * Log a <tt>FINER</tt> message using the {@link Throwable} information.
     * 
     * @param thrown throwable used to create log message
     */
    public void finer(Throwable thrown)
    {
        super.log(Level.FINER, thrown.getMessage(), thrown);
    }

    /**
     * Log a <tt>FINEST</tt> message, with one object parameter.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param param parameter to the message
     */
    public void finest(String msg, Object param)
    {
        super.log(Level.FINEST, msg, param);
    }

    /**
     * Log a <tt>FINEST</tt> message, with an array of object parameters.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param params array of parameters to the message
     */
    public void finest(String msg, Object[] params)
    {
        super.log(Level.FINEST, msg, params);
    }

    /**
     * Log a <tt>FINEST</tt> message, with associated {@link Throwable} information.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param thrown throwable associated with log message
     */
    public void finest(String msg, Throwable thrown)
    {
        super.log(Level.FINEST, msg, thrown);
    }

    /**
     * Log a <tt>FINEST</tt> message using the {@link Throwable} information.
     * 
     * @param thrown throwable used to create log message
     */
    public void finest(Throwable thrown)
    {
        super.log(Level.FINEST, thrown.getMessage(), thrown);
    }

    /**
     * Get the named {@link Class} for this logger.
     * 
     * @return Named logger {@link Class} or <tt>null</tt> for anonymous loggers.
     * @throws IllegalStateException If no named {@link Class} was used.
     */
    public Class<?> getNamedClass()
    {
        String name = getName();
        Class<?> cls = classCache.get(name);

        if (cls == null)
        {
            try
            {
                cls = ClassLoader.getSystemClassLoader().loadClass(name);
                if (cls != null)
                {
                    classCache.put(name, cls);
                }
            }
            catch (Exception e)
            {
                throw new IllegalStateException("cannot load class for logger: " + name);
            }
        }

        return cls;
    }

    /**
     * Log a <tt>INFO</tt> message, with one object parameter.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param param parameter to the message
     */
    public void info(String msg, Object param)
    {
        super.log(Level.INFO, msg, param);
    }

    /**
     * Log a <tt>INFO</tt> message, with an array of object parameters.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param params array of parameters to the message
     */
    public void info(String msg, Object[] params)
    {
        super.log(Level.INFO, msg, params);
    }

    /**
     * Log a <tt>INFO</tt> message, with associated {@link Throwable} information.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param thrown throwable associated with log message
     */
    public void info(String msg, Throwable thrown)
    {
        super.log(Level.INFO, msg, thrown);
    }

    /**
     * Log a <tt>INFO</tt> message using the {@link Throwable} information.
     * 
     * @param thrown throwable used to create log message
     */
    public void info(Throwable thrown)
    {
        super.log(Level.INFO, thrown.getMessage(), thrown);
    }

    /**
     * Log a message using the {@link Throwable} information.
     * 
     * @param level one of the message level identifiers
     * @param thrown throwable used to create log message
     */
    public void log(Level level, Throwable thrown)
    {
        super.log(level, thrown.getMessage(), thrown);
    }

    /**
     * Log a message.
     * 
     * @param level string to be parsed as level identifiers
     * @param msg string message (or a key in the message catalogue)
     */
    public void log(String level, String msg)
    {
        super.log(Level.parse(level), msg);
    }

    /**
     * Log a message, with one object parameter.
     * 
     * @param level string to be parsed as level identifiers
     * @param msg string message (or a key in the message catalogue)
     * @param param parameter to the message
     */
    public void log(String level, String msg, Object param)
    {
        super.log(Level.parse(level), msg, param);
    }

    /**
     * Log a message, with an array of object parameters.
     * 
     * @param level string to be parsed as level identifiers
     * @param msg string message (or a key in the message catalogue)
     * @param params array of parameters to the message
     */
    public void log(String level, String msg, Object[] params)
    {
        super.log(Level.parse(level), msg, params);
    }

    /**
     * Log a message, with associated {@link Throwable} information.
     * 
     * @param level string to be parsed as level identifiers
     * @param msg string message (or a key in the message catalogue)
     * @param thrown throwable associated with log message
     */
    public void log(String level, String msg, Throwable thrown)
    {
        super.log(Level.parse(level), msg, thrown);
    }

    /**
     * Log a message using the {@link Throwable} information.
     * 
     * @param level string to be parsed as level identifiers
     * @param thrown throwable used to create log message
     */
    public void log(String level, Throwable thrown)
    {
        super.log(Level.parse(level), thrown.getMessage(), thrown);
    }

    /**
     * Log a <tt>SEVERE</tt> message, with one object parameter.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param param parameter to the message
     */
    public void severe(String msg, Object param)
    {
        super.log(Level.SEVERE, msg, param);
    }

    /**
     * Log a <tt>SEVERE</tt> message, with an array of object parameters.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param params array of parameters to the message
     */
    public void severe(String msg, Object[] params)
    {
        super.log(Level.SEVERE, msg, params);
    }

    /**
     * Log a <tt>SEVERE</tt> message, with associated {@link Throwable} information.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param thrown throwable associated with log message
     */
    public void severe(String msg, Throwable thrown)
    {
        super.log(Level.SEVERE, msg, thrown);
    }

    /**
     * Log a <tt>SEVERE</tt> message using the {@link Throwable} information.
     * 
     * @param thrown throwable used to create log message
     */
    public void severe(Throwable thrown)
    {
        super.log(Level.SEVERE, thrown.getMessage(), thrown);
    }

    /**
     * TODO: Document
     * 
     * @param thrown
     * @throws IllegalStateException
     */
    public void throwing(Throwable thrown)
    {
        // TODO: Use Classes.getRuntimeLocator()
        super.throwing(getNamedClass().getName(), null, thrown);
    }

    /**
     * TODO: Document
     * 
     * @param sourceMethod
     * @param thrown
     * @throws IllegalStateException
     */
    public void throwing(String sourceMethod, Throwable thrown)
    {
        super.throwing(getNamedClass().getName(), sourceMethod, thrown);
    }

    /**
     * Log a <tt>WARNING</tt> message, with one object parameter.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param param parameter to the message
     */
    public void warning(String msg, Object param)
    {
        super.log(Level.WARNING, msg, param);
    }

    /**
     * Log a <tt>WARNING</tt> message, with an array of object parameters.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param params array of parameters to the message
     */
    public void warning(String msg, Object[] params)
    {
        super.log(Level.WARNING, msg, params);
    }

    /**
     * Log a <tt>WARNING</tt> message, with associated {@link Throwable} information.
     * 
     * @param msg string message (or a key in the message catalogue)
     * @param thrown throwable associated with log message
     */
    public void warning(String msg, Throwable thrown)
    {
        super.log(Level.WARNING, msg, thrown);
    }

    /**
     * Log a <tt>WARNING</tt> message using the {@link Throwable} information.
     * 
     * @param thrown throwable used to create log message
     */
    public void warning(Throwable thrown)
    {
        super.log(Level.WARNING, thrown.getMessage(), thrown);
    }
}