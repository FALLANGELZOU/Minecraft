package com.angel.core.Annotation.Commands;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PlayerOnly {
}
