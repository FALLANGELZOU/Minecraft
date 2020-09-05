package com.angel.bukkit.Private.Commands;

import com.angel.bukkit.Private.Store;
import com.angel.core.Annotation.Command;
import com.angel.core.Annotation.Commands.PlayerOnly;
import com.angel.core.Command.Commands;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:19 2020/8/27
 * @Connection: ahacgn@gmail.com
 * @Description: 返回死亡地点
 */
@Command
public class Back extends Commands {
    @Override
    @PlayerOnly
    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String s, String[] strings) {
        Player player = (Player) commandSender;

        if(strings.length != 0){
            player.sendMessage(ChatColor.RED+"参数错误!");
        }

        if(Store.containsInDeathInformationWraps(player)){
            player.teleport(Store.getDeathInformationWrap(player).getLocation());
            return true;
        }else{
            player.sendMessage(ChatColor.RED+"你还未死亡过!");
            return true;
        }
    }

    @Override
    public CommandWrap createCommand() {
        return CommandWrap.builder()
                .description("返还死亡地点")
                .permission("suda")
                .name("back")
                .usage("/back")
                .build();
    }
}
