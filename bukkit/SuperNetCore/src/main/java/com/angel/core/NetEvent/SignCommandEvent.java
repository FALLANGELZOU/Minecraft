package com.angel.core.NetEvent;

import com.angel.core.Entity.SignCommand;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockBreakEvent;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:22 2020/8/14
 * @Connection: ahacgn@gmail.com
 * @Description: 木牌指令事件
 */
@Data
public class SignCommandEvent extends NetEvent implements Cancellable {
    private boolean cancel = false;
    private SignCommand signCommand;
    private Player player;
    public SignCommandEvent(@NotNull SignCommand signCommand, Player player){
        this.player = player;
        this.signCommand = signCommand;
    }
    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
            this.cancel = cancel;
    }
}
