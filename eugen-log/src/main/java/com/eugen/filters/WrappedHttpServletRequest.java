package com.eugen.filters;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;


/**
 * 包装HttpServletRequest，读取post数据流，再将post数据流回填到request中
 * 
 * @author Eguen
 * @version 2017年11月2日
 * @see WrappedHttpServletRequest
 * @since
 */
public class WrappedHttpServletRequest extends HttpServletRequestWrapper
{

    /**
     * 输入流字节数组
     */
    private byte[] bytes;

    /**
     * 输入流包装类
     */
    private WrappedServletInputStream wrappedServletInputStream;

    public WrappedHttpServletRequest(HttpServletRequest request)
        throws IOException
    {
        super(request);
        // 读取输入流里的请求参数，并保存到bytes里
        bytes = IOUtils.toByteArray(request.getInputStream());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        this.wrappedServletInputStream = new WrappedServletInputStream(byteArrayInputStream);
    }

    /**
     * 把参数重新写进请求里
     */
    public void reWriteInputStream()
    {
        wrappedServletInputStream.setStream(
            new ByteArrayInputStream(bytes != null ? bytes : new byte[0]));
    }

    @Override
    public ServletInputStream getInputStream()
        throws IOException
    {
        return wrappedServletInputStream;
    }

    @Override
    public BufferedReader getReader()
        throws IOException
    {
        return new BufferedReader(new InputStreamReader(wrappedServletInputStream));
    }

    /**
     * 获取post参数，可以自己再转为相应格式
     * 
     * @return String
     * @throws IOException
     *             IO异常
     */
    public String getRequestParams()
        throws IOException
    {
        return new String(bytes, this.getCharacterEncoding());
    }

    private class WrappedServletInputStream extends ServletInputStream
    {
        /**
         * 输入流
         */
        private InputStream stream;

        /**
         * @param stream
         */
        WrappedServletInputStream(InputStream stream)
        {
            this.stream = stream;
        }

        public void setStream(InputStream stream)
        {
            this.stream = stream;
        }

        @Override
        public int read()
            throws IOException
        {
            return stream.read();
        }

        @Override
        public boolean isFinished()
        {
            return true;
        }

        @Override
        public boolean isReady()
        {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener)
        {

        }
    }
}
