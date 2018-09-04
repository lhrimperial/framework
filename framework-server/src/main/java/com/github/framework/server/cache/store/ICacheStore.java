package com.github.framework.server.cache.store;

import java.util.Map;


/**
* @ClassName: ICacheStorage
* @Description: 抽象的数据缓存仓库
* @author longhairen
* @date 2017年4月22日 下午1:33:16
*
* @param <K>
* @param <V>
*/
public interface ICacheStore<K, V> {
    
    /**
     * 存放数据
     * 
     * @param key
     * @param value
     */
    void set(K key, V value);
    
    /**
     * 
     * @param values
     */
    void set(Map<K, V> values);
    
    /**
     * 获取数据
     * 
     * @param key
     */
    V get(K key);
    
    /**
     * 移除指定的数据
     * 
     * @param key
     */
    void remove(K key);
    
    /**
     * 移除所有的数据
     */
    void clear();
    
    /**
     * 
     * @return
     */
    Map<K, V> get();
    
    /**
     * 是否存在
     * @param key
     * @return
     * @see
     */
    boolean exists(K key);
}
