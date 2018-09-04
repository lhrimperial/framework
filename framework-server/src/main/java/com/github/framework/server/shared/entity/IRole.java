package com.github.framework.server.shared.entity;

import java.util.Set;

/**
 * @ClassName: BaseEntity
 * @Description: 角色抽象
 * @author 龙海仁
 * @date 2016年3月19日下午5:34:22
 *
 */
public interface IRole  extends IEntity{
	/**
	 * 功能ID集合
	 * @return
	 */
	Set<String> getFunctionIds();

}