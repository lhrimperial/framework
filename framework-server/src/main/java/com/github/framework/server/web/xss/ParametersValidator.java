package com.github.framework.server.web.xss;


import com.github.framework.server.context.XssConfigContext;
import com.github.framework.util.string.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数验证，放置xss注入
 * 
 *
 */
public class ParametersValidator {
	
	/**
	 * 配置类context
	 */
	private XssConfigContext xssConfigContext;
	
	/**
	 * 非法字符的正则表达式
	 */
	Set<Pattern> excludeParams = Collections.emptySet();
	
	/**
	 * 根据参数配置创建验证类
	 * 
	 * @param xssConfigContext
	 */
	public ParametersValidator(XssConfigContext xssConfigContext) {
		this.xssConfigContext = xssConfigContext;
		setExcludeParams(xssConfigContext.getExpression());
	}

	/**
	 * 验证方法
	 * 验证request中的参数
	 * @param request
	 * @param response
	 * @see
	 */
	public void doValidator(HttpServletRequest request, HttpServletResponse response) throws ParametersValidatorException {
		if (request.getParameterMap() == null || request.getParameterMap().isEmpty()) {
			return;
		}
		
		processValidator(null, request, response);
	}
	
	/**
	 * 验证方法
	 * 验证传入的字符
	 * @param json
	 * @param response
	 * @return
	 * @see
	 */
	public String doValidator(String json, HttpServletResponse response) throws ParametersValidatorException {
		if (StringUtils.isBlank(json)) {
			return json;
		}
		
		return processValidator(json, null, response);
	}
	
	/**
	 * 处理验证的逻辑
	 * @param json
	 * @param request
	 * @param response
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	private String processValidator(String json, HttpServletRequest request, HttpServletResponse response) throws ParametersValidatorException {
		Tactics tactics = null;
		boolean escape = false;
		if(XssConfigContext.REPLACE_EMPTY.equalsIgnoreCase(xssConfigContext.getTactics())) {
			tactics = new ReplaceEmpty();
		} else if(XssConfigContext.REPLACE_ESCAPE.equalsIgnoreCase(xssConfigContext.getTactics())) {
			tactics = new ReplaceEscape();
			escape = true;
		} else if(XssConfigContext.REDIRECT.equalsIgnoreCase(xssConfigContext.getTactics())) {
			tactics = new Redirect(response,xssConfigContext.getPath());
		} else {
			tactics = new ReplaceEmpty();
		}
		
		if(request != null) {
			Map<String, String[]> params = request.getParameterMap();
			
			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				String[] values = entry.getValue();
				if (values == null) {
					continue;
				}
				
				if(escape) {
					for(int i = 0; i < values.length; i++) {
						values[i] = tactics.process(values[i],null);
					}
				} else {
					for(int i = 0; i < values.length; i++) {
						values[i] = isExcluded(values[i],tactics);
					}
				}
			}
			
			return null;
		}
		if(json != null) {
			if (escape) {
				return tactics.process(json, null);
			}
			
			return isExcluded(json, tactics);
		}
		
		return null;
	}
	
	/**
	 * 验证参数是否匹配表达式，如果匹配走相应的策略
	 * 
	 * @param paramValue
	 * @param tactics
	 * @return
	 * @see
	 */
	private String isExcluded(String paramValue, Tactics tactics) {
		if (StringUtils.isBlank(paramValue)) {
			return paramValue;
		}
		
        if (!this.excludeParams.isEmpty()) {
            for (Pattern pattern : excludeParams) {
                Matcher matcher = pattern.matcher(paramValue);
                if (matcher.find()) {
                	paramValue = tactics.process(paramValue, pattern.pattern());
                }
            }
        }
        
        return paramValue;
    }
	
	/**
	 * 设置验证的表达式
	 * @param commaDelim
	 * @see
	 */
	private void setExcludeParams(String commaDelim) {
        Collection<String> excludePatterns = asCollection(commaDelim);
        if (excludePatterns != null) {
            excludeParams = new HashSet<Pattern>();
            for (String pattern : excludePatterns) {
                excludeParams.add(Pattern.compile(pattern));
            }
        }
    }

    private Collection<String> asCollection(String commaDelim) {
        if (commaDelim == null || commaDelim.trim().length() == 0) {
            return null;
        }
        return commaDelimitedStringToSet(commaDelim);
    }
    
    private static Set<String> commaDelimitedStringToSet(String s) {
        Set<String> set = new HashSet<String>();
        String[] split = s.split(",");
        for (String aSplit : split) {
            String trimmed = aSplit.trim();
            if (trimmed.length() > 0)
                set.add(trimmed);
        }
        return set;
    }
}
