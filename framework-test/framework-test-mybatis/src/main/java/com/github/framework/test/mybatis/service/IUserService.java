package com.github.framework.test.mybatis.service;


import com.github.framework.test.mybatis.domain.UserPO;

import java.util.List;

/**
 *
 */
public interface IUserService {
    UserPO findByUserName(Integer id);

    List<UserPO> findAllUser();

    List<UserPO> findByPage(int pageNo);

    void save(UserPO userPO);

    void update(UserPO userPO);
}
