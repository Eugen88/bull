package com.eugen.log.log;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;


/**
 * 定义操作日志注解部分
 * 
 * @author Eugen
 * @version 2018年5月14日
 * @see Log
 * @since
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 操作项名称
     * 
     * @return
     */
    String name() default "unknown";

    /**
     * 操作来源，可能是pc端、app端或其他渠道
     * 
     * @return
     * @see
     */
    int source() default 0;

    /**
     * 操作模块
     * 
     * @return
     */
    int module() default 0;
}