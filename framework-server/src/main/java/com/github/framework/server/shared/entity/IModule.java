package com.github.framework.server.shared.entity;

import java.util.List;


/**
 * @ClassName: BaseEntity
 * @Description: 模块抽象
 * @author 龙海仁
 * @date 2016年3月19日下午5:34:22
 *
 */
public interface IModule extends IEntity {

	/**
	 * 模块功能集合
	 * @return
	 */
	List<IFunction>	 getFunctions();

}
