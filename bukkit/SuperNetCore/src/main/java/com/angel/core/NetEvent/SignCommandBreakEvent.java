package com.angel.core.NetEvent;

import lombok.Data;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockBreakEvent;
import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:17 2020/8/13
 * @Connection: ahacgn@gmail.com
 * @Description: 木牌指令被破坏时触发
 */
@Data
public class SignCommandBreakEvent extends NetEvent implements Cancellable {
    private boolean cancel = false;
    private BlockBreakEvent event;
    private int type;
    public SignCommandBreakEvent(@NotNull BlockBreakEvent event,int type){
        this.event = event;
        this.type = type;
    }
    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean b) {
            cancel = b;
    }
}
