package com.angel.core.Listener;



import com.angel.core.Annotation.Listener;
import com.angel.core.Exception.NotListenerException;


import com.angel.core.TemplatePlugin;
import com.angel.core.util.ReflectionsUtil;
import org.bukkit.Bukkit;


import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:37 2020/8/19
 * @Connection: ahacgn@gmail.com
 * @Description: 监听器处理类
 */
public class ListenerHandler {
    private static Set<Class<?>> handlerList;

    public ListenerHandler(){
        handlerList = ReflectionsUtil.getTypesAnnotatedWith(Listener.class);
        //System.out.println("带有Listener注解的类为" + temp);
        register();
    }


    public Set<Class<?>> getHandlerList(){
        return handlerList;
    }

    public static Set<Class<?>> getHandler(){
        return handlerList;
    }


    @Deprecated
    public static void addListener(@NotNull Class<Listeners> listenerClass) throws NotListenerException {
        if(listenerClass.isAnnotationPresent(com.angel.core.Annotation.Listener.class)){
            handlerList.add(listenerClass);
        }else{
            throw new NotListenerException(listenerClass.getName()+"不是监听器");
        }

    }

    private void register(){
        handlerList.forEach(v->{
            if(!Listeners.class.isAssignableFrom(v)){
                try {
                    throw new NotListenerException(v.getName()+"不是监听类");
                } catch (NotListenerException e) {
                    e.printStackTrace();
                }
            }

            try {
                Bukkit.getPluginManager().registerEvents( (org.bukkit.event.Listener) v.newInstance(), TemplatePlugin.getInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

}
