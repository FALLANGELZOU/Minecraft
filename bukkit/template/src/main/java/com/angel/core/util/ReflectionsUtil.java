package com.angel.core.util;

import com.angel.core.TemplatePlugin;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:53 2020/8/20
 * @Connection: ahacgn@gmail.com
 * @Description: 反射框架工具包
 */
public class ReflectionsUtil {
    public static Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> cls){
//        System.out.println("******************");
//        System.out.println(TemplatePlugin.getInstance().getClass().getPackage().getName());
//        System.out.println("******************");
        return TemplatePlugin.getInstance().getReflections().getTypesAnnotatedWith(cls);
    }
}
