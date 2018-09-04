package com.github.framework.server.cache.store;

import com.github.framework.server.cache.CacheUtils;
import com.github.framework.server.cache.exception.KeyIsNotFoundException;
import com.github.framework.server.cache.exception.RedisCacheStorageException;
import com.github.framework.server.cache.exception.ValueIsBlankException;
import com.github.framework.server.cache.exception.ValueIsNullException;
import com.github.framework.server.database.redis.RedisClient;
import com.github.framework.util.string.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * @author longhairen
 * @create 2017/6/7 0007 上午 9:47
 */
public class RedisCacheStore<K, V> implements IRemoteCacheStore<K, V>,InitializingBean,DisposableBean {

    private int expire = 3600 * 24;

    private RedisClient redisClient;

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    /**
     * 存入有时效的数据
     * @param key
     * @param value
     * @return
     */
    @Override
    public String set(K key, V value) {
        return set(key, value, expire);
    }

    /**
     * 存入有时效的数据
     * @param key
     * @param value
     * @param exp
     * @return
     */
    @Override
    public String set(K key, V value, int exp) {
        String skey = CacheUtils.toJsonString(key);
        String svalue = CacheUtils.toJsonString(value);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        return redisClient.set(skey, svalue, exp);
    }

    /**
     * 获取key对应的数据
     * @param key 缓存Key
     * @return
     */
    @Override
    public V get(K key) {
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        String re = redisClient.get(skey);
        if(re != null) {
            if(StringUtils.isBlank(re)) {
                //key存在，value为空串
                throw new ValueIsBlankException("key exists, value is blank!");
            } else if(StringUtils.equalsIgnoreCase(re, "nil")) {
                //key不存在
                throw new KeyIsNotFoundException("key is not found!");
            } else if(StringUtils.equalsIgnoreCase(re, "null")) {
                //key存在，value为null
                throw new ValueIsNullException("key exists, value is null!");
            } else {
                return (V) CacheUtils.jsonParseObject(re);
            }
        } else {
            //key不存在
            throw new KeyIsNotFoundException("key is not found!");
        }
    }

    /**
     * 删除指定的缓存信息
     * @param key 缓存Key
     */
    @Override
    public void remove(K key) {
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        redisClient.del(skey);
    }

    /**
     * 删除多个key的缓存信息
     * @param keys 动态参数 数组[]
     */
    @Override
    public void removeMulti(K[] keys) {
        if(keys == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        String[] skey = new String[keys.length];
        for(int i = 0,len = keys.length; i < len; i++){
            skey[i] = CacheUtils.toJsonString(keys[i]);
        }
        redisClient.del(skey);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (redisClient == null) {
            redisClient = new RedisClient();
            redisClient.afterPropertiesSet();
        }
    }

    @Override
    public void destroy() throws Exception {

    }

    /**
     * 初始化Redis Cache任务
     * @since
     * @version
     */
    class RedisCacheTask extends Thread {
        int count = 1;
        String cacheId;
        Map<K,V> map;
        public RedisCacheTask(String name, String cacheId, Map<K,V> map) {
            super(name);
            count = 1;
            this.cacheId = cacheId;
            this.map = map;
        }

        @SuppressWarnings("static-access")
        @Override
        public void run() {

        }

    }
}
