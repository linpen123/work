package com.baizhi.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解使用位置
@Target(ElementType.FIELD)
//注解使用时机
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAnnotation {
    public String name();
}
