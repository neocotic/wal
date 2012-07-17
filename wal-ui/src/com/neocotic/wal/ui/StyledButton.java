package com.neocotic.wal.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class StyledButton extends JButton implements StyledComponent
{

    private boolean hover;

    private boolean pressed;

    private Style style = new Style("button");

    public StyledButton()
    {
        super();

        init();
    }

    public StyledButton(Action a)
    {
        super(a);

        init();
    }

    public StyledButton(Icon icon)
    {
        super(icon);

        init();
    }

    public StyledButton(String text, Icon icon)
    {
        super(text, icon);

        init();
    }

    public StyledButton(String text)
    {
        super(text);

        init();
    }

    public void draw(Graphics2D g2d, Dimension size)
    {
    }

    protected String getStyleState()
    {
        if (!isEnabled())
        {
            return "disabled";
        }
        else if (isPressed())
        {
            return "pressed";
        }
        else if (isHover())
        {
            return "hover";
        }
        else if (hasFocus()) { return "focus"; }

        return null;
    }

    public Style getStyle()
    {
        return style;
    }

    private void init()
    {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        addMouseListener(new MouseAdapter()
        {

            /*
             * @see MouseAdapter#mouseEntered(MouseEvent)
             */
            @Override
            public void mouseEntered(MouseEvent e)
            {
                hover = true;
            }

            /*
             * @see MouseAdapter#mouseExited(MouseEvent)
             */
            @Override
            public void mouseExited(MouseEvent e)
            {
                hover = false;
            }

            /*
             * @see MouseAdapter#mousePressed(MouseEvent)
             */
            @Override
            public void mousePressed(MouseEvent e)
            {
                pressed = true;
            }

            /*
             * @see MouseAdapter#mouseReleased(MouseEvent)
             */
            @Override
            public void mouseReleased(MouseEvent e)
            {
                pressed = false;
            }
        });
    }

    public boolean isHover()
    {
        return hover;
    }

    public boolean isPressed()
    {
        return pressed;
    }

    /*
     * @see JComponent#paintComponent(Graphics)
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        Dimension size = getPreferredSize();
        String state = getStyleState();

        Color background = style.getColor("background", state);
        Color backgroundGradient = style.getColor("backgroundGradient", state);

        if (background != null)
        {
            if (backgroundGradient != null)
            {
                g2d.setPaint(new GradientPaint(0, 0, backgroundGradient, 0, size.height, background));
            }
            else
            {
                g2d.setColor(background);
            }

            g2d.fillRect(0, 0, size.width, size.height);
        }

        g2d.setColor(style.getColor("foreground", state));

        super.paintComponent(g2d);

        draw((Graphics2D) g, getPreferredSize());
    }

    public void restyle()
    {
        setBackground(style.getColor("background"));
        setForeground(style.getColor("foreground"));

        if (style.hasBoolean("focusable"))
        {
            setFocusable(style.getBoolean("focusable"));
        }

        if (style.hasCursor("cursor"))
        {
            setCursor(style.getCursor("cursor"));
        }

        if (style.hasFont("font"))
        {
            setFont(style.getFont("font"));
        }

        if (style.hasSize("preferredSize"))
        {
            setPreferredSize(style.getSize("preferredSize"));
        }

        repaint();
    }

    public void setStyle(String style)
    {
        setStyle(StyleManager.get(style));
    }

    public void setStyle(Style style)
    {
        if (style == null)
        {
            style = new Style();
        }

        this.style = style;

        restyle();
    }
}