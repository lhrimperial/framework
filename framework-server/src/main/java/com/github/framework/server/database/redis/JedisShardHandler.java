package com.github.framework.server.database.redis;

import com.github.framework.server.database.redis.exception.RedisConnectionException;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author longhairen
 * @create 2017/6/7 0007 上午 10:16
 */
public class JedisShardHandler implements IRedisHandler {

    private JedisPoolConfig jedisPoolConfig;
    private List<JedisShardInfo> jedisShardInfos;
    private ShardedJedisPool pool;
    private ShardedJedis jedis;

    public JedisShardHandler(JedisPoolConfig jedisPoolConfig, List<JedisShardInfo> jedisShardInfos) {
        this.jedisPoolConfig = jedisPoolConfig;
        this.jedisShardInfos = jedisShardInfos;
    }

    public void init(){
        if (jedisPoolConfig == null || jedisShardInfos == null || jedisShardInfos.size() == 0) {
            throw new RedisConnectionException("init failed, check the config");
        }
        pool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfos);
        jedis = pool.getResource();
    }

    @Override
    public Long del(String key) {
        return jedis.del(key);
    }

    @Override
    public Long del(String... keys) {
        for (String key : keys) {
            jedis.del(key);
        }
        return Long.valueOf(keys.length);
    }

    @Override
    public Boolean exists(String key) {
        return jedis.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedis.expire(key, seconds);
    }

    @Override
    public Long persist(String key) {
        return jedis.persist(key);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return jedis.expireAt(key, unixTime);
    }

    @Override
    public Long ttl(String key) {
        return jedis.ttl(key);
    }

    @Override
    public String type(String key) {
        return jedis.type(key);
    }

    @Override
    public String set(String key, String value) {
        return jedis.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public String mset(String... keyValues) {
        List<String> list = new ArrayList<>();
        for (String keyValue : keyValues) {
            list.add(keyValue);
        }
        for (int i = 0, len = list.size(); i < len; i = i+2) {
            jedis.set(list.get(i), list.get(i+1));
        }
        return "OK";
    }

    @Override
    public List<String> mget(String... keys) {
        List<String> list = new ArrayList<>();
        for (String key : keys) {
            list.add(jedis.get(key));
        }
        return list;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return jedis.setex(key, seconds, value);
    }

    @Override
    public Long append(String key, String value) {
        return jedis.append(key, value);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedis.hset(key, field, value);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return jedis.hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... field) {
        return jedis.hmget(key, field);
    }

    @Override
    public Long hlen(String key) {
        return jedis.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return jedis.hkeys(key);
    }

    @Override
    public List<String> hvals(String key) {
        return jedis.hvals(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedis.hgetAll(key);
    }

    @Override
    public String hget(String key, String field) {
        return jedis.hget(key, field);
    }

    @Override
    public Long exists(String key, String field) {
        return jedis.hexists(key, field) ? 1L : 0L;
    }

    @Override
    public Long hdel(String key, String... field) {
        return jedis.hdel(key, field);
    }
}
