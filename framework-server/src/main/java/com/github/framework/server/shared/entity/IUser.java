package com.github.framework.server.shared.entity;

import java.util.Set;

/**
 * @ClassName: BaseEntity
 * @Description: 用户实体抽象
 * @author 龙海仁
 * @date 2016年3月19日下午5:34:22
 *
 */
public interface IUser extends IEntity{
	/**
	 * 用户名设置
	 * @param userName
	 */
	void setUserName(String userName);

	/**
	 * 获取用户名
	 * @return
	 */
	String getUserName();

	/**
	 * 设置用户的权限
	 * @param paramSet
	 */
	void setRoleids(Set<String> paramSet);

	/**
	 * 获取用户的所有角色id
	 */
	Set<String> getRoleids();

	/**
	 * 获取用户允许访问的URI
	 * @return
	 */
	Set<String> queryAccessUris();


}
