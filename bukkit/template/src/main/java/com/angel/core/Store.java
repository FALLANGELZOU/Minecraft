package com.angel.core;

import com.angel.core.Template.TemplateManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 14:44 2020/9/5
 * @Connection: ahacgn@gmail.com
 * @Description: 储存实例
 */
public abstract class Store {
    private static Map<String,TemplateManager> templateManagerList = new HashMap<>();
    public static boolean addTemplateManager(TemplateManager tm){
        String key = tm.getPlugin().getName();
        if(!templateManagerList.containsKey(key)){
            templateManagerList.put(key,tm);
        }
        return true;
    }
}
