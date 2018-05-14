package com.eugen.log.log.strategy;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 处理get请求
 * 
 * @author Eugen
 * @version 2018年5月14日
 * @see LogGetStrategy
 * @since
 */
@Component
public class LogGetStrategy extends LogStrategy
{
    private static final Logger LOG = LoggerFactory.getLogger(LogGetStrategy.class);

    protected String handleLog(HttpServletRequest request)
    {
        String queryString = request.getQueryString();
        if (null == queryString)
        {
            return "";
        }

        StringBuilder result = new StringBuilder("{");
        String[] value = queryString.split("&");
        for (String val : value)
        {
            result.append(transfer(val));
        }

        int length = result.length();
        return result.replace(length - 1, length, "}").toString();
    }

    /**
     * 将查询标识转换成有意义的文字
     * 
     * @param key
     *            查询标识
     * @return String
     * @see
     */
    private String transfer(String key)
    {
        String[] val = key.split("=");
        return new StringBuilder("\"").append(this.getProp(val[0])).append("\":\"").append(decodeUrl(val[1])).append("\",").toString();
    }

    private String decodeUrl(String value)
    {
        try
        {
            return URLDecoder.decode(value, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            LOG.error("decode url error.", e);
        }

        return "";
    }
}
