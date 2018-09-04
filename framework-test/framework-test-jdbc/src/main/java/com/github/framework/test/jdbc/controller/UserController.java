package com.github.framework.test.jdbc.controller;

import com.github.framework.test.jdbc.domain.UserPO;
import com.github.framework.test.jdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 *
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/find/{id}")
    public UserPO findUser(@PathVariable Integer id) {
        UserPO userPO =  userService.findByUserName(id);
        return userPO;
    }

    @RequestMapping("/save")
    public String saveUser() {
        Random r = new Random();
        UserPO userPO = new UserPO(r.nextInt(10), "hello"+r.nextInt(100));
        userService.save(userPO);
        return "success";
    }

    @RequestMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id) {
        Random r = new Random();
        UserPO userPO = new UserPO(id, "hello"+r.nextInt(100));
        userService.update(userPO);
        return "success";
    }

    @RequestMapping("/list")
    public List<UserPO> findAll() {
        return userService.findAllUser();
    }
}
