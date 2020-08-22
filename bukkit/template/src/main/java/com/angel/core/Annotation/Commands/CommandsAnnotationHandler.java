package com.angel.core.Annotation.Commands;

import com.angel.core.Command.Commands;
import com.angel.core.Wrap.Cmd;
import lombok.SneakyThrows;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

/**
 * @Author: Angel_zou
 * @Date: Created in 14:40 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 命令类注解处理器
 */
public class CommandsAnnotationHandler {
    @SneakyThrows
    public static boolean onPlayerOnly(Commands commands,CommandSender commandSender){
        Method method = commands.getClass().getMethod("ProcessingCommand", CommandSender.class, Cmd.class, String.class, String[].class);
        if(method.getDeclaredAnnotation(PlayerOnly.class)!=null){
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage("only player!");
                return false;
            }
        }
        return true;
    }
}
