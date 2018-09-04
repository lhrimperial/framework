package com.github.framework.util;

/**
 * @author longhairen
 * @create 2017/6/6 0006 上午 11:49
 */
public class NumberUtil {

    /**
     * int整数转换为4字节的byte数组
     *
     * @param i 整数
     * @return byte数组
     */
    public static byte[] intToByte4(int i) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (i & 0xFF);
        targets[2] = (byte) (i >> 8 & 0xFF);
        targets[1] = (byte) (i >> 16 & 0xFF);
        targets[0] = (byte) (i >> 24 & 0xFF);
        return targets;
    }

    /**
     * long整数转换为8字节的byte数组
     *
     * @param lo long整数
     * @return byte数组
     */
    public static byte[] longToByte8(long lo) {
        byte[] targets = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte) ((lo >>> offset) & 0xFF);
        }
        return targets;
    }

    /**
     * short整数转换为2字节的byte数组
     *
     * @param s short整数
     * @return byte数组
     */
    public static byte[] unsignedShortToByte2(int s) {
        byte[] targets = new byte[2];
        targets[0] = (byte) (s >> 8 & 0xFF);
        targets[1] = (byte) (s & 0xFF);
        return targets;
    }

    /**
     * byte数组转换为无符号short整数
     *
     * @param bytes byte数组
     * @return short整数
     */
    public static int byte2ToUnsignedShort(byte[] bytes) {
        return byte2ToUnsignedShort(bytes, 0);
    }

    /**
     * byte数组转换为无符号short整数
     *
     * @param bytes byte数组
     * @param off   开始位置
     * @return short整数
     */
    public static int byte2ToUnsignedShort(byte[] bytes, int off) {
        if (bytes.length != 2) {
            throw new IllegalArgumentException("Byte length must be 2");
        }
        int high = bytes[off];
        int low = bytes[off + 1];
        return (high << 8 & 0xFF00) | (low & 0xFF);
    }

    /**
     * byte数组转换为int整数
     *
     * @param bytes byte数组
     * @return int整数
     */
    public static int byte4ToInt(byte[] bytes) {
        return byte4ToInt(bytes, 0);
    }

    /**
     * byte数组转换为int整数
     *
     * @param bytes byte数组
     * @param off   开始位置
     * @return int整数
     */
    public static int byte4ToInt(byte[] bytes, int off) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Byte length must be 4");
        }
        int b0 = bytes[off] & 0xFF;
        int b1 = bytes[off + 1] & 0xFF;
        int b2 = bytes[off + 2] & 0xFF;
        int b3 = bytes[off + 3] & 0xFF;
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

    /**
     * byte数组转换为long整数
     * @param bytes
     * @return
     */
    public static long byte8ToLong(byte[] bytes) {
        return byte8ToLong(bytes, 0);
    }

    /**
     * byte数组转换为long整数
     * @param bytes
     * @param off
     * @return
     */
    public static long byte8ToLong(byte[] bytes, int off) {
        if (bytes.length != 8) {
            throw new IllegalArgumentException("Byte length must be 8");
        }
        long result = 0;
        for (int i = off,len = bytes.length; i < len; i++){
            int offset = (len - 1 - i) * 8;
            result |= ((long)(bytes[i] & 0xFF) << offset);
        }
        return result;
    }
}
