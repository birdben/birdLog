package com.birdben.log.service;

import com.birdben.log.annotation.Log;
import com.birdben.log.annotation.LogHandlerMethod;
import com.birdben.log.annotation.LogParam;
import com.birdben.log.bean.UserInfo;
import com.birdben.log.handler.UserServiceLogHandler;
import com.birdben.log.springaop.LogAopWithPointcut;
import org.springframework.stereotype.Service;

/**
 * @author birdben
 * @version V1.0
 * @name: UserServiceImpl
 * @description 用户业务实现类
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
@Service
public class UserServiceImpl implements IUserService {

    @Log(message = "访问saveHandlerLogInThisClassWithMultipleParam用户信息", method = "handlerUserServiceLogInThisClassWithMultipleParam")
    public void saveHandlerLogInThisClassWithMultipleParam(@LogParam("user") UserInfo user) {
        System.out.println("访问saveHandlerLogInThisClassWithMultipleParam用户信息 name:" + user.getName());
        System.out.println("访问saveHandlerLogInThisClassWithMultipleParam用户信息 age:" + user.getAge());
        System.out.println("访问saveHandlerLogInThisClassWithMultipleParam用户信息 job:" + user.getJob());
        System.out.println("访问saveHandlerLogInThisClassWithMultipleParam用户信息 website:" + user.getWebsite());
    }

    @Log(message = "访问saveHandlerLogInThisClassWithMapParam用户信息", method = "handlerUserServiceLogInThisClassWithMapParam")
    public void saveHandlerLogInThisClassWithMapParam(@LogParam("user") UserInfo user) {
        System.out.println("访问saveHandlerLogInThisClassWithMapParam用户信息 name:" + user.getName());
        System.out.println("访问saveHandlerLogInThisClassWithMapParam用户信息 age:" + user.getAge());
        System.out.println("访问saveHandlerLogInThisClassWithMapParam用户信息 job:" + user.getJob());
        System.out.println("访问saveHandlerLogInThisClassWithMapParam用户信息 website:" + user.getWebsite());
    }

    @Log(message = "访问saveHandlerLogWithMultipleParam用户信息", handler = UserServiceLogHandler.class, method = "handlerUserServiceLogWithMultipleParam")
    public void saveHandlerLogWithMultipleParam(@LogParam("user") UserInfo user) {
        System.out.println("访问saveHandlerLogWithMultipleParam用户信息 name:" + user.getName());
        System.out.println("访问saveHandlerLogWithMultipleParam用户信息 age:" + user.getAge());
        System.out.println("访问saveHandlerLogWithMultipleParam用户信息 job:" + user.getJob());
        System.out.println("访问saveHandlerLogWithMultipleParam用户信息 website:" + user.getWebsite());
    }

    @Log(message = "访问saveHandlerLogWithMapParam用户信息", handler = UserServiceLogHandler.class, method = "handlerUserServiceLogWithMapParam")
    public void saveHandlerLogWithMapParam(@LogParam("user") UserInfo user) {
        System.out.println("访问saveHandlerLogWithMapParam用户信息 name:" + user.getName());
        System.out.println("访问saveHandlerLogWithMapParam用户信息 age:" + user.getAge());
        System.out.println("访问saveHandlerLogWithMapParam用户信息 job:" + user.getJob());
        System.out.println("访问saveHandlerLogWithMapParam用户信息 website:" + user.getWebsite());
    }

    //@LogHandlerMethod(name="handlerUserServiceLogInThisClassWithMultipleParam")
    public void handlerUserServiceLogInThisClassWithMultipleParam(UserInfo user) {
        System.out.println("========================handlerUserServiceLogInThisClassWithMultipleParam用户自定义格式开始============================");
        System.out.println("name:" + user.getName());
        System.out.println("age:" + user.getAge());
        System.out.println("job:" + user.getJob());
        System.out.println("website:" + user.getWebsite());
        System.out.println("========================handlerUserServiceLogInThisClassWithMultipleParam用户自定义格式结束============================");
    }

    //@LogHandlerMethod(name="handlerUserServiceLogInThisClassWithMapParam")
    public void handlerUserServiceLogInThisClassWithMapParam(Object parameterObject) {
        System.out.println("========================handlerUserServiceLogInThisClassWithMapParam用户自定义格式开始============================");
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        if (parameterType == LogAopWithPointcut.ParamMap.class) {
            LogAopWithPointcut.ParamMap paramMap = (LogAopWithPointcut.ParamMap) parameterObject;
            UserInfo user = (UserInfo) paramMap.get("user");

            System.out.println("name:" + user.getName());
            System.out.println("age:" + user.getAge());
            System.out.println("job:" + user.getJob());
            System.out.println("website:" + user.getWebsite());
        }
        System.out.println("========================handlerUserServiceLogInThisClassWithMapParam用户自定义格式结束============================");
    }

}