package com.github.framework.server.cache.provider;

import java.util.Map;


/**
* @ClassName: IBatchCacheProvider
* @Description: 批量加载缓存接口
* @author longhairen
* @date 2017年4月22日 下午1:23:47
*
* @param <K>
* @param <V>
*/
public interface IBatchCacheProvider<K, V> extends ICacheProvider<K, V> {
    
	/**
	 * 批量加载数据
	 * get
	 * @return
	 * @return Map<K,V>
	 * @since: 0.6
	 */
    Map<K, V> get();
}
