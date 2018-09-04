package com.github.framework.server.shared.define;


/**
* @ClassName: Protocol
* @Description: TODO
* @author 龙海仁
* @date 2016年4月21日 下午5:34:02
*
*/

public interface Protocol {
	/**
	 * 方法名定义
	 */
    public static final String SECURITY_HEADER = "method-name";
    
    /**
     * json头定义
     */
    public static final String JSON_CONTENT_TYPE = "application/json";
    
    /**
     * hessian头定义
     */
    public static final String HESSIAN_CONTENT_TYPE = "x-application/hessian";


}
