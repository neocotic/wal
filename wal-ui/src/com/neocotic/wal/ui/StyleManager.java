package com.neocotic.wal.ui;

import java.util.HashMap;
import java.util.Map;

public final class StyleManager
{

    private static final Map<String, Style> map = new HashMap<String, Style>();

    public static void clear()
    {
        map.clear();
    }

    public static void clear(String key)
    {
        if (map.containsKey(key))
        {
            map.get(key).clear();
        }
    }

    public static Style get(String key)
    {
        return map.get(key);
    }

    public static boolean has(String key)
    {
        return map.containsKey(key);
    }

    public static void remove(String key)
    {
        map.remove(key);
    }

    public static void set(String key, Style style)
    {
        map.put(key, style);
    }
}