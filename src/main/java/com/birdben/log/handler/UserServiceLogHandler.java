package com.birdben.log.handler;

import com.birdben.log.bean.UserInfo;
import com.birdben.log.springaop.LogAopWithPointcut;

/**
 * @author birdben
 * @version V1.0
 * @name UserServiceLogHandler
 * @description 用户Service日志处理类
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
public class UserServiceLogHandler {

    /**
     * 这里的日志处理方法 handlerUserServiceLogWithMapParam 的参数将Log注解的method调用方法的参数封装成一个Map
     * @param parameterObject
     */
    public void handlerUserServiceLogWithMapParam(Object parameterObject) {
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        if (parameterType == LogAopWithPointcut.ParamMap.class) {
            LogAopWithPointcut.ParamMap paramMap = (LogAopWithPointcut.ParamMap) parameterObject;
            // 这里的Map的key是根据@LogParam的注解对应的
            UserInfo user = (UserInfo) paramMap.get("user");
            System.out.println("handlerUserServiceLogWithMapParam name:" + user.getName());
            System.out.println("handlerUserServiceLogWithMapParam age:" + user.getAge());
            System.out.println("handlerUserServiceLogWithMapParam job:" + user.getJob());
            System.out.println("handlerUserServiceLogWithMapParam website:" + user.getWebsite());
        }
    }

    /**
     * 这里的日志处理方法 handlerUserServiceLogMethodParamNotMatch 的参数与Log注解的method调用方法的参数不一致
     * @param name
     */
    public void handlerUserServiceLogMethodParamNotMatch(String name) {
        System.out.println("handlerUserServiceLogMethodParamNotMatch name:" + name);
    }
}
