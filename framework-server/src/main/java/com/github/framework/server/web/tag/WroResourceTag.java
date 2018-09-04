package com.github.framework.server.web.tag;

import com.github.framework.server.context.AppContext;
import com.github.framework.server.context.RequestContext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Properties;

/**
* @ClassName: WroResourceTag
* @Description:  wro4j压缩静态资源文件所提供的jsp页面所用的tag标签
* @author 龙海仁
* @date 2016年4月22日 下午3:03:26
*
*/
public class WroResourceTag extends SimpleTagSupport {

	private static final long serialVersionUID = 2597279237276049255L;
   //资源名称
	private String resource;
	
    public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	//模块名
	private String module;
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if(module==null){
			module = RequestContext.getCurrentContext().getModuleName();
		}
		if(this.resource.contains(".js")){
			getJspContext().getOut().write(getWroResourceUrl(module,true));			
		}else{
			getJspContext().getOut().write(getWroResourceUrl(module,false));
		}
	}
    /**
     * 
     * <p>通过resource和module查找压缩后js</p> 
     * @param moduleName
     * @param isJS
     * @return
     * @throws IOException
     * @see
     */
    private String getWroResourceUrl(String moduleName,boolean isJS) throws IOException {
    	StringBuilder wroResPropObject = new StringBuilder("");
		Properties properties = WroResourcePropCache.getInstance().getWroResourceInfo(moduleName);
		if (properties == null) {
			return wroResPropObject.toString();
		}
		String contextPath = AppContext.getAppContext().getContextPath();
		if(isJS){
			wroResPropObject.append("<script type='text/javascript' src='");
			wroResPropObject.append(contextPath + "/scripts/" + moduleName);
		}else{
			wroResPropObject.append("<link rel='stylesheet' type='text/css' href='");
			wroResPropObject.append(contextPath + "/styles/" + moduleName);
		}
		if(properties.containsKey(resource)){
			wroResPropObject.append("/wro/");	
		}else{
			wroResPropObject.append("/");
		}
		wroResPropObject.append(properties.getProperty(resource,resource));
		if(isJS){
			wroResPropObject.append("'></script>");
		}else{
			wroResPropObject.append("'/>");
		}
		return wroResPropObject.toString();
    }
}
