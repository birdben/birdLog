package com.birdben.log.annotation;
 
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author birdben
 * @version V1.0
 * @name: LogHandler
 * @description 日志处理方法注解
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface LogHandlerMethod {

    /** 日志处理方法名称 */
    String name();
}