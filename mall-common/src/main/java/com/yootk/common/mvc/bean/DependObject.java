package com.yootk.common.mvc.bean;

import com.yootk.common.factory.ObjectFactory;
import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.util.ScannerPackageUtil;

import java.lang.reflect.Field;

public class DependObject {
    // 如果是业务层的注入，则targetObject就是Action对象实例
    // 如果是数据层的注入，则targetObject就是Service对象实例
    private Object targetObject; // 要进行注入的管理类
    // 一个类之中的属性上会存在有“@Autowired”注解，现在只考虑属性，所以扫描类中的属性即可
    public DependObject(Object targetObject) {
        this.targetObject = targetObject; // 根据目标对象进行控制
    }
    public void injectObject() {    // 实现注入操作
        // 当前只考虑成员属性的注入了，如果你有兴趣，则可以继续在此处实现方法之中的注入
        Field fields [] = this.targetObject.getClass().getDeclaredFields(); // 获取全部的成员属性
        for (int x = 0; x < fields.length; x ++) {
            if (fields[x].isAnnotationPresent(Autowired.class)) {   // 存在有注入的管理注解
                Autowired autowired = fields[x].getAnnotation(Autowired.class);
                // 当前的注入操作可以根据名称注入也可以根据类型注入，所以要先确定是否有名称
                Class<?> injectClazz = null; // 通过ScannerPackageUtil工具找到
                if ("none".equals(autowired.name())) {  // 没有配置名称
                    // 根据类型实现注入操作
                    injectClazz = ScannerPackageUtil.getByTypeMap().get(fields[x].getType());
                } else {    // 此时存在有名称
                    injectClazz = ScannerPackageUtil.getByNameMap().get(autowired.name());
                }
                if (injectClazz != null) {  // 已经查找到了对应的类型
                    try {
                        fields[x].setAccessible(true); // 取消封装
                        if (injectClazz.isAnnotationPresent(Aspect.class)) {    // 业务层上存在这样的注解
                            fields[x].set(this.targetObject, ObjectFactory.getServiceInstance(injectClazz));
                            // 如果现在注入的是一个业务层对象，那么后续还有可能继续要注入数据层对象
                            new DependObject(ObjectFactory.getOrignServiceObject()).injectObject();
                        } else {    // 没有事务的控制要求
                            fields[x].set(this.targetObject, ObjectFactory.getInstance(injectClazz));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
