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

/**
 * @Author: Angel_zou
 * @Date: Created in 22:50 2020/8/29
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
@Command
public class IsKeepInventory extends Commands {
    @Override
    @PlayerOnly
    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String s, String[] strings) {
        Player player = (Player) commandSender;
        World world = player.getWorld();
        if(world.getGameRuleValue("keepInventory").equals("true")){
            player.sendMessage(ChatColor.GREEN+"此世界已是死亡不掉落!");
        }
        else
        {
            player.sendMessage(ChatColor.RED+"非死亡不掉落世界,小心!");
        }
        return true;
    }

    @Override
    public CommandWrap createCommand() {
        return CommandWrap.builder()
                .description("判断当前世界是否是死亡不掉落")
                .usage("/isKeepInventory")
                .permission("suda")
                .name("isKeepInventory")
                .build();
    }
}
