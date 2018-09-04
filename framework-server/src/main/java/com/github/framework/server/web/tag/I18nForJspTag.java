package com.github.framework.server.web.tag;



import com.github.framework.server.web.message.MessageBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
* @ClassName: I18nForJspTag
* @Description: 提供前台jsp页面使用的国际化标签
* @author 龙海仁
* @date 2016年4月22日 下午3:01:40
*
*/
public class I18nForJspTag extends SimpleTagSupport {
	//国际化信息key
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public void doTag() throws JspException, IOException {
		MessageBundle messageBundle = new MessageBundle();
		getJspContext().getOut().write(messageBundle.getMessage(key));
	}
}
