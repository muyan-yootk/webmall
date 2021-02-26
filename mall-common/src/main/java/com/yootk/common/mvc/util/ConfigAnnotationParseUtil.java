package com.yootk.common.mvc.util;

import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.mvc.bean.ControllerRequestMapping;
import com.yootk.common.util.StringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ConfigAnnotationParseUtil { // 配置Annotation解析类
    // key为映射路径，而value保存的是Action与Method的关联对象实例
    private Map<String, ControllerRequestMapping> controllerMapResult = new HashMap<>();
    // 业务层和数据层的Map集合：key = 设置的名称、value = 对应的类型
    private Map<String, Class> nameAndTypeMap = new HashMap<>();
    // 业务层和数据层的Map集合：key = 接口的类型、value = 对应的类型
    private Map<Class, Class> objectInterfaceAndClassMap = new HashMap<>();
    private String parentUrl; // 保存父路径
    private Class<?> clazz;
    public ConfigAnnotationParseUtil(Class<?> clazz) { // 解析类的处理
        this.clazz = clazz; // 当前要根据传入的Action进行解析处理
        this.classHandle(); // 实现解析控制
    }
    public void classHandle() { // 进行具体的解析处理操作
        Annotation annotations [] = this.clazz.getAnnotations(); // 获取全部的Annotation
        for (Annotation annotation : annotations) { // 迭代全部的Annotation
            if (annotation.annotationType().equals(Controller.class)) { // 自定义Annotation
                try { // 在整个的控制器类之中对于访问路径的配置有两种形式，一种是进行父路径配置，一种是直接在子路径上编写
                    RequestMapping mapping = this.clazz.getAnnotation(RequestMapping.class); // 获取指定的Annotation
                    this.parentUrl = mapping.value(); // 获取映射的父路径
                    if (this.parentUrl.lastIndexOf("/") == -1) {    // 没有结尾路径符
                        this.parentUrl += "/"; // 追加一个结尾的路径分隔符
                    }
                } catch (Exception e) {}
                this.handleMappingMethod(); // 解析Action类的处理方法
            } else if (annotation.annotationType().equals(Service.class)) { // 业务层注解
                Service service = this.clazz.getAnnotation(Service.class);
                if ("none".equalsIgnoreCase(service.value())) { // 是一个空内容
                    // 此时没有为Service设置具体的名称，那么这个名称就应该由类名称直接生成
                    // 此时将类名称首字母小写，例如：MessageServiceImpl子类，名称为“messageServiceImpl”
                    this.nameAndTypeMap.put(StringUtil.firstLower(this.clazz.getSimpleName()),this.clazz);
                } else {    // 用户设置了具体的Bean名称
                    this.nameAndTypeMap.put(service.value(), this.clazz);
                }
                // 除了实现名称的注入之外，还有可能会根据类型实现注入的管理操作，最佳的做法就是匹配接口
                Class<?> clazzInterfaces[] = this.clazz.getInterfaces(); // 获取全部的接口
                for (int x = 0; x < clazzInterfaces.length; x ++) { // 实现接口迭代
                    this.objectInterfaceAndClassMap.put(clazzInterfaces[x], this.clazz);
                }
            } else if (annotation.annotationType().equals(Repository.class)) {  // 数据层注解
                Repository repository = this.clazz.getAnnotation(Repository.class); // 获取数据层注解
                if ("none".equals(repository.value())) {    // 判断是否有名称
                    this.nameAndTypeMap.put(StringUtil.firstLower(this.clazz.getSimpleName()), this.clazz);
                } else {
                    this.nameAndTypeMap.put(repository.value(), this.clazz);
                }
                Class<?> clazzInterfaces[] = this.clazz.getInterfaces(); // 获取全部接口
                for (int x = 0; x < clazzInterfaces.length; x++) { // 迭代全部接口
                    this.objectInterfaceAndClassMap.put(clazzInterfaces[x], this.clazz);
                }
            }
        }
    }
    // 在每一个Action程序类中还会存在有大量的程序控制方法，因为这些方法可以完成具体的业务处理
    private void handleMappingMethod() {    // 解析映射处理方法
        if (this.parentUrl == null) {   // 现在没有得到父路径
            this.parentUrl = ""; // 现在没有父路径
        }
        // 获取当前类之中的所有的处理方法，从这些方法里面找到拥有“@RequestMapping”注解的方法
        Method methods[] = this.clazz.getDeclaredMethods(); // 获取类中的全部方法
        for (Method method : methods) { // 循环所有的方法
            if (method.isAnnotationPresent(RequestMapping.class)) { // 判断拥有指定的注解项
                RequestMapping mapping = method.getAnnotation(RequestMapping.class); // 获取指定Annotation
                String path = this.parentUrl + mapping.value(); // 获取完整路径
                this.controllerMapResult.put(path, new ControllerRequestMapping(this.clazz, method));
            }
        }
    }

    /**
     * 将一个Action类的解析结果进行返回（key = 访问路径、value = Class/Method）
     * @return 返回Map集合
     */
    public Map<String, ControllerRequestMapping> getControllerMapResult() {
        return controllerMapResult;
    }

    public Map<String, Class> getNameAndTypeMap() { // 获取名称的Map集合
        return nameAndTypeMap;
    }

    public Map<Class, Class> getObjectInterfaceAndClassMap() { // 获取类型的Map集合
        return objectInterfaceAndClassMap;
    }
}
