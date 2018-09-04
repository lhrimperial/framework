package com.github.framework.test.mybatis.mapper;

import com.github.framework.test.mybatis.domain.UserPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longhairen
 * @create 2018-02-23 17:47
 * @description
 **/
@Repository
public interface UserMapper {

    @Select("select * from t_user where id = #{id}")
    @ResultMap("com.github.framework.test.mybatis.mapper.UserMapper.BaseResultMap")
    UserPO findByUserName(Integer id);

    @Select("select * from t_user")
    @ResultMap("com.github.framework.test.mybatis.mapper.UserMapper.BaseResultMap")
    List<UserPO> findAllUser();

    @Select("select * from t_user")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="user_name", property="userName"),
    })
    List<UserPO> findByPage(int pageNo);

    @Insert("insert into t_user(id,user_name) values(#{id},#{userName})")
    void save(UserPO userPO);

    void update(UserPO userPO);
}
