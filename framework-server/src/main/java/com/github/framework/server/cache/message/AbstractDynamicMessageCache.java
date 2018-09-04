package com.github.framework.server.cache.message;



import com.github.framework.server.cache.DefaultStrongCache;

import java.util.Map;


/**
* @ClassName: AbstractDynamicMessageCache
* @Description: 动态国际化缓存
* @author 龙海仁
* @date 2016年4月22日 下午1:43:32
*
* @param <K>
* @param <V>
*/
public class AbstractDynamicMessageCache<K, V> extends DefaultStrongCache<String, Map<String, String>> {
    
    public static final String UUID = AbstractDynamicMessageCache.class.getName();
    /**
     * 
     * <p>返回类名称最为uuid</p> 
     * @return 
     */
	public String getUUID() {
		return AbstractDynamicMessageCache.UUID;
	}

}
