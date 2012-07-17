package com.neocotic.wal.ui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.border.Border;

public class Style
{

    public static final String DEFAULT_STATE = "default";

    private Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();

    public Style()
    {
    }

    public Style(String... styles)
    {
        extend(styles);
    }

    public Style(Style... styles)
    {
        extend(styles);
    }

    public void clear()
    {
        map.clear();
    }

    public void clear(String key)
    {
        if (map.containsKey(key))
        {
            map.get(key).clear();
        }
    }

    public void extend(String... styles)
    {
        for (String style : styles)
        {
            extend(StyleManager.get(style));
        }
    }

    public void extend(Style... styles)
    {
        for (Style style : styles)
        {
            for (String key : style.map.keySet())
            {
                Map<String, Object> states = style.map.get(key);

                if (map.containsKey(key))
                {
                    map.get(key).putAll(states);
                }
                else
                {
                    map.put(key, new HashMap<String, Object>(states));
                }
            }
        }
    }

    public Boolean getBoolean(String key)
    {
        return (Boolean) getObject(key);
    }

    public Boolean getBoolean(String key, String state)
    {
        return (Boolean) getObject(key, state);
    }

    public Border getBorder(String key)
    {
        return (Border) getObject(key);
    }

    public Border getBorder(String key, String state)
    {
        return (Border) getObject(key, state);
    }

    public Color getColor(String key)
    {
        return (Color) getObject(key);
    }

    public Color getColor(String key, String state)
    {
        return (Color) getObject(key, state);
    }

    public Cursor getCursor(String key)
    {
        return (Cursor) getObject(key);
    }

    public Cursor getCursor(String key, String state)
    {
        return (Cursor) getObject(key, state);
    }

    public Set<String> getKeys()
    {
        return map.keySet();
    }

    public Font getFont(String key)
    {
        return (Font) getObject(key);
    }

    public Font getFont(String key, String state)
    {
        return (Font) getObject(key, state);
    }

    public Integer getInteger(String key)
    {
        return (Integer) getObject(key);
    }

    public Integer getInteger(String key, String state)
    {
        return (Integer) getObject(key, state);
    }

    public Object getObject(String key)
    {
        return getObject(key, null);
    }

    public Object getObject(String key, String state)
    {
        if (state == null)
        {
            state = DEFAULT_STATE;
        }

        Object object = null;

        if (map.containsKey(key))
        {
            object = map.get(key).get(state);
        }

        if (object == null && !DEFAULT_STATE.equals(state))
        {
            object = getObject(key, null);
        }

        return object;
    }

    public Rectangle getRectangle(String key)
    {
        return (Rectangle) getObject(key);
    }

    public Rectangle getRectangle(String key, String state)
    {
        return (Rectangle) getObject(key, state);
    }

    public Dimension getSize(String key)
    {
        return (Dimension) getObject(key);
    }

    public Dimension getSize(String key, String state)
    {
        return (Dimension) getObject(key, state);
    }

    public Set<String> getStates(String key)
    {
        Set<String> states = null;

        if (map.containsKey(key))
        {
            states = map.get(key).keySet();
        }

        return states;
    }

    public String getString(String key)
    {
        return (String) getObject(key);
    }

    public String getString(String key, String state)
    {
        return (String) getObject(key, state);
    }

    public boolean hasBoolean(String key)
    {
        return hasObject(key) && getObject(key) instanceof Boolean;
    }

    public boolean hasBoolean(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof Boolean;
    }

    public boolean hasBorder(String key)
    {
        return hasObject(key) && getObject(key) instanceof Border;
    }

    public boolean hasBorder(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof Border;
    }

    public boolean hasColor(String key)
    {
        return hasObject(key) && getObject(key) instanceof Color;
    }

    public boolean hasColor(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof Color;
    }

    public boolean hasCursor(String key)
    {
        return hasObject(key) && getObject(key) instanceof Cursor;
    }

    public boolean hasCursor(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof Cursor;
    }

    public boolean hasFont(String key)
    {
        return hasObject(key) && getObject(key) instanceof Font;
    }

    public boolean hasFont(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof Font;
    }

    public boolean hasInteger(String key)
    {
        return hasObject(key) && getObject(key) instanceof Integer;
    }

    public boolean hasInteger(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof Integer;
    }

    public boolean hasObject(String key)
    {
        return map.containsKey(key);
    }

    public boolean hasObject(String key, String state)
    {
        return map.containsKey(key) && map.get(key).containsKey(state);
    }

    public boolean hasRectangle(String key)
    {
        return hasObject(key) && getObject(key) instanceof Rectangle;
    }

    public boolean hasRectangle(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof Rectangle;
    }

    public boolean hasSize(String key)
    {
        return hasObject(key) && getObject(key) instanceof Dimension;
    }

    public boolean hasSize(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof Dimension;
    }

    public boolean hasString(String key)
    {
        return hasObject(key) && getObject(key) instanceof String;
    }

    public boolean hasString(String key, String state)
    {
        return hasObject(key, state) && getObject(key, state) instanceof String;
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    public boolean isEmpty(String key)
    {
        if (key != null && map.containsKey(key)) { return map.get(key).isEmpty(); }

        return map.isEmpty();
    }

    public void remove(String key)
    {
        map.remove(key);
    }

    public void remove(String key, String state)
    {
        if (state == null)
        {
            map.remove(key);
        }
        else if (map.containsKey(key))
        {
            map.get(key).remove(state);
        }
    }

    public void setBoolean(String key, Boolean bool)
    {
        setBoolean(key, null, bool);
    }

    public void setBoolean(String key, String state, Boolean bool)
    {
        setObject(key, state, bool);
    }

    public void setBorder(String key, Border border)
    {
        setBorder(key, null, border);
    }

    public void setBorder(String key, String state, Border border)
    {
        setObject(key, state, border);
    }

    public void setColor(String key, Color color)
    {
        setColor(key, null, color);
    }

    public void setColor(String key, String color)
    {
        setColor(key, null, color);
    }

    public void setColor(String key, String state, Color color)
    {
        setObject(key, state, color);
    }

    public void setColor(String key, String state, String color)
    {
        setColor(key, state, Color.decode(color));
    }

    public void setCursor(String key, Cursor cursor)
    {
        setCursor(key, null, cursor);
    }

    public void setCursor(String key, int cursor)
    {
        setCursor(key, null, cursor);
    }

    public void setCursor(String key, String cursor) throws HeadlessException, AWTException
    {
        setCursor(key, null, cursor);
    }

    public void setCursor(String key, String state, Cursor cursor)
    {
        setObject(key, state, cursor);
    }

    public void setCursor(String key, String state, int cursor)
    {
        setCursor(key, state, Cursor.getPredefinedCursor(cursor));
    }

    public void setCursor(String key, String state, String cursor) throws HeadlessException, AWTException
    {
        setCursor(key, state, Cursor.getSystemCustomCursor(cursor));
    }

    public void setFont(String key, Font font)
    {
        setFont(key, null, font);
    }

    public void setFont(String key, String state, Font font)
    {
        setObject(key, state, font);
    }

    public void setInteger(String key, Integer integer)
    {
        setInteger(key, null, integer);
    }

    public void setInteger(String key, String state, Integer integer)
    {
        setObject(key, state, integer);
    }

    public void setObject(String key, Object object)
    {
        setObject(key, null, object);
    }

    public void setObject(String key, String state, Object object)
    {
        if (state == null)
        {
            state = DEFAULT_STATE;
        }

        if (!map.containsKey(key))
        {
            map.put(key, new HashMap<String, Object>());
        }

        map.get(key).put(state, object);
    }

    public void setRectangle(String key, Rectangle rectangle)
    {
        setRectangle(key, null, rectangle);
    }

    public void setRectangle(String key, String state, Rectangle rectangle)
    {
        setObject(key, state, rectangle);
    }

    public void setSize(String key, Dimension size)
    {
        setSize(key, null, size);
    }

    public void setSize(String key, String state, Dimension size)
    {
        setObject(key, state, size);
    }

    public void setString(String key, String string)
    {
        setString(key, null, string);
    }

    public void setString(String key, String state, String string)
    {
        setObject(key, state, string);
    }
}