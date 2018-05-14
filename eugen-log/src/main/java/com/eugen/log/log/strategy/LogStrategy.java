package com.eugen.log.log.strategy;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.eugen.Fra;
import com.eugen.log.log.LogEntity;
import com.eugen.log.log.operate.OperateLogEntity;
import com.eugen.log.log.operate.OperateLogService;
import com.eugen.resp.Response;


public abstract class LogStrategy extends Fra
{
    @Autowired
    private Environment enviornment;

    @Autowired
    private OperateLogService operateLogService;

    private LogEntity logEntity;

    /**
     * 子类实现，用以解析请求头中的数据
     * 
     * @param request
     * @return
     * @see
     */
    protected abstract String handleLog(HttpServletRequest request);

    public void recordLog(HttpServletRequest request, LogEntity logEntity, Response resp)
    {
        this.logEntity = logEntity;
        String result = this.handleLog(request);
        OperateLogEntity entity = new OperateLogEntity();
        entity.setOperateName(logEntity.getName());
        entity.setSource(logEntity.getSource());
        entity.setModule(logEntity.getModule());
        entity.setOperateDesc(result);
        entity.setStatus(resp.getStatus());
        entity.setOperateResult(resp.getDesc());
        operateLogService.add(entity);
    }

    protected String getProp(String key)
    {
        if (null == key)
        {
            return "";
        }
        String realKey = new StringBuilder(logEntity.getPackageName()).append(".").append(key).toString();
        return enviornment.getProperty(realKey, key);
    }
}
