package com.redis.util;

import java.io.*;

/**
 * 序列化
 * @author xcl
 */
public class SerializeUtil {

    /**
     * 序列化
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj) {
        byte[] arr = null;
        ObjectOutputStream os = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(obj);
            arr = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }

    /**
     * 反序列化
     *
     * @param in
     * @return
     */
    public static Object unserialize(byte[] in) {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(in);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}