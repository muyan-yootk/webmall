package com.yootk.common.mvc.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD}) // 成员属性以及方法上司hi用
@Retention(RetentionPolicy.RUNTIME) // 该注解在运行时生效
public @interface Autowired { // 注入管理
    public String name() default "none"; // 每一个注解的组件都会存在有一个名称
}
