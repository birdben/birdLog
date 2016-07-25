package com.birdben.log.controller;

import com.birdben.log.annotation.Log;
import com.birdben.log.annotation.LogParam;
import com.birdben.log.bean.UserInfo;
import com.birdben.log.handler.UserControllerLogHandler;
import com.birdben.log.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author birdben
 * @version V1.0
 * @name UserController
 * @description 用户Controller
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
@Controller
@RequestMapping("/aop")
public class UserController {

    @Resource
    IUserService userService;

    /**
     * 使用Log注解指定method:日志处理方法名,method是当前类的方法名
     * 这里的日志处理方法 handlerUserControllerLogInThisClassWithMapParam 的参数和 aopHandlerLogInThisClassWithMapParam 方法的参数是一模一样的
     * @param name
     * @param age
     * @param job
     * @param website
     * @return
     * @throws InterruptedException
     */
    @Log(message = "访问aopHandlerLogInThisClassWithMapParam方法", method = "handlerUserControllerLogInThisClassWithMapParam")
    @ResponseBody
    @RequestMapping(value = "/aopHandlerLogInThisClassWithMapParam")
    public String aopHandlerLogInThisClassWithMapParam(@LogParam("name") String name, @LogParam("age") int age, @LogParam("job") String job, @LogParam("website") String website) throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println("访问aopHandlerLogInThisClassWithMapParam方法");
        UserInfo user = new UserInfo();
        user.setName(name);
        user.setAge(age);
        user.setJob(job);
        user.setWebsite(website);
        userService.saveHandlerLogInThisClassWithMapParam(user);
        return "";
    }

    /**
     * 使用Log注解指定handler:日志处理类,method:日志处理方法名
     * 这里的日志处理方法 handlerUserControllerLogWithMapParam 的参数和 aopHandlerLogWithMapParam 方法的参数是不一样的,handlerUserControllerLogWithMapParam将多个参数转化成一个Map参数
     * @param name
     * @param age
     * @param job
     * @param website
     * @return
     * @throws InterruptedException
     */
    @Log(message = "访问aopHandlerLogWithMapParam方法", handler = UserControllerLogHandler.class, method = "handlerUserControllerLogWithMapParam")
    @ResponseBody
    @RequestMapping(value = "/aopHandlerLogWithMapParam")
    public String aopHandlerLogWithMapParam(@LogParam("name") String name, @LogParam("age") int age, @LogParam("job") String job, @LogParam("website") String website) throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println("访问aopHandlerLogWithMapParam方法");
        UserInfo user = new UserInfo();
        user.setName(name);
        user.setAge(age);
        user.setJob(job);
        user.setWebsite(website);
        userService.saveHandlerLogWithMapParam(user);
        return "";
    }

    /******************************************************** UserController自己当成LogHandler的处理方法开始 **********************************************************/
    /**
     * 自定义日志处理方法,会通过Log注解的method指定该方法的名称
     * @param map
     */
    public void handlerUserControllerLogInThisClassWithMapParam(Map map) {
        // 这里的Map的key是根据@LogParam的注解对应的
        String name = (String) map.get("name");
        Integer age = (Integer) map.get("age");
        String job = (String) map.get("job");
        String website = (String) map.get("website");
        System.out.println("handlerUserControllerLogInThisClassWithMapParam name:" + name);
        System.out.println("handlerUserControllerLogInThisClassWithMapParam age:" + age);
        System.out.println("handlerUserControllerLogInThisClassWithMapParam job:" + job);
        System.out.println("handlerUserControllerLogInThisClassWithMapParam website:" + website);
    }
    /******************************************************** UserController自己当成LogHandler的处理方法开始 **********************************************************/

}