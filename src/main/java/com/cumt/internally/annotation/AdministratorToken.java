package com.cumt.internally.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需管理员权限
 *
 * @author NNroc
 * @date 2020/5/14 18:48
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdministratorToken {
    boolean required() default true;
}