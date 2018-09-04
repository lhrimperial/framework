package com.github.framework.server.cache.provider;


/**
* @ClassName: ITTLCacheProvider
* @Description: TTL的缓存数据提供者
* @author longhairen
* @date 2017年4月22日 下午1:24:24
*
* @param <V>
*/
public interface ITTLCacheProvider<V> {

    /**
     * 加载单个元素
     * get
     * @param key
     * @return
     * @return V
     * @since:
     */
    V get(String key);
    
}
