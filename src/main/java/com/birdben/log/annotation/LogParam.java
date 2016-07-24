package com.birdben.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author birdben
 * @version V1.0
 * @name LogParam
 * @description 日志参数注解
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface LogParam {

  /** 日志参数名称 */
  String value();
}
