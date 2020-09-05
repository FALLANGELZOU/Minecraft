package com.angel.core.Annotation;

import java.lang.annotation.*;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:31 2020/8/30
 * @Connection: ahacgn@gmail.com
 * @Description:
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JsonConfiguration {
    String value();
}
