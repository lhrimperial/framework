package com.github.framework.test.jdbc.dao;

import com.github.framework.test.jdbc.domain.UserPO;
import java.util.List;

/**
 *
 */
public interface UserDao {

    UserPO findByUserName(Integer id);

    List<UserPO> findAllUser();

    void save(UserPO userPO);

    void update(UserPO userPO);
}
