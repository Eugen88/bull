package com.eugen;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public abstract class Fra
{
    /**
     * 获取HttpServeltRequest
     * 
     * @return HttpServletRequest
     */
    protected HttpServletRequest getRequest()
    {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取HttpServletResponse
     * 
     * @return HttpServletResponse
     */
    protected HttpServletResponse getResponse()
    {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
