package com.github.framework.test.redis.dao;

import com.github.framework.test.redis.domain.PersonPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longhairen
 * @create 2018-02-25 19:00
 * @description
 **/
public interface PersonRepository extends JpaRepository<PersonPO,Integer> {
}
