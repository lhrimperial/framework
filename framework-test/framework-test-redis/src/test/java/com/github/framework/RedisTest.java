package com.github.framework;

import com.github.framework.test.redis.RedisApplication;
import com.github.framework.test.redis.domain.PersonPO;
import com.github.framework.test.redis.service.IPersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testRedis() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        // 向redis设置数据
        operations.set("key1", "value1");
        // 从redis获取数据
        String value = operations.get("key1");
        System.out.println(value);
        // 删除缓存
        redisTemplate.delete("key3");
        // 设置有效期100秒
        redisTemplate.expire("key4", 100, TimeUnit.SECONDS);
    }

    @Autowired
    private IPersonService personService;

    @Test
    public void testFind() {
        PersonPO personPO = personService.findById(1);
        System.out.println(personPO);
    }

    @Test
    public void testSave() {
        PersonPO personPO = new PersonPO();
        personPO.setName("personName");
        personService.save(personPO);
    }
}
