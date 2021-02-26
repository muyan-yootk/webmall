package com.yootk.common.mvc.annotation;

import java.lang.annotation.*;

@Documented
// 该注解可以在类上使用，也可以在方法上使用，如果在类上使用就表示定义父路径，而在访问上使用表示配置子路径
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // 该注解在运行时生效
public @interface RequestMapping {
    public String value() default "/"; // 路径的名称
}
