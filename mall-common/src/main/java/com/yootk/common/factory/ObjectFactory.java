package com.yootk.common.factory;

import com.yootk.common.service.proxy.ServiceProxy;

public class ObjectFactory { // 定义一个工厂类
    // 考虑到后续的开发之中业务层的操作可能很多的地方都会使用到，所以将其保存在ThreadLocal之中
    private static final ThreadLocal<Object> SERVICE_OBJECTS = new ThreadLocal<>();
    private ObjectFactory() {} // 构造方法私有化
    public static Object getInstance(Class<?> clazz) {  // DAO接口实例
        Object result = null;
        try {
            result = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {}
        return result;
    }
    public static Object getServiceInstance(Class<?> clazz) {   // 控制层调用
        Object result = null;
        try {
            SERVICE_OBJECTS.set(clazz.getConstructor().newInstance());
            result = new ServiceProxy().bind(SERVICE_OBJECTS.get()); // 返回代理对象
        } catch (Exception e) {}
        return result;
    }
    public static Object getOrignServiceObject() {  // 留给后续其他方式使用
        return SERVICE_OBJECTS.get();
    }
}
