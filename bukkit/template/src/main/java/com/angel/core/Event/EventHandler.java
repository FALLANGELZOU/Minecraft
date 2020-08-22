package com.angel.core.Event;

import com.angel.core.Annotation.Event;
import com.angel.core.Exception.NotEventException;
import com.angel.core.util.ReflectionsUtil;

import java.util.Set;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:40 2020/8/19
 * @Connection: ahacgn@gmail.com
 * @Description: 事件处理类
 */
public class EventHandler {
    private static Set<Class<?>> eventList;
    public EventHandler(){
        eventList = ReflectionsUtil.getTypesAnnotatedWith(Event.class);
        eventList.forEach(v->{
            if(!Events.class.isAssignableFrom(v)){
                try {
                    throw new NotEventException(v.getName() + "不是事件类");
                } catch (NotEventException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
