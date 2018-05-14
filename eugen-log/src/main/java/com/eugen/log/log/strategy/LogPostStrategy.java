package com.eugen.log.log.strategy;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;


/**
 * 处理post请求
 * 
 * @author Eugen
 * @version 2018年5月14日
 * @see LogPostStrategy
 * @since
 */
@Component
public class LogPostStrategy extends LogStrategy
{
    protected String handleLog(HttpServletRequest request)
    {
        return "";
    }
}
