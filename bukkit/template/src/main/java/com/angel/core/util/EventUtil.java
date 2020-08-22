package com.angel.core.util;

import com.angel.core.Event.Events;
import org.bukkit.Bukkit;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 15:06 2020/8/21
 * @Connection: ahacgn@gmail.com
 * @Description: 事件工具类
 */
public class EventUtil implements Util{
    public static <T extends Events> void triggerEvent(@NotNull T event){
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
