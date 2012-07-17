package com.neocotic.wal.view;

import com.neocotic.wal.ui.StyledButton;
import com.neocotic.wal.ui.StyledTextField;
import com.neocotic.wal.view.View;
import com.neocotic.wal.view.ViewInfo;

@ViewInfo("launcher")
public class LauncherView extends View
{

    public LauncherView(String viewName)
    {
        super(viewName);

        StyledTextField urlField = new StyledTextField();
        // TODO: Do more
        getContentPane().add(urlField);

        StyledButton testButton = new StyledButton("test");
        // TODO: Do more
        getControlsPane().add(testButton);

        StyledButton launchButton = new StyledButton("launch");
        // TODO: Do more
        getControlsPane().add(launchButton);
    }

    /*
     * @see View#getHeader()
     */
    @Override
    public String getHeader()
    {
        return "launcher";
    }

    /*
     * @see View#load(String)
     */
    @Override
    protected void load(String action)
    {
        super.load(action);

        // TODO Auto-generated method stub
    }
}