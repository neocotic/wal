package com.neocotic.wal.view;

/**
 * TODO
 * 
 * @author Alasdair Mercer
 */
public class ViewDetails
{

    private View instance;

    private final String name;

    private final Class<? extends View> viewClass;

    public ViewDetails(final String name, final Class<? extends View> viewClass)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name");
        }
        else if (viewClass == null) { throw new IllegalArgumentException("viewClass"); }

        this.name = name;
        this.viewClass = viewClass;
    }

    protected void destroyInstance()
    {
        // TODO: Any advanced work required?
        instance = null;
    }

    public View getInstance()
    {
        if (instance == null)
        {
            instance = newInstance();
        }

        return instance;
    }

    public String getName()
    {
        return name;
    }

    public Class<? extends View> getViewClass()
    {
        return viewClass;
    }

    public boolean hasInstance()
    {
        return instance != null;
    }

    protected View newInstance()
    {
        destroyInstance();

        try
        {
            instance = viewClass.getConstructor(String.class).newInstance(name);
        }
        catch (Exception e)
        {
            throw new ViewException("Unable to create view", e);
        }

        return instance;
    }
}