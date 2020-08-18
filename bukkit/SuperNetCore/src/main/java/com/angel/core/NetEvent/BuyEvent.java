package com.angel.core.NetEvent;


import com.angel.core.Entity.SignShop;
import com.angel.core.Exception.FailTranslationToIntException;
import com.angel.core.Main;
import com.angel.core.util.CheckUtil;
import com.angel.core.util.TranslationUtil;
import lombok.Data;
import net.minecraft.server.v1_16_R1.Item;
import net.minecraft.server.v1_16_R1.ItemActionContext;
import net.minecraft.server.v1_16_R1.ItemStack;
import net.minecraft.server.v1_16_R1.Items;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:33 2020/8/10
 * @Connection: ahacgn@gmail.com
 * @Description: 木牌购买事件
 */
@Data
public class BuyEvent extends NetEvent implements Cancellable {
    private SignShop signShop;
    private Player player;
    private String cmd;
    private boolean cancel;
    public BuyEvent(SignShop signShop, Player player){
        this.signShop = signShop;
        this.player = player;
        this.cmd = "give " + player.getName() + " " + signShop.getId() + " " + String.valueOf(signShop.getCnt());

    }
    public boolean give(){
        try {
            Main.getInstance().getServer().dispatchCommand(Main.getInstance().getServer().getConsoleSender(), cmd);
            return true;
        }catch (CommandException e){
            Main.getInstance().getLogger().info("命令异常!");
            return false;
        }
    }

    public boolean isCancelled() {
        return this.cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

}
