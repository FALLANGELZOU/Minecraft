package com.angel.core.NetEvent;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:16 2020/8/15
 * @Connection: ahacgn@gmail.com
 * @Description: 书本脚本事件
 */
@Data
public class BookScriptEvent extends NetEvent implements Cancellable {
    private boolean cancel = false;
    private PlayerInteractEvent event;
    public BookScriptEvent(@NotNull PlayerInteractEvent event){
        this.event = event;
       }
    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancel = cancel;
    }
}
