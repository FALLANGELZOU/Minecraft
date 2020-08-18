package com.angel.core.NetCommandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:02 2020/8/9
 * @Connection: ahacgn@gmail.com
 * @Description: 处理木牌命令
 */
public class SignCommandHandler extends NetCommandHandler {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!this.filter(commandSender,command,s,strings)){
            return false;
        }

        return true;
    }

    @Override
    protected boolean filter(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return false;
        }
        return true;
    }
}
