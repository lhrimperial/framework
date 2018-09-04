package com.github.framework.server.cache.message;

import com.github.framework.server.cache.DefaultStrongCache;
import com.github.framework.server.cache.provider.IBatchCacheProvider;
import org.springframework.stereotype.Component;

import java.util.Properties;


/**
* @ClassName: MessageCache
* @Description: 国际化缓存
* @author longhairen
* @date 2016年4月22日 下午1:43:47
*
*/
@Component
public class MessageCache extends DefaultStrongCache<String, Properties> {
    
    public static final String UUID = MessageCache.class.getName();
        
    @SuppressWarnings("unchecked")
    @javax.annotation.Resource(name = "messageCacheProvider")
    public void setCacheProvider(@SuppressWarnings("rawtypes") IBatchCacheProvider cacheProvider) {
        super.setCacheProvider(cacheProvider);
    }
   /**
    * 
    * <p>返回uuid 为/+类名称</p> 
    * @return 
    */
	@Override
	public String getUUID() {
		return UUID;
	}

}
