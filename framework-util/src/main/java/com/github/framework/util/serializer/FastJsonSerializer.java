package com.github.framework.util.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @author longhairen
 * @create 2018-02-25 9:22
 * @description
 **/
public class FastJsonSerializer {

    public static <T> byte[] serializer(T obj) {
        return JSON.toJSONString(obj).getBytes();
    }

    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        return JSON.parseObject(data, clazz);
    }
}
