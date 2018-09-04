package com.github.framework.server.context;



import com.github.framework.server.shared.entity.IUser;

import java.util.Locale;

/**
 * 系统用户信息获得的上下文管理
 * 用户信息的ID存放于应用服务器的Session中
 * 通过Session的ID通过缓存获取用户
 * 缓存中没有指定用户信息时，会自动通过Provider去获取信息
 * 用户在缓存中存在的时候受DataReloader决定
 *
 */
public final class UserContext {
    
	private static final ThreadLocal<IUser> USER_STORE = new ThreadLocal<IUser>();
	
	private static final ThreadLocal<Locale> USER_LOCALE= new ThreadLocal<Locale>();
    
    private UserContext() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * 获取当前会话的用户
     * 如果没有用户信息返回值为NULL
     * @return
     */
	public static IUser getCurrentUser() {
        return USER_STORE.get();
    }
    
    /**
    */
    
    public static void setCurrentUser(IUser user){
    	USER_STORE.set(user);
    }
    
    public static Locale getUserLocale(){
    	return USER_LOCALE.get();
    }
    
    public static void setUserLocale(Locale locale){
    	USER_LOCALE.set(locale);
    }
    
    /**
     * 清除ThreadLocal中的数据
     * remove
     * @return void
     * @since:0.6
     */
    public static void remove(){
    	USER_STORE.set(null);
    	USER_LOCALE.set(null);
    }
}
