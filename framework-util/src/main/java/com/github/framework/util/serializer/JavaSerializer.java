package com.github.framework.util.serializer;

import java.io.*;

/**
 *
 */
public class JavaSerializer {


    public static <T> byte[] serializer(T obj) {
        ObjectOutputStream out = null;
        ByteArrayOutputStream bout = null;
        try {
            bout = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bout);
            out.writeObject(obj);
            return bout.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(data)));
            return (T) ois.readObject();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
