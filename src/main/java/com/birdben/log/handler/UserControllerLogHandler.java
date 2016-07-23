package com.birdben.log.handler;

import com.birdben.log.springaop.LogAopWithPointcut;

/**
 * @author birdben
 * @version V1.0
 * @name: UserControllerLogHandler
 * @description 用户Controller日志处理类
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
public class UserControllerLogHandler {

    /**
     * 这里的日志处理方法 handlerUserControllerLogWithMultipleParam 的参数和Log注解的method调用方法是一模一样的
     * @param name
     * @param age
     * @param job
     * @param website
     */
    public void handlerUserControllerLogWithMultipleParam(String name, int age, String job, String website) {
        System.out.println("========================handlerUserControllerLogWithMultipleParam用户自定义格式开始============================");
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        System.out.println("job:" + job);
        System.out.println("website:" + website);
        System.out.println("========================handlerUserControllerLogWithMultipleParam用户自定义格式结束============================");
    }

    /**
     * 这里的日志处理方法 handlerUserControllerLogWithMapParam 的参数将Log注解的method调用方法的参数封装成一个Map
     * @param parameterObject
     */
    public void handlerUserControllerLogWithMapParam(Object parameterObject) {
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        if (parameterType == LogAopWithPointcut.ParamMap.class) {
            LogAopWithPointcut.ParamMap paramMap = (LogAopWithPointcut.ParamMap) parameterObject;
            String name = (String) paramMap.get("name");
            Integer age = (Integer) paramMap.get("age");
            String job = (String) paramMap.get("job");
            String website = (String) paramMap.get("website");

            System.out.println("========================handlerUserControllerLogWithMapParam用户自定义格式开始============================");
            System.out.println("name:" + name);
            System.out.println("age:" + age);
            System.out.println("job:" + job);
            System.out.println("website:" + website);
            System.out.println("========================handlerUserControllerLogWithMapParam用户自定义格式结束============================");
        }
    }
}
