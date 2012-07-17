package com.neocotic.wal.view.event;

import java.util.Stack;

public class ViewEvent
{

    public static final int VIEW_CHANGED = 0;

    public static final int VIEW_ENTERED = 1;

    public static final int VIEW_EXITED = 2;

    private int id;

    private String source;

    private Stack<String> stack;

    private String target;

    public ViewEvent(String source, String target, Stack<String> stack, int id)
    {
        if (stack == null) { throw new IllegalArgumentException("stack"); }

        this.source = source;
        this.target = target;
        this.stack = stack;
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public Stack<String> getStack()
    {
        return stack;
    }

    public String getSource()
    {
        return source;
    }

    public String getTarget()
    {
        return target;
    }
}