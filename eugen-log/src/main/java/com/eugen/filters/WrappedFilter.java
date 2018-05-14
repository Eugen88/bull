package com.eugen.filters;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.eugen.Constant;


/**
 * 过滤器，实现功能如下：<br> 1、将post中数据保存到ThreadLocal中，便于后续使用；
 * 
 * @author Eugen
 * @version 2017年11月2日
 * @see WrappedFilter
 * @since
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "wrappedFilter")
public class WrappedFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException,
        ServletException
    {
        HttpServletRequest req = (HttpServletRequest)request;
        WrappedHttpServletRequest requestWrapper = new WrappedHttpServletRequest(req);
        if (Constant.HTTP_METHOD_POST.equals(requestWrapper.getMethod().toUpperCase()))
        {
            // 获取请求参数
            ThreadCache.setPostRequestParams(requestWrapper.getRequestParams());
        }

        // 传递过滤链
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy()
    {}

}
