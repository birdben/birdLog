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
     * 这里的日志处理方法 handlerUserServiceLogWithMultipleParam 的参数和Log注解的method调用方法是一模一样的
     * @param user
     */
    public void handlerUserServiceLogWithMultipleParam(UserInfo user) {
        System.out.println("========================handlerUserServiceLogWithMultipleParam用户自定义格式开始============================");
        System.out.println("name:" + user.getName());
        System.out.println("age:" + user.getAge());
        System.out.println("job:" + user.getJob());
        System.out.println("website:" + user.getWebsite());
        System.out.println("========================handlerUserServiceLogWithMultipleParam用户自定义格式结束============================");
    }

    /**
     * 这里的日志处理方法 handlerUserServiceLogWithMapParam 的参数将Log注解的method调用方法的参数封装成一个Map
     * @param parameterObject
     */
    public void handlerUserServiceLogWithMapParam(Object parameterObject) {
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        if (parameterType == LogAopWithPointcut.ParamMap.class) {
            LogAopWithPointcut.ParamMap paramMap = (LogAopWithPointcut.ParamMap) parameterObject;
            UserInfo user = (UserInfo) paramMap.get("user");
            System.out.println("========================handlerUserServiceLogWithMapParam用户自定义格式开始============================");
            System.out.println("name:" + user.getName());
            System.out.println("age:" + user.getAge());
            System.out.println("job:" + user.getJob());
            System.out.println("website:" + user.getWebsite());
            System.out.println("========================handlerUserServiceLogWithMapParam用户自定义格式结束============================");
        }
    }
}
