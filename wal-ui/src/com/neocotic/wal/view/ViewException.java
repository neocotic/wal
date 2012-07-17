package com.neocotic.wal.view;

import com.neocotic.wal.exception.WalException;

public class ViewException extends WalException
{

    public ViewException()
    {
        super();
    }

    public ViewException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ViewException(String message)
    {
        super(message);
    }

    public ViewException(Throwable cause)
    {
        super(cause);
    }
}