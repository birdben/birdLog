package com.birdben.log.handler;

import com.birdben.log.springaop.LogAopWithPointcut;

/**
 * @author birdben
 * @version V1.0
 * @name UserControllerLogHandler
 * @description 用户Controller日志处理类
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
public class UserControllerLogHandler {

    /**
     * 这里的日志处理方法 handlerUserControllerLogWithMapParam 的参数将Log注解的method调用方法的参数封装成一个Map
     * @param parameterObject
     */
    public void handlerUserControllerLogWithMapParam(Object parameterObject) {
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        if (parameterType == LogAopWithPointcut.ParamMap.class) {
            LogAopWithPointcut.ParamMap paramMap = (LogAopWithPointcut.ParamMap) parameterObject;
            // 这里的Map的key是根据@LogParam的注解对应的
            String name = (String) paramMap.get("name");
            Integer age = (Integer) paramMap.get("age");
            String job = (String) paramMap.get("job");
            String website = (String) paramMap.get("website");

            System.out.println("handlerUserControllerLogWithMapParam name:" + name);
            System.out.println("handlerUserControllerLogWithMapParam age:" + age);
            System.out.println("handlerUserControllerLogWithMapParam job:" + job);
            System.out.println("handlerUserControllerLogWithMapParam website:" + website);
        }
    }
}
