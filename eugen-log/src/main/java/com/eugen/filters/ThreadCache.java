package com.eugen.filters;

/**
 * ThreadLocal缓存
 * 
 * @author Eugen
 * @version 2017年11月2日
 * @see ThreadCache
 * @since
 */
public class ThreadCache
{
    /**
     * cache
     */
    private static ThreadLocal<String> TREAD_LOACAL = new ThreadLocal<String>();

    public static String getPostRequestParams()
    {
        return TREAD_LOACAL.get();
    }

    /**
     * 设置缓存
     * 
     * @param value
     *            待缓存值
     * @see
     */
    public static void setPostRequestParams(String value)
    {
        TREAD_LOACAL.set(value);
    }

    /**
     * 删除缓存
     * 
     * @see
     */
    public static void removePostRequestParams()
    {
        TREAD_LOACAL.remove();
    }
}