package com.birdben.log.service;

import com.birdben.log.bean.UserInfo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author birdben
 * @version V1.0
 * @name TestUserService
 * @description 测试用户业务接口
 * @github https://github.com/birdben
 * @date 16/7/24 下午2:29
 */
public class TestUserService {

    private static IUserService userService;

    @BeforeClass
    public static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-test-context.xml");
        // 这里使用的默认UserServiceImpl类名来寻找的Bean
        userService = (IUserService) context.getBean("userServiceImpl");
    }

    /******************************************************** 测试正常情况开始 **********************************************************/
    @Test
    public void testSaveHandlerLogInThisClassWithMapParam() throws Throwable {
        UserInfo user = new UserInfo();
        user.setName("birdben");
        user.setAge(20);
        user.setJob("programmer");
        user.setWebsite("https://github.com/birdben");
        userService.saveHandlerLogInThisClassWithMapParam(user);
    }

    @Test
    public void testSaveHandlerLogWithMapParam() throws Throwable {
        UserInfo user = new UserInfo();
        user.setName("birdben");
        user.setAge(20);
        user.setJob("programmer");
        user.setWebsite("https://github.com/birdben");
        userService.saveHandlerLogWithMapParam(user);
    }
    /******************************************************** 测试正常情况结束 **********************************************************/

    /******************************************************** 测试异常情况开始 **********************************************************/
    @Test
    public void testSaveHandlerLogInThisClassMethodNotFound() throws Throwable {
        UserInfo user = new UserInfo();
        user.setName("birdben");
        user.setAge(20);
        user.setJob("programmer");
        user.setWebsite("https://github.com/birdben");
        userService.saveHandlerLogInThisClassMethodNotFound(user);
    }

    @Test
    public void testSaveHandlerLogInThisClassMethodParamNotMatch() throws Throwable {
        UserInfo user = new UserInfo();
        user.setName("birdben");
        user.setAge(20);
        user.setJob("programmer");
        user.setWebsite("https://github.com/birdben");
        userService.saveHandlerLogInThisClassMethodParamNotMatch(user);
    }

    @Test
    public void testSaveHandlerLogMethodNotFound() throws Throwable {
        UserInfo user = new UserInfo();
        user.setName("birdben");
        user.setAge(20);
        user.setJob("programmer");
        user.setWebsite("https://github.com/birdben");
        userService.saveHandlerLogMethodNotFound(user);
    }

    @Test
    public void testSaveHandlerLogMethodParamNotMatch() throws Throwable {
        UserInfo user = new UserInfo();
        user.setName("birdben");
        user.setAge(20);
        user.setJob("programmer");
        user.setWebsite("https://github.com/birdben");
        userService.saveHandlerLogMethodParamNotMatch(user);
    }
    /******************************************************** 测试异常情况结束 **********************************************************/

}
