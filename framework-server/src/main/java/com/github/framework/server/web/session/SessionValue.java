package com.github.framework.server.web.session;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标注允许被放入session的类型
 * @author 龙海仁
 * @create：2016年3月19日 上午8:38:19
 * @description：
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface SessionValue {

}
