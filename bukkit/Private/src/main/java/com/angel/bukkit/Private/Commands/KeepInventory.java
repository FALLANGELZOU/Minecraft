package com.angel.bukkit.Private.Commands;

import com.angel.core.Annotation.Command;
import com.angel.core.Annotation.Commands.PlayerOnly;
import com.angel.core.Command.Commands;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:26 2020/8/29
 * @Connection: ahacgn@gmail.com
 * @Description: 死亡不掉落
 */
@Command
public class KeepInventory extends Commands {

    @Override
    @PlayerOnly
    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String s, String[] strings) {
        Player player = (Player) commandSender;
        World world = player.getWorld();
        String value = world.getGameRuleValue("keepInventory");
        if (value.equals("true")){
            player.sendMessage(ChatColor.GREEN + "此世界已经是死亡不掉落的了!");
            return true;
        }else{
            int Exp = player.getLevel();
            if(Exp < 30){
                player.sendMessage(ChatColor.RED + "你当前的经验不够，还差"+(30-Exp) + "级!");
            }else{
                world.setGameRuleValue("keepInventory","true");
                player.sendMessage(ChatColor.GREEN+"当前世界已经转变成死亡不掉落!");
                int newExp = player.getLevel() - 30;
                player.setLevel(newExp);
            }
        }
        return true;
    }

    @Override
    public CommandWrap createCommand() {
        return CommandWrap.builder()
                .name("keepInventory")
                .permission("suda")
                .usage("keepInventory")
                .description("解锁该世界的死亡不掉落")
                .build();
    }
}
