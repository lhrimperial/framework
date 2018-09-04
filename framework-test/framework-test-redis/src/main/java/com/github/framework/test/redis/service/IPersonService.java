package com.github.framework.test.redis.service;

import com.github.framework.test.redis.domain.PersonPO;

/**
 * @author longhairen
 * @create 2018-02-25 13:15
 * @description
 **/
public interface IPersonService {

    PersonPO save(PersonPO personPO);

    void delete(Integer id);

    PersonPO findById(Integer id);
}
