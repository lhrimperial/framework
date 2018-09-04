package com.github.framework.server.database.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class RedisTemplateHandler implements IRedisHandler {

    private static final String success = "OK";
    private static final String failed = "FAIL";

    private StringRedisTemplate redisTemplate;

    public RedisTemplateHandler(){}

    public RedisTemplateHandler(StringRedisTemplate template) {
        this.redisTemplate = template;
    }

    @Override
    public void init() {

    }

    @Override
    public Long del(String key) {
        redisTemplate.delete(key);
        return 1L;
    }

    @Override
    public Long del(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
        return Long.valueOf(keys.length);
    }

    @Override
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return redisTemplate.expire(key,seconds, TimeUnit.SECONDS)?1L:0L;
    }

    @Override
    public Long persist(String key) {
        return redisTemplate.persist(key)?1L:0L;
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return redisTemplate.expireAt(key,new Date(unixTime))?1L:0L;
    }

    @Override
    public Long ttl(String key) {
        //TODO
        return null;
    }

    @Override
    public String type(String key) {
        return redisTemplate.type(key).name();
    }

    @Override
    public String set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return success;
    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public String mset(String... keyValues) {
        return null;
    }

    @Override
    public List<String> mget(String... keys) {
        return null;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        return value;
    }

    @Override
    public Long append(String key, String value) {
        return null;
    }

    @Override
    public Long hset(String key, String field, String value) {
        return null;
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return null;
    }

    @Override
    public List<String> hmget(String key, String... field) {
        return null;
    }

    @Override
    public Long hlen(String key) {
        return null;
    }

    @Override
    public Set<String> hkeys(String key) {
        return null;
    }

    @Override
    public List<String> hvals(String key) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return null;
    }

    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public Long exists(String key, String field) {
        return null;
    }

    @Override
    public Long hdel(String key, String... field) {
        return null;
    }
}
