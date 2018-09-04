package com.github.framework.server.web.tag;



import com.github.framework.server.context.AppContext;
import com.github.framework.server.context.RequestContext;
import com.github.framework.server.web.message.MessageBundle;
import com.github.framework.server.web.security.SecurityAccessor;
import com.github.framework.util.string.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;


/**
* @ClassName: ModuleForJsTag
* @Description: 产生指定模块内javascript会用到的变量、国际化函数、权限函数
* @author 龙海仁
* @date 2016年4月22日 下午3:02:06
*
*/
public class ModuleForJsTag extends SimpleTagSupport {
	
	/**
	 * subModule
	 */
	private String subModule;
	
	private String groups;

	public String getSubModule() {
		return subModule;
	}

	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	
	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}
	/**
	 * 
	 * <p>tag配置</p> 
	 * @throws JspException
	 * @throws IOException 
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		String moduleName = RequestContext.getCurrentContext().getModuleName();
		String[] groupArray = parseStringToArray(groups);
		Map<String, String> tags = null;
		if(groupArray.length==0){
			tags = TagsCache.getInstance().getTagesInfo(moduleName);
		}else{
			tags = TagsCache.getInstance().getTagesInfo(moduleName, groupArray);
		}
		getJspContext().getOut().write(createModuleScript(moduleName));
		getJspContext().getOut().write(createI18nScript(moduleName, tags.get("keys")));
		getJspContext().getOut().write(createPermissionScript(moduleName, tags.get("urls")));
		if(groupArray.length!=0){
			for(String group : groupArray){
				getJspContext().getOut().write(createWroResourceUrl(moduleName, group));
			}
		}
	}
	/**
	 * 
	 * <p>根据模块名称和资源名称查找文件</p> 
	 * @param moduleName
	 * @param resource
	 * @return
	 * @throws IOException
	 * @see
	 */
	private String createWroResourceUrl(String moduleName, String resource) throws IOException {
    	StringBuilder wroResPropObject = new StringBuilder("");
		Properties properties = WroResourcePropCache.getInstance().getWroResourceInfo(moduleName);
		if (properties == null) {
			return wroResPropObject.toString();
		}
		String contextPath = AppContext.getAppContext().getContextPath();
		String resJs = properties.getProperty(resource+".js");
		String resCss = properties.getProperty(resource+".css");
		if(resCss!=null && !resCss.endsWith("-0.css")){
			wroResPropObject.append("<link rel='stylesheet' type='text/css' href='");
			wroResPropObject.append(contextPath + "/styles/" + moduleName);
			wroResPropObject.append("/wro/");
			wroResPropObject.append(resCss);
			wroResPropObject.append("'/> \n");
		}
		if(resJs!=null && !resJs.endsWith("-0.js")){
			wroResPropObject.append("<script type='text/javascript' src='");
			wroResPropObject.append(contextPath + "/scripts/" + moduleName);
			wroResPropObject.append("/wro/");
			wroResPropObject.append(resJs);
			wroResPropObject.append("'></script> \n");
		}
		return wroResPropObject.toString();
    }
	/**
	 * 
	 * <p>生成模块javascript代码格式</p> 
	 * @param moduleName
	 * @return
	 * @see
	 */
	private String createModuleScript(String moduleName){
		StringBuilder msgObject = new StringBuilder("");
		msgObject.append("<script type='text/javascript'> \n");
		msgObject.append("if(typeof ").append(moduleName).append(" == 'undefined'){\n")
				 .append(moduleName).append("={};\n")
				 .append("}");
		msgObject.append("\n").append(moduleName).append(".realPath = function (path) { \n");
		msgObject.append("return '").append(AppContext.getAppContext().getContextPath()).append("/");
		msgObject.append(moduleName).append("/' + ").append("path;\n");
		msgObject.append("};\n");
		if(subModule!=null){
			msgObject.append("\n").append(moduleName).append(".").append(subModule).append("={}");
		}
		msgObject.append("\n</script>\n");
		
		return msgObject.toString();
	}
	/**
	 * 
	 * <p>生成国际化javascript代码格式</p> 
	 * @param moduleName
	 * @param keys
	 * @return
	 * @see
	 */
	private String createI18nScript(String moduleName, String keys){
		if(StringUtils.isBlank(keys)) {
			return keys;
		}
		MessageBundle messageBundle = new MessageBundle();
		StringBuilder msgObject = new StringBuilder("");
		msgObject.append("<script type='text/javascript'> \n");
		// 声明一个function，用于取message
		msgObject.append(moduleName);
		if(subModule!=null){
			msgObject.append(".");
			msgObject.append(subModule);
		}
		msgObject.append(".i18n = function(key, args) { \n");
		// 声明一个对象，存放message信息
		msgObject.append("msg = {");
	    String[] keyArray = parseStringToArray(keys);
	    for (String key : keyArray) {
	        String message = messageBundle.getMessage(key);
	        if (message != null && !"".equals(message)) {
	            msgObject.append("'" + key + "'" + ":'" + message + "',");
	        }
	    }
	    //如果keys为空,下面这句截取字符串会把方法参数中的逗号去掉
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
		return msgObject.toString();
	}
	/**
	 * 
	 * <p>生成权限控制的javascript代码格式</p> 
	 * @param moduleName
	 * @param urls
	 * @return
	 * @see
	 */
	private String createPermissionScript(String moduleName, String urls){
		if(StringUtils.isBlank(urls)) {
			return urls;
		}
		String[] urlArray = parseStringToArray(urls);
		StringBuilder  msgObject=new StringBuilder("");
		msgObject.append("<script type='text/javascript'> \n");
		msgObject.append(moduleName);
		if(subModule!=null){
			msgObject.append(".");
			msgObject.append(subModule);
		}
		msgObject.append(".isPermission = function (url){ \n");
		msgObject.append("permissions = {");
		for(String url :urlArray)
		{
			boolean message = SecurityAccessor.hasAccessSecurity(url);
			msgObject.append("'"+url+"'" +":"+message+",");
		}
		msgObject.deleteCharAt(msgObject.lastIndexOf(","));
		msgObject.append("}; \n");
		msgObject.append("return permissions[url]; \n");
		msgObject.append("}; \n");
		msgObject.append("</script>");
		return msgObject.toString();
	}
	/**
	 * 
	 * <p>将字符串转成字符数组</p> 
	 * @param str
	 * @return
	 * @see
	 */
	private String[] parseStringToArray(String str){
		if(str==null){
			return new String[0];
		}
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r", "");
		String[] keyArray = str.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			keyArray[i] = keyArray[i].trim();
		}
		return keyArray;		
	}
}
