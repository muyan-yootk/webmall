package com.yootk.common.mvc.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ResourceUtil { // 资源读取
    private static ResourceBundle resource; // 资源读取类
    private ResourceUtil() {}
    public static void setMessageBaseName(String baseName) {
        resource = ResourceBundle.getBundle(baseName); // 获取资源对象
    }
    public static String getString(String key) {
        try {
            return resource.getString(key);
        } catch (Exception e) {
            return null;
        }
    }
    public static String getMessage(String key,String ... param) {
        try {
            String str = resource.getString(key) ;
            if (param != null && param.length > 0) {
                return MessageFormat.format(str,param) ;
            }
            return str ;
        } catch (Exception e) {
            return null ;
        }
    }
}
