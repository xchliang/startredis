package com.redis.util;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description: Properties文件操作
 *
 * @author xcl
 */
public class PropertiesUtil {
    private static final Logger logger = Logger.getLogger(PropertiesUtil.class);
    public static Map<String, Properties> proMap = new HashMap<String, Properties>();

    /**
     * 读取properties文件属性值
     *
     * @param filePath 资源文件路径
     * @param key
     * @return
     */
    public static String getProperties(String filePath, String key) {
        try {
            Properties prop = getPropertiesFile(filePath);
            String val = null;
            if (prop!=null && prop.get(key) != null) {
                val = prop.get(key).toString();
            }
            return val;
        } catch (Exception e) {
            logger.error("properties文件" + filePath + "读取 " + key + " 值异常！");
        }
        return null;
    }

    /**
     * 读取properties文件
     *
     * @param filePath 资源文件路径
     * @return
     */
    public static Properties getPropertiesFile(String filePath) {
        InputStream in = null;
        Properties prop = null;
        try {
            if (proMap == null) {
                proMap = new HashMap<String, Properties>();
            }
            if ((prop = proMap.get(filePath)) == null) {
                logger.info("加载配置文件:" + filePath);
                in = PropertiesUtil.class.getClassLoader().getResourceAsStream(
                        filePath);
                prop = new Properties();
                prop.load(in);
                proMap.put(filePath, prop);
            }
            return prop;
        } catch (Exception e) {
            logger.error("配置文件" + filePath + "加载异常", e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception ee) {
            }
        }
        return null;

    }

    /**
     * 重新读取配置文件
     *
     * @param filePath 资源文件路径
     * @return
     */
    public static Properties getNewFile(String filePath) {
        InputStream in = null;
        Properties prop = null;
        try {
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
            prop = new Properties();
            prop.load(in);
            if (proMap == null) {
                proMap = new HashMap<String, Properties>();
            }
            proMap.put(filePath, prop);
            return prop;
        } catch (Exception e) {
            logger.error("配置文件" + filePath + "加载异常", e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception ee) {
            }
        }
        return null;
    }

    /**
     * 重新读取配置文件属性值
     *
     * @param filePath 资源文件路径
     * @param key
     * @return
     */
    public static String getNewValue(String filePath, String key) {
        try {
            Properties prop = getNewFile(filePath);
            String val = null;
            if (prop!=null && prop.get(key) != null) {
                val = prop.get(key).toString();
            }
            return val;
        } catch (Exception e) {
            logger.error("properties文件" + filePath + "读取 " + key + " 值异常！");
        }
        return null;
    }

}
