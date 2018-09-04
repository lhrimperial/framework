package com.github.framework.server.cache.provider;

import java.util.Date;


/**
* @ClassName: ICacheProvider
* @Description: Cache数据提供接口
* @author longhairen
* @date 2017年4月22日 下午1:33:02
*
* @param <K>
* @param <V>
*/
public interface ICacheProvider<K, V> {
    /**
     * 获取最后修改时间
     * @return
     * @see
     */
    Date getLastModifyTime();
}
