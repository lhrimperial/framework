package com.github.framework.test.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.framework.test.dubbo.api.domain.UserDTO;
import com.github.framework.test.dubbo.api.service.IUserservice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longhairen
 * @create 2018-02-23 21:50
 * @description
 **/
@RestController
public class UserController {

    @Reference
    private IUserservice userservice;

    @RequestMapping("/find/user")
    UserDTO findUser() {
        return userservice.findUser("");
    }
}
