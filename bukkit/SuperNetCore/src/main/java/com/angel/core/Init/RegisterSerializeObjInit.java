package com.angel.core.Init;

import org.bukkit.configuration.serialization.ConfigurationSerialization;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:34 2020/8/10
 * @Connection: ahacgn@gmail.com
 * @Description: 注册序列化对象
 */
public class RegisterSerializeObjInit {
    private String[] className = {
            "User",
            "SignCommand",
            "SignShop"

    };
    public RegisterSerializeObjInit() throws ClassNotFoundException {
        for(String name:className){
            Class c = Class.forName("com.angel.core.Entity." + name);
            ConfigurationSerialization.registerClass(c);
        }
    }
}
