package com.github.framework.server.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


/**
* @ClassName: WebApplicationContextHolder
* @Description: WebApplicationContext持有类
* @author 龙海仁
* @date 2016年4月22日 下午2:49:01
*
*/
@Component
public class WebApplicationContextHolder implements ApplicationContextAware {
	
	private static volatile ApplicationContext context;
	
	private static final Object lock = new Object();
	
	/**
	 * 注入WebApplicationContext
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * setApplicationContext
	 * @param applicationContext
	 * @throws BeansException
	 * @since:0.9
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
	    synchronized (lock) {
	        context = applicationContext;
        }
	}
	
	public static WebApplicationContext getWebApplicationContext() {
		return (WebApplicationContext)context;
	}

}
