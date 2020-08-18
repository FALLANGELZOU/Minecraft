package com.angel.core.NetEvent;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:38 2020/8/13
 * @Connection: ahacgn@gmail.com
 * @Description: 所有自定义事件的祖类
 */
public abstract class NetEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public NetEvent() {

    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }
}