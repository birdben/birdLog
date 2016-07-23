package com.birdben.log.service;

import com.birdben.log.bean.UserInfo;

/**
 * @author birdben
 * @version V1.0
 * @name: IUserService
 * @description 用户业务接口
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
public interface IUserService {

    void saveHandlerLogInThisClassWithMultipleParam(UserInfo user);

    void saveHandlerLogInThisClassWithMapParam(UserInfo user);

    void saveHandlerLogWithMultipleParam(UserInfo user);

    void saveHandlerLogWithMapParam(UserInfo user);
}