package com.neocotic.wal.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.neocotic.wal.view.View;

public class Frame extends JFrame implements StyledComponent
{

    // TODO: Style tooltips

    private JPanel contentPane;

    private JPanel mainPanel;

    private Style style = new Style("frame");

    /**
     * Create the frame.
     */
    public Frame()
    {
        setBackground(null);
        setUndecorated(true);
        setTitle("WAL");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(null);
        contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel windowPanel = new JPanel();
        windowPanel.setBorder(null);
        contentPane.add(windowPanel, BorderLayout.NORTH);
        windowPanel.setBackground(null);
        windowPanel.setLayout(new BorderLayout(0, 0));

        JPanel titleWrapperPanel = new JPanel();
        FlowLayout fl_titleWrapperPanel = (FlowLayout) titleWrapperPanel.getLayout();
        fl_titleWrapperPanel.setVgap(0);
        fl_titleWrapperPanel.setHgap(0);
        titleWrapperPanel.setBackground(null);
        titleWrapperPanel.setBorder(null);
        windowPanel.add(titleWrapperPanel, BorderLayout.WEST);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        titleWrapperPanel.add(horizontalStrut);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titleWrapperPanel.add(titlePanel);

        JLabel titleLabel = new JLabel(getTitle());
        titleLabel.setName("titleLabel");
        titlePanel.add(titleLabel);
        titleLabel.setBackground(null);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(null);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));

        JPanel controlPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) controlPanel.getLayout();
        flowLayout.setHgap(1);
        flowLayout.setVgap(0);
        controlPanel.setBorder(null);
        windowPanel.add(controlPanel, BorderLayout.EAST);
        controlPanel.setBackground(null);

        StyledButton minimizeButton = new StyledButton()
        {

            /*
             * @see StyleButton#draw(Graphics2D, Dimension)
             */
            @Override
            public void draw(Graphics2D g2d, Dimension size)
            {
                super.draw(g2d, size);

                int height = 6;
                int width = 7;

                int x = (size.width - width) / 2;
                int y = (size.height - height) / 2;

                g2d.drawRect(x, y + height, width, 1);
            }
        };
        minimizeButton.setStyle("windowMinimizeButton");
        minimizeButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                if (isMaximized())
                {
                    setExtendedState(MAXIMIZED_BOTH | ICONIFIED);
                }
                else
                {
                    setExtendedState(ICONIFIED);
                }
            }
        });
        minimizeButton.setToolTipText("minimize");
        controlPanel.add(minimizeButton);

        final StyledButton maximizeButton = new StyledButton()
        {

            /*
             * @see StyleButton#draw(Graphics2D, Dimension)
             */
            @Override
            public void draw(Graphics2D g2d, Dimension size)
            {
                super.draw(g2d, size);

                if (isMaximized())
                {
                    int height = 6;
                    int width = 12;

                    int x = (size.width - width) / 2;
                    int y = (size.height - height) / 2;

                    g2d.drawLine(x + (x / 3), y, x + width, y);
                    g2d.drawRect(x + (x / 3), y + 1, width - (x / 3), height);

                    g2d.drawLine(x, y + (y / 3), x + width - (x / 3), y + (y / 3));
                    g2d.drawRect(x, y + (y / 3) + 1, width - (x / 3), height);
                }
                else
                {
                    int height = 6;
                    int width = 9;

                    int x = (size.width - width) / 2;
                    int y = (size.height - height) / 2;

                    g2d.drawLine(x, y, x + width, y);
                    g2d.drawRect(x, y + 1, width, height);
                }
            }
        };
        maximizeButton.setStyle("windowMaximizeButton");
        maximizeButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                if (isMaximized())
                {
                    setExtendedState(NORMAL);
                }
                else
                {
                    setExtendedState(MAXIMIZED_BOTH);
                }
            }
        });
        maximizeButton.setToolTipText("maximize");
        restoreOrMaximize(maximizeButton);
        controlPanel.add(maximizeButton);

        StyledButton closeButton = new StyledButton()
        {

            /*
             * @see StyleButton#draw(Graphics2D, Dimension)
             */
            @Override
            public void draw(Graphics2D g2d, Dimension size)
            {
                super.draw(g2d, size);

                int scale = 6;

                int x = (size.width - scale) / 2;
                int y = (size.height - scale) / 2;

                g2d.drawLine(x, y, x + scale, y + scale);
                g2d.drawLine(x, y + scale, x + scale, y);

                g2d.drawLine(x + 1, y, x + scale + 1, y + scale);
                g2d.drawLine(x + 1, y + scale, x + scale + 1, y);
            }
        };
        closeButton.setStyle("windowCloseButton");
        closeButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        controlPanel.add(closeButton);
        closeButton.setToolTipText("close");

        Component horizontalStrut_1 = Box.createHorizontalStrut(4);
        controlPanel.add(horizontalStrut_1);

        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(null);

        addComponentListener(new ComponentAdapter()
        {

            /*
             * @see ComponentAdapter#componentMoved(ComponentEvent)
             */
            @Override
            public void componentMoved(ComponentEvent e)
            {
                restoreOrMaximize(maximizeButton);
            }

            /*
             * @see ComponentAdapter#componentResized(ComponentEvent)
             */
            @Override
            public void componentResized(ComponentEvent e)
            {
                restoreOrMaximize(maximizeButton);
            }
        });

        addPropertyChangeListener("resizable", new PropertyChangeListener()
        {

            public void propertyChange(PropertyChangeEvent e)
            {
                if (e.getOldValue() != e.getNewValue())
                {
                    maximizeButton.setEnabled((Boolean) e.getNewValue());
                }
            }
        });

        addWindowListener(new WindowAdapter()
        {

            /*
             * @see WindowAdapter#windowActivated(WindowEvent)
             */
            @Override
            public void windowActivated(WindowEvent e)
            {
                restyle();
            }

            /*
             * @see WindowAdapter#windowDeactivated(WindowEvent)
             */
            @Override
            public void windowDeactivated(WindowEvent e)
            {
                restyle();
            }
        });

        Decorator.registerMoveComponent(this);
        Decorator.registerMoveComponent(windowPanel);
        Decorator.registerResizeComponent(this);
        Decorator.registerTitleComponent(windowPanel);
    }

    /*
     * @see Window#dispose()
     */
    @Override
    public void dispose()
    {
        super.dispose();

        System.exit(0);
    }

    public Style getStyle()
    {
        return style;
    }

    private String getStyleState()
    {
        if (!isEnabled())
        {
            return "disabled";
        }
        else if (!isActive())
        {
            return "inactive";
        }
        else if (hasFocus()) { return "focus"; }

        return null;
    }

    public View getView()
    {
        return (View) ((mainPanel.getComponentCount() > 0) ? mainPanel.getComponent(0) : null);
    }

    private boolean isMaximized()
    {
        return MAXIMIZED_BOTH == getExtendedState();
    }

    private void restoreOrMaximize(JButton button)
    {
        if (isMaximized())
        {
            button.setToolTipText("restore down");
        }
        else
        {
            button.setToolTipText("maximize");
        }
    }

    public void restyle()
    {
        String state = getStyleState();

        contentPane.setBackground(style.getColor("background", state));
        contentPane.setForeground(style.getColor("foreground", state));

        if (style.hasCursor("cursor", state))
        {
            contentPane.setCursor(style.getCursor("cursor", state));
        }

        if (style.hasFont("font", state))
        {
            contentPane.setFont(style.getFont("font", state));
        }

        if (style.hasSize("preferredSize", state))
        {
            contentPane.setPreferredSize(style.getSize("preferredSize", state));
        }
    }

    /*
     * @see Frame#setExtendedState(int)
     */
    @Override
    public void setExtendedState(int state)
    {
        if ((state & MAXIMIZED_BOTH) == MAXIMIZED_BOTH)
        {
            Rectangle bounds = getGraphicsConfiguration().getBounds();
            Rectangle maxBounds = null;

            if (bounds.x == 0 && bounds.y == 0)
            {
                Insets screenInsets = getToolkit().getScreenInsets(getGraphicsConfiguration());
                maxBounds = new Rectangle(screenInsets.left, screenInsets.top, bounds.width - screenInsets.right
                        - screenInsets.left, bounds.height - screenInsets.bottom - screenInsets.top);
            }

            super.setMaximizedBounds(maxBounds);
        }

        super.setExtendedState(state);
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

    public void setView(View view)
    {
        mainPanel.removeAll();
        mainPanel.add(view);
    }
}