package com.github.framework.test.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.framework.test.dubbo.api.domain.UserDTO;
import com.github.framework.test.dubbo.api.service.IUserservice;

/**
 * @author longhairen
 * @create 2018-02-23 21:37
 * @description
 **/
@Service
public class UserServiceImpl implements IUserservice {

    @Override
    public UserDTO findUser(String userName) {

        return new UserDTO("andy", "123");
    }
}
