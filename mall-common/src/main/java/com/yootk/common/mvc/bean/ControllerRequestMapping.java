package com.yootk.common.mvc.bean;

import java.lang.reflect.Method;
// 同一个Action之中的处理方法有可能会存在有若干个Method，每一个Method映射都要有一个此类对象包装
public class ControllerRequestMapping { // 控制层的数据关联
    private Class<?> actionClazz; // 保存匹配的Action类的信息
    private Method actionMethod; // 保存映射的访问
    public ControllerRequestMapping(Class<?> actionClazz, Method actionMethod) {
        this.actionClazz = actionClazz;
        this.actionMethod = actionMethod;
    }
    public Class<?> getActionClazz() {
        return actionClazz;
    }
    public Method getActionMethod() {
        return actionMethod;
    }
}
