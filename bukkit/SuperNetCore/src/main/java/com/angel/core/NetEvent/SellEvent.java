package com.angel.core.NetEvent;

import com.angel.core.Entity.SignShop;
import com.angel.core.Main;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:39 2020/8/10
 * @Connection: ahacgn@gmail.com
 * @Description: 收购事件
 */
@Data
public class SellEvent extends NetEvent implements Cancellable {
    private SignShop signShop;
    private Player player;
    private String cmd;
    private boolean cancel;
    public SellEvent(SignShop signShop, Player player){
        this.signShop = signShop;
        this.player = player;
        //this.cmd = "give " + player.getName() + " " + signShop.getId() + " " + String.valueOf(signShop.getCnt());

    }

    public boolean sell(){
        ItemStack item = player.getInventory().getItem(2).clone();

        return true;
    }

    public boolean isCancelled() {
        return this.cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

}
