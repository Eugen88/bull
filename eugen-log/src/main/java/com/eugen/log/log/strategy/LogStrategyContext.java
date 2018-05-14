package com.eugen.log.log.strategy;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eugen.Constant;
import com.eugen.log.log.LogEntity;
import com.eugen.resp.Response;


@Component
public class LogStrategyContext
{
    private static final Logger LOG = LoggerFactory.getLogger(LogStrategyContext.class);

    /**
     * 具体日志处理对象，避免if-else多重判断
     */
    private static final Map<String, LogStrategy> LOG_STRATEGY_MAP = new HashMap<>();

    @Autowired
    private LogGetStrategy logGetStrategy;

    @Autowired
    private LogPostStrategy logPostStrategy;

    /**
     * 启动时，注入日志操作对象
     * 
     * @see
     */
    @PostConstruct
    private void init()
    {
        LOG_STRATEGY_MAP.put(Constant.HTTP_METHOD_GET, logGetStrategy);
        LOG_STRATEGY_MAP.put(Constant.HTTP_METHOD_POST, logPostStrategy);
    }

    /**
     * 记录日志
     * 
     * @param request
     * @see
     */
    public void recordLog(HttpServletRequest request, LogEntity logEntity, Response resp)
    {
        String methodName = request.getMethod().toUpperCase();
        LogStrategy logStrategy = LOG_STRATEGY_MAP.get(methodName);
        if (null == logStrategy)
        {
            LOG.error(String.format("Unkown method: %s", methodName));
            return;
        }
        logStrategy.recordLog(request, logEntity, resp);
    }
}
