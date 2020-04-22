package com.lyj.common.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lyj
 * @description: 忽略服务间鉴权
 * @date 2019/10/29
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

    /**
     * 是否内部
     *
     * @return false, true
     */
    boolean isInner() default true;
}
