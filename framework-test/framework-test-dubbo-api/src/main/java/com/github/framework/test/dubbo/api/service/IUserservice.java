package com.github.framework.test.dubbo.api.service;

import com.github.framework.test.dubbo.api.domain.UserDTO;

/**
 * @author longhairen
 * @create 2018-02-23 21:32
 * @description
 **/
public interface IUserservice {

    UserDTO findUser(String userName);
}
