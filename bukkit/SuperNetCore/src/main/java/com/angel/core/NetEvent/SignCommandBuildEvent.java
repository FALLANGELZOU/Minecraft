package com.angel.core.NetEvent;


import com.google.common.base.Verify;
import lombok.Data;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.SignChangeEvent;

/**
 * @Author: Angel_zou
 * @Date: Created in 23:20 2020/8/8
 * @Connection: ahacgn@gmail.com
 * @Description: 牌子指令生成事件
 */
@Data
public class SignCommandBuildEvent extends NetEvent implements Cancellable {
    private boolean cancel = false;
    private SignChangeEvent event;

    public SignCommandBuildEvent(SignChangeEvent event) {
        this.event = event;
    }

    public boolean isCancelled() {
        return this.cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }




}
