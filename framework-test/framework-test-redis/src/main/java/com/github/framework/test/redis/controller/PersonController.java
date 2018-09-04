package com.github.framework.test.redis.controller;

import com.github.framework.test.redis.domain.PersonPO;
import com.github.framework.test.redis.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;

    @RequestMapping("/find/{id}")
    public PersonPO findById(@PathVariable Integer id) {
        PersonPO personPO = personService.findById(id);
        logger.info("person:{}",personPO);
        return personPO;
    }
}
