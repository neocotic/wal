package com.neocotic.wal.util;

public class Strings
{

    public static final String EMPTY = "";

    public static boolean isBlank(String str)
    {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isEmpty(String str)
    {
        return length(str) > 0;
    }

    public static String join(String[] arr, String separator)
    {
        if (arr == null) return null;

        if (separator == null)
        {
            separator = EMPTY;
        }

        StringBuilder buf = new StringBuilder();

        for (String str : arr)
        {
            if (buf.length() > 0)
            {
                buf.append(separator);
            }
            if (str != null)
            {
                buf.append(str);
            }
        }

        return buf.toString();
    }

    public static int length(String str)
    {
        return (str == null) ? 0 : str.length();
    }

    public static String trim(String str)
    {
        return (str == null) ? str : str.trim();
    }

    public static String trimToEmpty(String str)
    {
        String ret = trim(str);
        return (ret == null) ? EMPTY : ret;
    }

    public static String trimToNull(String str)
    {
        String ret = trim(str);
        return (length(ret) > 0) ? ret : null;
    }

    Strings()
    {
    }
}