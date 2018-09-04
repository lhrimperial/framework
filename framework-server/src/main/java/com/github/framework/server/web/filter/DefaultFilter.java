package com.github.framework.server.web.filter;

import javax.servlet.*;
import java.io.IOException;


/**
* @ClassName: DefaultFilter
* @Description: filte默认实现
* @author 龙海仁
* @date 2016年4月22日 下午3:05:48
*
*/
public class DefaultFilter implements Filter {
    
    @Override
    public void destroy() {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {
    	filterChain.doFilter(request, response);
    }
    
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        
    }
    
}
