package com.github.framework.server.cache.message;

import com.github.framework.server.cache.provider.IBatchCacheProvider;
import com.github.framework.util.string.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
* @ClassName: MessageCacheProvider
* @Description: 国际化缓存数据提供者
* @author longhairen
* @date 2016年4月22日 下午1:44:04
*
*/
@Component("messageCacheProvider")
public class MessageCacheProvider implements IBatchCacheProvider<String, Properties> {
    
    private Log logger = LogFactory.getLog(MessageCacheProvider.class);
    private Date startTime = new Date();
    
    /**
     * 
     * get
     * @throws 
     * @since:0.6
     */
    @Override
    public Map<String, Properties> get() {
        Map<String, Properties> map = new HashMap<String, Properties>();
        final String prefix =  "com/caiwei/";
        try {
        	//加载指定位置的properties文件
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath*:**/messages/message*.properties");
            for (Resource resource : resources) {
                String classpath = resource.getURL().getPath();
//                String classpath = path.substring(path.lastIndexOf(prefix));
                if (logger.isInfoEnabled()) {
                    logger.info("[Framework] add message bundle: " + classpath);
                }
                //取得文件名
//                final String moduleName = classpath.replaceAll("/server/META-INF/.*$", "").replaceAll("^.*/", "");
                final String localeName = classpath.replaceAll(".*\\/message([_a-zA-Z]*)\\.properties$", "$1");
                Properties properties = new Properties();
                InputStream in = resource.getInputStream();
                try {
                    properties.load(in);
                } finally {
                    in.close();
                }
//                map.put(moduleName + localeName, properties);
                
                //一个语言类型，不同模块下的语言文件合并到一个properties中
                String locale = localeName;
                if(StringUtils.isBlank(locale)) {
                    //文件没有语言标识后缀的默认为英文
                    locale = Locale.US.toString();
                }
                if(locale.startsWith("_")) {
                    locale = locale.substring(1);
                }
                Properties p = map.get(locale);
                if(p != null) {
                    p.putAll(properties);
                    map.put(locale, p);
                } else {
                    map.put(locale, properties);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return map;
    }

    /**
     * 
     * getLastModifyTime
     * @return
     * @since:
     */
	@Override
	public Date getLastModifyTime() {
		return startTime;
	}
    
}
