package com.angel.core.Event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 15:00 2020/8/21
 * @Connection: ahacgn@gmail.com
 * @Description: 事件类祖类
 */
public class Events extends Event {
    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList() {
        return handlers;
    }
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }
}
