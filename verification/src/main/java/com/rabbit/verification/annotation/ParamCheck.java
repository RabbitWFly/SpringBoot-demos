package com.rabbit.verification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认做空验证，对于严格验证，需标记format正则格式
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ParamCheck {

    /**
     * 正则表达式
     * @return
     */
    String [] format() default "";

    /**
     * 是否可空
     * @return
     */
    boolean allowNull() default false;

    /**
     * 错误标志
     * @return
     */
    String errorMsg() default "";

    /**
     * 同时不可为空的其他字段
     * @return
     */
    String[] orNulls() default {};
}
