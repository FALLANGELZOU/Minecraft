package com.angel.core.NetCommandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @Author: Angel_zou
 * @Date: Created in 9:04 2020/8/14
 * @Connection: ahacgn@gmail.com
 * @Description: 所有命令处理类的祖类
 */
abstract class NetCommandHandler implements CommandExecutor {
    @Override
    public abstract boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings);

    protected abstract boolean filter(CommandSender commandSender, Command command, String s, String[] strings);
}
