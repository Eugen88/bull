package com.eugen.log.log;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eugen.Fra;
import com.eugen.log.log.strategy.LogStrategyContext;
import com.eugen.resp.Response;


/**
 * 定义日志切面
 * 
 * @author Eugen
 * @version 2018年5月14日
 * @see LogAspect
 * @since
 */
@Aspect
@Component
public class LogAspect extends Fra
{
    /**
     * log
     */
    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogStrategyContext logStrategyContext;

    /**
     * 定义控制器层切面标识
     * 
     * @see
     */
    @Pointcut("@annotation(com.eugen.log.log.Log)")
    public void controllerLogAspect()
    {
        LOG.info("Validator aspect initial successfully...");
    }

    /**
     * 定义后置通知，用于获取get、post提交的数据，已经获取操作结果
     * 
     * @param joinPoint
     * @param response
     * @see
     */
    @AfterReturning(value = "controllerLogAspect()", argNames = "response", returning = "response")
    public void afterReturn(JoinPoint joinPoint, Response response)
    {
        // 获取注解中配置参数
        LogEntity logEntity = this.getLogEntity(joinPoint);

        LOG.info(String.format("log annotation info: %s", logEntity.toString()));

        HttpServletRequest request = this.getRequest();
        logStrategyContext.recordLog(request, logEntity, response);
    }

    /**
     * 获取log注解配置参数
     * 
     * @param joinPoint
     * @return
     * @see
     */
    @SuppressWarnings("rawtypes")
    private LogEntity getLogEntity(JoinPoint joinPoint)
    {
        LogEntity entity = new LogEntity();
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Package pkg = targetClass.getPackage();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Method[] methods = targetClass.getMethods();
        for (Method method : methods)
        {
            if (method.getName().equals(methodName))
            {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length)
                {
                    entity.setSource(method.getAnnotation(Log.class).source());
                    entity.setModule(method.getAnnotation(Log.class).module());
                    entity.setName(method.getAnnotation(Log.class).name());
                    break;
                }
            }
        }
        entity.setPackageName(pkg.getName());
        return entity;
    }
}
