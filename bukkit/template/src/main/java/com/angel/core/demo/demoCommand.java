package com.angel.core.demo;

import com.angel.core.Annotation.Command;
import com.angel.core.Annotation.Commands.PlayerOnly;
import com.angel.core.Command.Commands;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 17:52 2020/8/29
 * @Connection: ahacgn@gmail.com
 * @Description:
 */

@Command
public class demoCommand extends Commands {
    @Override
    @PlayerOnly
    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String label, String[] strings) {
        cmd.getCommand();
        return false;
    }

    @Override
    public CommandWrap createCommand() {
        List<String> aliases = Arrays.asList("d","D");
        return CommandWrap.builder()
                .name("demo")
                .permission("demo")
                .usage("/demo")
                .permissionMessage("你没有权限使用此指令")
                .aliases(aliases)
                .build();
    }
}
