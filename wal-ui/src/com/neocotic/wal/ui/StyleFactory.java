package com.neocotic.wal.ui;

import java.awt.Cursor;
import java.awt.Dimension;

public final class StyleFactory
{

    public static void create()
    {
        StyleManager.set("global", createGlobal());
        StyleManager.set("frame", createFrame());
        // TODO: Apply to tooltips
        // TODO: See http://docs.oracle.com/javase/1.4.2/docs/api/javax/swing/JToolTip.html
        StyleManager.set("toolTip", createToolTip());
        StyleManager.set("button", createButton());
        StyleManager.set("windowButton", createWindowButton());
        StyleManager.set("windowCloseButton", createWindowCloseButton());
        StyleManager.set("windowMaximizeButton", createWindowMaximizeButton());
        StyleManager.set("windowMinimizeButton", createWindowMinimizeButton());
    }

    private static Style createButton()
    {
        Style style = new Style("global");
        // TODO: Disabled state
        style.setColor("background", "#F0F0F0");
        style.setColor("backgroundGradient", "#E5E5E5");
        style.setColor("border", "#ACACAC");
        style.setColor("border", "focused", "#678DB4");

        return style;
    }

    private static Style createFrame()
    {
        Style style = new Style("global");
        style.setColor("background", "#FFFFFF");
        style.setColor("background", "inactive", "#D9D9D9");
        style.setColor("border", "#C3C3C3");

        return style;
    }

    private static Style createGlobal()
    {
        Style style = new Style();
        style.setColor("foreground", "#000000");

        return style;
    }

    private static Style createWindowButton()
    {
        Style style = new Style("global");
        style.setBoolean("focusable", Boolean.FALSE);
        // style.setColor("background", "#FFFFFF");
        style.setColor("foreground", "#565656");
        style.setColor("foreground", "disabled", "#BFBFBF");
        // TODO: Improve colour selection
        style.setColor("foreground", "hover", style.getColor("foreground").brighter());
        style.setColor("foreground", "pressed", style.getColor("foreground").darker());
        style.setCursor("cursor", Cursor.getDefaultCursor());

        return style;
    }

    private static Style createWindowCloseButton()
    {
        Style style = new Style("windowButton");
        // TODO: Disabled state
        style.setColor("background", "#C75151");
        // TODO: Improve colour selection
        style.setColor("background", "hover", style.getColor("background").brighter());
        style.setColor("backgroundGradient", "pressed", style.getColor("background").brighter());
        style.setColor("foreground", "#FFFFFF");
        style.remove("foreground", "hover");
        style.remove("foreground", "pressed");
        style.setSize("preferredSize", new Dimension(45, 20));

        return style;
    }

    private static Style createWindowMaximizeButton()
    {
        Style style = new Style("windowButton");
        style.setSize("preferredSize", new Dimension(24, 20));

        return style;
    }

    private static Style createWindowMinimizeButton()
    {
        Style style = new Style("windowButton");
        style.setSize("preferredSize", new Dimension(26, 20));

        return style;
    }

    private static Style createToolTip()
    {
        Style style = new Style();
        // TODO: Apply styles

        return style;
    }
}