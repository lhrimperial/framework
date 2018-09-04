package com.github.framework.util.cryp;



import java.security.MessageDigest;

/**
 * @author 龙海仁
 * @date 2015年12月4日下午10:48:35
 * @Description: MD5工具类
 */
public class MD5Util {

    private static final String HEX_NUMS_STR="0123456789ABCDEF";
    private final static String MD5 = "MD5";
    private static final Integer SALT_LENGTH = 12;

    /**
     * MD5加密算法
     * @param data
     * @return
     */
    public static String md5Encrypt(String data) {
        String resultString = null;
        try {
            resultString = new String(data);
            MessageDigest md = MessageDigest.getInstance(MD5);
            resultString =byteToHexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }


    /**
     * 将16进制字符串转换成字节数组
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4
                    | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }


    /**
     * 将指定byte数组转换成16进制字符串
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

}
