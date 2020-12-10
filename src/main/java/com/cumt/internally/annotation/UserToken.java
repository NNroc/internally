package com.cumt.internally.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需普通用户或管理员权限
 *
 * @author NNroc
 * @date 2020/5/12 14:53
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserToken {
    boolean required() default true;
}