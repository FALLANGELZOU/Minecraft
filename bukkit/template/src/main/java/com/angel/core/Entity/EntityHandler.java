package com.angel.core.Entity;

import com.angel.core.Annotation.Entity;
import com.angel.core.Exception.NotEntityException;
import com.angel.core.util.ReflectionsUtil;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.craftbukkit.libs.jline.internal.Configuration;

import java.util.Set;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:39 2020/8/19
 * @Connection: ahacgn@gmail.com
 * @Description: 实体类处理类
 */
public class EntityHandler {
    public EntityHandler(){
        Set<Class<?>> handleList = ReflectionsUtil.getTypesAnnotatedWith(Entity.class);
        handleList.forEach(v->{
            if(!Entities.class.isAssignableFrom(v)){
                try {
                    throw new NotEntityException(v.getName()+"不是实体类");
                } catch (NotEntityException e) {
                    e.printStackTrace();
                }
            }
            ConfigurationSerialization.registerClass((Class<? extends ConfigurationSerializable>) v);
        });

    }
}
