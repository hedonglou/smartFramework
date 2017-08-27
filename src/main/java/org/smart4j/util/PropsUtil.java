package org.smart4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件工具类
 */
public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties properties = null;
        InputStream is = null;
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(is == null){
                throw new FileNotFoundException(fileName + "file is not found");
            }
            properties = new Properties();
            properties.load(is);
        } catch (IOException e){
            LOGGER.error("load properties file failure",e);
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    /**
     * 获取字符串属性
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key, String defaultValue){
        String value = defaultValue;
        if(properties.contains(key)){
            value = properties.getProperty(key);
        }
        return value;
    }

    public static String getString(Properties properties, String key){
        return getString(properties,key,"");
    }


    /**
     * 获取boolean型属性
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getbBoolean(Properties properties, String key, boolean defaultValue){
        boolean value = defaultValue;
        if(properties.contains(key)){
            value = Boolean.parseBoolean(String.valueOf(properties.getProperty(key)));
        }
        return value;
    }

    public static boolean getbBoolean(Properties properties, String key){
        return getbBoolean(properties,key,false);
    }
}