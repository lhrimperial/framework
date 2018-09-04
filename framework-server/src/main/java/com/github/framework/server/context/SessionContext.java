package com.github.framework.server.context;



import com.github.framework.server.shared.define.Definitions;
import com.github.framework.server.shared.entity.IUser;
import com.github.framework.server.web.session.ISession;
import com.github.framework.server.web.session.Session;

import java.util.Locale;

/**
* @ClassName: SessionContext
* @Description: session上下文
* @author 龙海仁
* @date 2016年4月22日 下午2:48:33
*
*/
public final class SessionContext {
    /**
     * 初始化session上下文
     */
    private static final ThreadLocal<ISession<Object>> SAFE_SESSION = new ThreadLocal<ISession<Object>>() {
        @Override
        protected ISession<Object> initialValue() {
            return new Session<Object>();
        }
        
    };
    
    private SessionContext() {
        
    }
    
    public static ISession<Object> getSession() {
        return SAFE_SESSION.get();
    }
    
    /**
     * 设置真是session
     * setSession
     * @param session
     * @return void
     * @since:0.7
     */
    public static void setSession(javax.servlet.http.HttpSession session) {
        ISession<Object> sessionHold = SAFE_SESSION.get();
        sessionHold.init(session);   
    }
    
    /**
     * 清除ThreadLocal
     * remove
     * @return void
     * @since:0.7
     */
    public static void remove() {
    	SAFE_SESSION.get().init(null);
    }
    
    /**
     * 设置User
     * setCurrentUser
     * @param user
     * @return void
     * @since:0.7
     */
    public static void setCurrentUser(String user){
    	SAFE_SESSION.get().setObject(Definitions.KEY_USER, user);
    }
    
    public static void setUser(IUser user){
    	SAFE_SESSION.get().setObject(Definitions.KEY_USER_CACHE, user);
    }
    /**
     * 设置Locale
     * setUserLocale
     * @param userLocale
     * @return void
     * @since:0.7
     */
    public static void setUserLocale(Locale userLocale){
    	SAFE_SESSION.get().setObject(Definitions.KEY_LOCALE, userLocale);
    }
    
    /**
     * 使session失效
     * invalidateSession
     * @return void
     * @since:0.7
     */
    public static void invalidateSession() {
    	SAFE_SESSION.get().invalidate();
    }
    
}
