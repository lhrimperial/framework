package com.github.framework.server.web.xss;


/**
* @ClassName: Tactics
* @Description: 验证处理策略
* @author 龙海仁
* @date 2016年4月22日 下午3:05:03
*
*/
interface Tactics {
	/**
	 * 处理的逻辑方法
	 * @param target 目标对象,正则匹配的字符串
	 * @param regex  正则表达式
	 * @return
	 * @see
	 */
	String process(String target, String regex) throws ParametersValidatorException;
}