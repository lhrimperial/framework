package com.github.framework.server.cache.store;


/**
* @ClassName: IRemoteCacheStore
* @Description: 远程缓存接口
* @author longhairen
* @date 2017年4月22日 下午1:33:29
*
* @param <K>
* @param <V>
*/
public interface IRemoteCacheStore<K, V> {
    
    /**
     * 主动向Cache更新指定的数据
     * 
     * @param key
     * @param value
     * @return String 是否执行成功
     */
    
    String set(K key, V value);
    
    /**
     * 主动向Cache更新指定的数据,指定过期时间
     * @param key
     * @param value
     * @param exp
     * @return String 是否执行成功
     */
    String set(K key, V value, int exp);
    
    /**
     * 获取缓存
     * 
     * @param key 缓存Key
     * @return 缓存Value
     */
    V get(K key);
    
    /**
     * 删除指定的缓存信息
     * @param key 缓存Key
     */
    void remove(K key);
    
    /**
     * 删除多个key的缓存信息
     * @param keys 动态参数 数组[]
     * @see
     */
    void removeMulti(K... keys);
    
}
