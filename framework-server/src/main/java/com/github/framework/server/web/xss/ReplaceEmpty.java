package com.github.framework.server.web.xss;

/**
 * 非法字符替换为空串的策略逻辑类
 * @since
 * @version
 */
class ReplaceEmpty implements Tactics {

	@Override
	public String process(String target, String regex) throws ParametersValidatorException {
		return target.replaceAll(regex, "");
	}
	
}
