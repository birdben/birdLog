package com.birdben.log.annotation;
 
import com.birdben.log.handler.DefaultLogHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author birdben
 * @version V1.0
 * @name: Log
 * @description 日志注解
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Log {

    /** 自定义日志消息 */
    String message() default "";

    /** 自定义日志处理Class */
    Class<?> handler() default DefaultLogHandler.class;

    /** 自定义日志处理方法名称 */
    String method();
}