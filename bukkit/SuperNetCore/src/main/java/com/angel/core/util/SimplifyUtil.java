package com.angel.core.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:08 2020/8/13
 * @Connection: ahacgn@gmail.com
 * @Description: 简化bukkit的操作
 */
public class SimplifyUtil {
    public static <T extends Event> void triggerEvent(@NotNull T event){
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
