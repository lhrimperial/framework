package com.github.framework.util.image;

/**
 * @author：龙海仁
 * @create：2015年7月17日 上午11:25:40
 * @description：
 */
public class RandomUtils {
	// 得到随机字符
    public static String randomStr(int n) {
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }
    
 // 得到随机字符
    public static String randomNum(int n) {
        String str1 = "1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }
}
