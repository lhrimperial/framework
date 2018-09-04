package com.github.framework.server.web.listener;


import com.github.framework.server.context.AppContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 应用上下文监听器
 * @author 龙海仁
 * @create：2016年3月24日 上午10:52:05
 * @description：
 */
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	/**
	 * 初始化应用上下文
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 * contextInitialized
	 * @param sce
	 * @since:0.7
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		AppContext.initAppContext(sc.getServletContextName(),
		        sc.getInitParameter("staticServerAddress"),
		        sc.getContextPath());

	}

}
