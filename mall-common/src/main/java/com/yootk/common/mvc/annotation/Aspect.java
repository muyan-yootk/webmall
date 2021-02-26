package com.yootk.common.mvc.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE}) // 该注解需要在类型的定义上使用
@Retention(RetentionPolicy.RUNTIME) // 该注解在运行时生效
public @interface Aspect {}
