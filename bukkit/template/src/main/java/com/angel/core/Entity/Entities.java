package com.angel.core.Entity;

import lombok.SneakyThrows;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 15:19 2020/8/21
 * @Connection: ahacgn@gmail.com
 * @Description: 实体类祖类
 */
public abstract class Entities  implements ConfigurationSerializable {

    @SneakyThrows
    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> map = new HashMap<>();
        new AutomaticSerialize(this,map);
        return map;
    }
    @SneakyThrows
    public Entities(Map<String,Object> map){
        new AutomaticDeserialize(this,map);
    }

    public Entities(){}

    class AutomaticDeserialize{
        public <T extends Entities> AutomaticDeserialize(T temp,Map<String,Object> map) throws IllegalAccessException {
            Class cls = temp.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field:fields){
                field.setAccessible(true);
                field.set(temp,map.get(field.getName()));
            }

        }
    }

    class AutomaticSerialize{
        public <T extends Entities> AutomaticSerialize(T temp, Map<String,Object> map) throws IllegalAccessException {
            Class cls = temp.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field: fields){
                field.setAccessible(true);
                map.put(field.getName(),field.get(temp));
            }
        }
    }
}
