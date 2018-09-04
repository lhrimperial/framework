package com.github.framework.server.shared.entity;


/**
 * @ClassName: BaseEntity
 * @Description: 功能抽象
 * @author 龙海仁
 * @date 2016年3月19日下午5:34:22
 *
 */
public interface IFunction extends IEntity{
	/**
	 * 
	 * @Title:getModule
	 * @Description: 在用户功能下有一个功能模块
	 * 在后来的完善中，过滤掉了此设计方案
	 * @param @return
	 * @return IModule
	 * @throws
	 */
	@Deprecated
	IModule getModule();
	
	/**
	 * 
	 * @Title:getUri
	 * @Description:用户功能菜单的href
	 * @param @return
	 * @return String
	 * @throws
	 */
	String getUri();
	/**
	 * 
	 * @Title:getKey
	 * @Description:功能菜单的id
	 * @param @return
	 * @return String
	 * @throws
	 */
	String getKey();
	/**
	 * 
	 * @Title:getFunctionCode
	 * @Description:功能菜单的的代码号：code
	 * @param @return
	 * @return String
	 * @throws
	 */
	String getFunctionCode();
	
	/**
	 * 功能是否被启用
	 * getValidFlag
	 * @return
	 * @return Boolean
	 * @since:0.6
	 */
	Boolean getValidFlag();

	/**
	 * 功能名称
	 * @return
	 */
	String getName();
}
