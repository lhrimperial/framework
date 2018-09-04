package com.github.framework.server.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author： longhairen
 * @Date：2016年7月11日 下午3:52:04
 * @Description：自定义Redis缓存集成注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RedisCacheable {
    public enum KeyMode{
        DEFAULT,    //默认 加上参数
        ANNO,       //只有加了@RedisCacheKey的参数,才加入key后缀中
        BASIC,      //只有基本类型参数,才加入key后缀中,如:String,Integer,Long,Short,Boolean
        BEAN,       //bean的属性加入KEY后缀
        MAP         //Map的属性加入KEY后缀
    }

    public enum Option{
        ADD,    //保存
        UP,     //更新
        SEL,    //查询
        DEL     //删除
    }

    public String key() default "";     //缓存key
    public KeyMode keyMode() default KeyMode.DEFAULT;       //key的后缀模式
    public Option option() default  Option.SEL;             //操作类型
    public int expire() default 7200;      //缓存多少秒,默认2个小时

}