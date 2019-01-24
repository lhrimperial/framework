package com.github.framework.test.mybatis.service.impl;

import com.github.framework.test.mybatis.domain.UserPO;
import com.github.framework.test.mybatis.mapper.UserMapper;
import com.github.framework.test.mybatis.service.IUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void save(UserPO userPO) {
        userMapper.save(userPO);
        String str = null;
        str.length();
    }

    @Override
    //    @DataSource("slave")
    public UserPO findByUserName(Integer id){
        return userMapper.findByUserName(id);
    }

    @Override
    public List<UserPO> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public List<UserPO> findByPage(int pageNo) {
        PageHelper.startPage(pageNo, 10);
        return userMapper.findByPage(pageNo);
    }

    @Override
    public void update(UserPO userPO) {
        userMapper.update(userPO);
    }

}
