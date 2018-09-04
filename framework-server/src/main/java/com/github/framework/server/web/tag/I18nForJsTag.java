package com.github.framework.server.web.tag;



import com.github.framework.server.context.RequestContext;
import com.github.framework.server.web.message.MessageBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;


/**
* @ClassName: I18nForJsTag
* @Description: 获得指定键的国际化信息，并返回封装好的javascript对象
* @author 龙海仁
* @date 2016年4月22日 下午3:01:50
*
*/
public class I18nForJsTag extends SimpleTagSupport {

	private String keys;
	public void setKeys(String keys) {
		this.keys = keys;
	}

	/**
	 * SimpleTagSupport标签执行，调用的主方法
	 * 调用国际化资源接口，遍历传入的国际化键的字符串，查找出每一个键对应的国际化信息，将键和国际化信息封装成一个javascript对象
	 * ，放入到script 标签中
	 * 
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag() doTag
	 * @throws JspException
	 * @throws IOException
	 * @since JDK1.6
	 */
	@Override
	public void doTag() throws JspException, IOException {

		if(this.keys== null||"".equals(this.keys))
		{
			return;
		}
		String moduleName = RequestContext.getCurrentContext().getModuleName();

		MessageBundle messageBundle = new MessageBundle();
		StringBuilder msgObject = new StringBuilder("");
		msgObject.append("<script type='text/javascript'> \n");
		// 声明一个function，用于取message
		msgObject.append(moduleName);
		msgObject.append(".i18n = function(key, args) { \n");
		// 声明一个对象，存放message信息
		msgObject.append("msg = {");

		String[] keyArray = parseKeys(keys);

		for (String key : keyArray) {
			String message = messageBundle.getMessage(key);
			if (message != null && !"".equals(message)) {
				msgObject.append("'" + key + "'" + ":'" + message + "',");
			}
		}
		msgObject.deleteCharAt(msgObject.lastIndexOf(","));
		msgObject.append("};");

		msgObject.append("\n");
		msgObject.append("var message = msg[ key] ; \n");
		msgObject.append("if(args != null){  \n");
		msgObject.append("for ( var i = 0; i < args.length; i++) { \n");
		msgObject.append("var reg ='{'+i+'}'; \n");
		msgObject
				.append("message = message.toString().replace(reg, args[i]); \n");
		msgObject.append("} \n");
		msgObject.append("} \n");
		msgObject.append("return message; \n");
		msgObject.append("} \n");
		msgObject.append("</script>");
		getJspContext().getOut().write(msgObject.toString());
	}

	/**
	 * 解析字符串，获得key组成的数组 parseKeys
	 * 
	 * @param keys
	 * @return
	 * @return String[]
	 * @since JDK1.6
	 */
	private String[] parseKeys(String keys) {
		String k = keys;
		k = k.replaceAll("\n", "");
		k = k.replaceAll("\r", "");
		String[] keyArray = k.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			keyArray[i] = keyArray[i].trim();
		}
		return keyArray;
	}
}
