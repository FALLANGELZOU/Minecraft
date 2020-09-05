package com.angel.bukkit.Private.Commands;

import com.angel.core.Annotation.Command;
import com.angel.core.Command.Commands;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @Author: Angel_zou
 * @Date: Created in 14:13 2020/8/27
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
@Command
public class Suda extends Commands {
    @Override
    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String s, String[] strings) {
        if(strings.length != 0){
            commandSender.sendMessage(ChatColor.RED+"参数错误");
            return true;
        }
        commandSender.sendMessage(
                          ChatColor.GREEN+"/suda\n" +
                             ChatColor.GREEN+"/back\n" +
                             ChatColor.GREEN+"/sethome\n" +
                             ChatColor.GREEN+"/home\n" +
                             ChatColor.GREEN+"/isKeepInventory\n" +
                             ChatColor.GREEN+"/keepInventory");
        return true;
    }

    @Override
    public CommandWrap createCommand() {
        return CommandWrap.builder()
                .usage("/suda")
                .name("suda")
                .description("指令集")
                .permission("suda")
                .build();
    }
}
