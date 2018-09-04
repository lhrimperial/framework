package com.github.framework.server.cache;


/**
* @ClassName: RefreshableCache
* @Description: 允许刷新缓存接口
* @author longhairen
* @date 2017年4月22日 下午1:33:45
*
* @param <K>
* @param <V>
*/
public interface IRefreshableCache<K, V> extends ICache<K, V>{
    /**
     * 刷新缓存
     * 
     * 根据provider提供的最后修改时间去刷新这段时间之内修改的数据
     * 如果是LRU的根据最后修改时间刷新时间段的数据
     * 如果是Strong根据最后修改时间刷新所有数据
     * @return
     * @see
     */
    boolean refresh();
    /**
     * 刷新Key对应的缓存
     * 
     * 根据provider提供的最后修改时间去刷新这段时间之内修改的数据
     * 如果是LRU的根据传入的Key修改缓存数据
     * 如果是Strong的会Throws RuntimeException异常，不允许刷新部分数据
     * @param keys
     * @return
     * @see
     */
    boolean refresh(K... keys);
}
