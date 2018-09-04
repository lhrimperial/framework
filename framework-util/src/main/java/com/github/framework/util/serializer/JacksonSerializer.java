package com.github.framework.util.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;

/**
 * @author longhairen
 * @create 2018-02-25 9:25
 * @description
 **/
public class JacksonSerializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> byte[] serializer(T obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            ExceptionUtils.rethrow(e);
        }
        return null;
    }

    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        try {
            return mapper.readValue(data, clazz);
        } catch (IOException e) {
            ExceptionUtils.rethrow(e);
        }
        return null;
    }
}
