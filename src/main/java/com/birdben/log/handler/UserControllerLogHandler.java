package com.birdben.log.handler;

import java.util.Map;

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
     * @param map
     */
    public void handlerUserControllerLogWithMapParam(Map map) {
        // 这里的Map的key是根据@LogParam的注解对应的
        String name = (String) map.get("name");
        Integer age = (Integer) map.get("age");
        String job = (String) map.get("job");
        String website = (String) map.get("website");

        System.out.println("handlerUserControllerLogWithMapParam name:" + name);
        System.out.println("handlerUserControllerLogWithMapParam age:" + age);
        System.out.println("handlerUserControllerLogWithMapParam job:" + job);
        System.out.println("handlerUserControllerLogWithMapParam website:" + website);
    }
}
