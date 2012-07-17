package com.neocotic.wal.util;

/**
 * TODO
 * 
 * @author Alasdair Mercer
 * @param <T>
 */
public interface Filter<T>
{

    /**
     * TODO
     * 
     * @param item
     * @return
     */
    public boolean include(T item);

    /**
     * TODO
     * 
     * @author Alasdair Mercer
     * @param <T>
     */
    public static class Default<T> implements Filter<T>
    {

        /**
         * TODO
         * 
         * @param item
         * @return
         */
        public boolean include(T item)
        {
            return true;
        }
    }
}