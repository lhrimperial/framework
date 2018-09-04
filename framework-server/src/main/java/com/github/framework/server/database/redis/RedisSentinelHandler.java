package com.github.framework.server.database.redis;

import com.github.framework.server.database.redis.exception.RedisConnectionException;
import com.github.framework.util.string.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author longhairen
 * @create 2017/5/27 0027 下午 3:19
 */
public class RedisSentinelHandler implements IRedisHandler{
    //redis 哨兵集群IP
    private HashSet<String> sentinels;
    //监控主机名
    private String masterName;
    //redis 配置
    private JedisPoolConfig poolConfig;
    //哨兵资源池
    private JedisSentinelPool sentinelPool;

    private int timeout = 2000;

    private String password;

    public RedisSentinelHandler(){}

    public RedisSentinelHandler(HashSet<String> sentinels, String masterName, JedisPoolConfig poolConfig){
        this(sentinels, masterName, poolConfig, 2000, null);
    }

    public RedisSentinelHandler(HashSet<String> sentinels, String masterName, JedisPoolConfig poolConfig, int timeout, String password){
        this.sentinels = sentinels;
        this.masterName = masterName;
        this.poolConfig = poolConfig;
        this.timeout = timeout;
        this.password = password;
    }

    public void init(){
        // 通过Jedis连接池创建一个Sentinel连接池
        if(sentinels == null || poolConfig == null || StringUtils.isEmpty(masterName)) {
            throw new RedisConnectionException("init failed, check the config");
        }
        sentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout, password);
    }

    public Jedis getResource(){
        if(sentinels == null){
            init();
        }
        return sentinelPool.getResource();
    }

    public void setSentinels(HashSet<String> sentinels) {
        this.sentinels = sentinels;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Long del(String key) {
        return getResource().del(key);
    }

    @Override
    public Long del(String... keys) {
        return getResource().del(keys);
    }

    @Override
    public Boolean exists(String key) {
        return getResource().exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return getResource().expire(key, seconds);
    }

    @Override
    public Long persist(String key) {
        return getResource().persist(key);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return getResource().expireAt(key, unixTime);
    }

    @Override
    public Long ttl(String key) {
        return getResource().ttl(key);
    }

    @Override
    public String type(String key) {
        return getResource().type(key);
    }

    @Override
    public String set(String key, String value) {
        return getResource().set(key, value);
    }

    @Override
    public String get(String key) {
        return getResource().get(key);
    }

    @Override
    public String mset(String... keyValues) {
        return getResource().mset(keyValues);
    }

    @Override
    public List<String> mget(String... keys) {
        return getResource().mget(keys);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return getResource().setex(key, seconds, value);
    }

    @Override
    public Long append(String key, String value) {
        return getResource().append(key, value);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return getResource().hset(key, field, value);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return getResource().hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... field) {
        return getResource().hmget(key, field);
    }

    @Override
    public Long hlen(String key) {
        return getResource().hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return getResource().hkeys(key);
    }

    @Override
    public List<String> hvals(String key) {
        return getResource().hvals(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return getResource().hgetAll(key);
    }

    @Override
    public String hget(String key, String field) {
        return getResource().hget(key, field);
    }

    @Override
    public Long exists(String key, String field) {
        return getResource().exists(key, field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return getResource().hdel(key,field);
    }
}
