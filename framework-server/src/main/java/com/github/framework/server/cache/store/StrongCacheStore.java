package com.github.framework.server.cache.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
* @ClassName: StrongCacheStorage
* @Description: 只读缓存存储
* @author longhairen
* @date 2017年4月22日 下午1:29:37
*
* @param <K>
* @param <V>
*/
public class StrongCacheStore<K, V> implements ICacheStore<K, V> {
    /**
     *
     */
    private volatile Map<K, V> map;

    /**
     * 日志
     */
    Log log = LogFactory.getLog(getClass());

    public StrongCacheStore() {
        this.map = new ConcurrentHashMap<K, V>();
    }

    /**
     * set
     * @param key
     * @param value
     * @since:0.6
     */
    @Override
    public void set(K key, V value) {
        map.put(key, value);
    }

    /**
     * get
     * @param key
     * @return
     * @since:0.6
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        return map.get(key);
    }

    /**
     * remove
     * @param key
     * @since:0.6
     */
    @Override
    public void remove(K key) {
        map.remove(key);
    }

    /**
     * clear
     * @since:0.6
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * set
     * @param values
     * @since:0.6
     */
    @Override
    public void set(Map<K, V> values) {
        map = values;
    }
    
    /**
     * get
     * @return
     * @since:0.6
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<K, V> get() {
        return map;
    }

    @Override
    public boolean exists(K key) {
        return map.containsKey(key);
    }
}
