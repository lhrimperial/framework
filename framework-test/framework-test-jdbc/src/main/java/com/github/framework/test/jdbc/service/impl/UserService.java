package com.github.framework.test.jdbc.service.impl;

import com.github.framework.test.jdbc.dao.UserDao;
import com.github.framework.test.jdbc.domain.UserPO;
import com.github.framework.test.jdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserService implements IUserService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserPO findByUserName(Integer id){
        return userDao.findByUserName(id);
    }

    @Override
    public List<UserPO> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
//    @DataSource("slave")
    public void save(UserPO userPO) {
        userDao.save(userPO);
    }

    @Override
    public void update(UserPO userPO) {
        userDao.update(userPO);
    }
}
