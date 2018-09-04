package com.github.framework.server.database.redis;

import com.github.framework.server.database.redis.exception.RedisConnectionException;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author longhairen
 * @create 2017-05-24 23:44
 * @description
 **/
public class RedisClusterHandler implements IRedisHandler{
   
    //初始化集合，用于装下面的多个主机和端口
    private  HashSet<HostAndPort> nodes;
    //创建config
    private JedisPoolConfig poolConfig;
    //通过config创建集群实例
    private JedisCluster jedisCluster;

    private int timeout = 2000;

    private String password;

    public RedisClusterHandler(){

    }

    public RedisClusterHandler(HashSet<HostAndPort> nodes, JedisPoolConfig poolConfig){
        this(nodes, poolConfig, 2000, null);
    }

    public RedisClusterHandler(HashSet<HostAndPort> nodes, JedisPoolConfig poolConfig, int timeout, String password){
        this.nodes = nodes;
        this.poolConfig = poolConfig;
        this.timeout = timeout;
        this.password = password;
    }

    public void init(){
        if(nodes == null || poolConfig == null){
            throw new RedisConnectionException("init failed, check the config");
        }
        jedisCluster = new JedisCluster(nodes, timeout, timeout,5, password, poolConfig);
    }

    public void setNodes(HashSet<HostAndPort> nodes) {
        this.nodes = nodes;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public Long del(String... keys) {
        return jedisCluster.del(keys);
    }

    @Override
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long persist(String key) {
        return jedisCluster.persist(key);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return jedisCluster.expireAt(key, unixTime);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public String type(String key) {
        return jedisCluster.type(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String mset(String... keyValues) {
        return jedisCluster.mset(keyValues);
    }

    @Override
    public List<String> mget(String... keys) {
        return jedisCluster.mget(keys);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return jedisCluster.setex(key, seconds, value);
    }

    @Override
    public Long append(String key, String value) {
        return jedisCluster.append(key, value);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return jedisCluster.hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... field) {
        return jedisCluster.hmget(key, field);
    }

    @Override
    public Long hlen(String key) {
        return jedisCluster.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }

    @Override
    public List<String> hvals(String key) {
        return jedisCluster.hvals(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public Long exists(String key, String field) {
        return jedisCluster.exists(key, field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key,field);
    }
}
