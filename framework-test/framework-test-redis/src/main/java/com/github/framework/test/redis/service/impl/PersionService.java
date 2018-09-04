package com.github.framework.test.redis.service.impl;

import com.github.framework.test.redis.dao.PersonRepository;
import com.github.framework.test.redis.domain.PersonPO;
import com.github.framework.test.redis.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author longhairen
 * @create 2018-02-25 13:17
 * @description
 **/
@Service
public class PersionService implements IPersonService {
    private Logger logger = LoggerFactory.getLogger(PersionService.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    @CachePut(cacheNames = "person", key = "\"\"+#personPO.id")
    public PersonPO save(PersonPO personPO) {
        personRepository.save(personPO);
        System.out.println(personPO);
        return personPO;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
//    @Cacheable(value = "person", key = "\"\"+#id")
    public PersonPO findById(Integer id) {
        logger.info(String.valueOf(id));
        return personRepository.findOne(id);
    }
}
