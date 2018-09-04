package com.github.framework.server.database.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author longhairen
 * @create 2017/5/27 0027 下午 3:15
 * Redis key 操作
 */
public interface IRedisHandler {

    void init();
    /**
     * 删除指定key
     *
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * 删除多个key
     *
     * @param keys
     * @return
     */
    Long del(String... keys);

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    Boolean exists(String key);

    /**
     * 指定key的生存时间
     *
     * @param key
     * @param seconds
     * @return
     */
    Long expire(String key, int seconds);

    /**
     * 移除给定 key 的生存时间
     *
     * @param key
     * @return
     */
    Long persist(String key);

    /**
     * 指定key的生存时间戳
     *
     * @param key
     * @param unixTime
     * @return
     */
    Long expireAt(String key, long unixTime);

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)
     *
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 返回 key 所储存的值的类型
     * none (key不存在)
     * string (字符串)
     * list (列表)
     * set (集合)
     * zset (有序集)
     * hash (哈希表)
     *
     * @param key
     * @return
     */
    String type(String key);

    /**
     * 设置key-value
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * 获取key的值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置多度key-value
     * @param keyValues
     * @return
     */
    String mset(String... keyValues);

    /**
     * 获取多个key的值
     * @param keys
     * @return
     */
    List<String> mget(String... keys);
    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    String setex(String key, int seconds, String value);

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾
     * @param key
     * @param value
     * @return
     */
    Long append(String key, String value);

    /**
     * 将哈希表 key 中的域 field 的值设为 value
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hset(String key, String field, String value);
    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中
     * @param key
     * @param hash
     * @return
     */
    String hmset(String key, Map<String, String> hash);

    /**
     * 返回哈希表 key 中，一个或多个给定域的值
     * @param key
     * @param field
     * @return
     */
    List<String> hmget(String key, String... field);
    /**
     * 返回哈希表 key 中域的数量
     * @param key
     * @return
     */
    Long hlen(String key);

    /**
     * 返回哈希表 key 中的所有域
     * @param key
     * @return
     */
    Set<String> hkeys(String key);

    /**
     * 返回哈希表 key 中所有域的值
     * @param key
     * @return
     */
    List<String> hvals(String key);
    /**
     * 返回哈希表 key 中，所有的域和值
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);
    /**
     * 返回哈希表 key 中给定域 field 的值
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);
    /**
     * 查看哈希表 key 中，给定域 field 是否存在
     * @param key
     * @param field
     * @return
     */
    Long exists(String key, String field);
    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略
     * @param key
     * @param field
     * @return
     */
    Long hdel(String key, String... field);
}
