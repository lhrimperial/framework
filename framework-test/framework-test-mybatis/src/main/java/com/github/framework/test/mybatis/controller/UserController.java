package com.github.framework.test.mybatis.controller;

import com.github.framework.test.mybatis.domain.UserPO;
import com.github.framework.test.mybatis.service.IUserService;
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
        try {
            Random r = new Random();
            UserPO userPO = new UserPO(r.nextInt(10), "hello"+r.nextInt(100));
            userService.save(userPO);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @RequestMapping("/find/page/{num}")
    public List<UserPO> findByPage(@PathVariable int num) {
        return userService.findByPage(num);
    }
}
