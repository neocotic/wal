package com.neocotic.wal.ui;

public interface StyledComponent
{

    public Style getStyle();

    public void restyle();

    public void setStyle(String style);

    public void setStyle(Style style);
}