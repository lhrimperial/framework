package com.github.framework.server.web.session;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * @author 龙海仁
 * @date 2016年3月19日上午8:37:52 
 * @param <V>
 */
public class Session<V> implements ISession<V> {
    
    private HttpSession session;

    /**
     *
     * setObject
     * @param k
     * @param v
     * @since:0.6
     */
    @Override
    public void setObject(String k, V v) {
    	//先验证设置的值是否被允许
        validateSessionValue(v);
        session.setAttribute(k, v);
    }

    /**
     *
     * getObject
     * @param k
     * @return
     * @since:0.6
     */
    @Override
    public V getObject(String k) {
        Object value = null;
        value = session.getAttribute(k);
        return (V) value;
    }

    /**
     *
     * init
     * @param session
     * @since:0.6
     */
    @Override
    public void init(HttpSession session) {
        this.session = session;
    }

    /**
     * 验证设置属性是否合法
     * validateSessionValue
     * @param v
     * @return void
     * @since:0.6
     */
    void validateSessionValue(V v) {
        if (null == v) {
        	return;
        }
        if (String.class == v.getClass()) {
        	return;
        }
        if (Long.class == v.getClass()) {
        	return;
        }
        if (Integer.class == v.getClass()) {
        	return;
        }
        if (java.util.Date.class == v.getClass()) {
        	return;
        }
        if(java.util.Locale.class == v.getClass()){ 
        	return;
        }
        //SessionValue注解标注的放过
        if (v.getClass().isAnnotationPresent(SessionValue.class)) {
        	return;
        }
        //对于Collection及Map的遍历里面所有元素进行判断
        if (Collection.class.isAssignableFrom(v.getClass())) {
            Collection<?> collect = (Collection<?>) v;
            for (Object t : collect) {
                validateSessionValue((V) t);
            }
            return;
        }
        if (Map.class.isAssignableFrom(v.getClass())) {
            Map<?, ?> map = (Map<?, ?>) v;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                validateSessionValue((V) entry.getValue());
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    /**
     * 
     * invalidate
     * @since:0.6
     */
	@Override
	public void invalidate() {
		session.invalidate();
		
	}

	@Override
	public String getId() {
		return session.getId();
	}

    @Override
    public int getMaxInactiveInterval() {
        return session.getMaxInactiveInterval();
    }
}
