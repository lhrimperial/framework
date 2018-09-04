package com.github.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * ValidateUtils
 * @author 龙海仁
 * @date 2016年6月12日上午10:28:59 
 */
public class ValidateUtils {

	/**
	 * 是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$");
		return pattern.matcher(str).matches();
	}

	/** 
     * 手机号验证 
     *  
     * @param  mobile
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String mobile) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][2,3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(mobile);  
        b = m.matches();
        return b;
    }
    
    /**
     * 邮箱验证
     * @param email
     * @return
     * @author 龙海仁
     * @date 2015年7月20日
     * @update
     */
	public static boolean isEmail(String email){
		if(email == null || email.equals("") || email.length() > 50){
			return false;
		}
		String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(pattern1);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 固定电话验证
	 * @param tel
	 * @return
	 * @author 龙海仁
	 * @date 2015年7月23日
	 * @update
	 */
	public static boolean isTel(String tel){
		String pattern = "^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(tel);
		return m.matches();
	}
}
