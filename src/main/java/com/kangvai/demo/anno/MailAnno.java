package com.kangvai.demo.anno;

import java.lang.annotation.*;

/**
 * 定义系统日志注解
 * @author kangvai
 * @date 2020/5/28 18:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MailAnno {
    String value() default "";
}
