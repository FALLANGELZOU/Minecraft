package com.angel.bukkit.Private.Commands;

import com.angel.core.Annotation.Command;
import com.angel.core.Command.Commands;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import org.bukkit.command.CommandSender;
import org.spigotmc.RestartCommand;

/**
 * @Author: Angel_zou
 * @Date: Created in 21:37 2020/8/30
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
@Command
public class Restart extends Commands {
    @Override
    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String s, String[] strings) {
        return false;
    }

    @Override
    public CommandWrap createCommand() {
        return CommandWrap.builder()
                .usage("/rs")
                .permission("suda")
                .description("重启")
                .name("rs")
                .build();
    }
}
