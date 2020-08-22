package com.angel.core.Command;

import com.angel.core.Annotation.Commands.CommandsAnnotationHandler;
import com.angel.core.TemplatePlugin;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.Value;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:22 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 命令祖类
 */

@Data
public abstract class Commands implements CommandExecutor {

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!CommandsAnnotationHandler.onPlayerOnly(this,commandSender)) return true;

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(!(player.hasPermission(command.getPermission())||player.isOp())){
                if(command.getPermissionMessage() == null||command.getPermissionMessage().isEmpty()||command.getPermissionMessage().equals("")){
                    command.setPermissionMessage("You have no permission to execute this command");
                }
                player.sendMessage(command.getPermissionMessage());
                return true;
            }
        }

        Cmd cmd = new Cmd(command);
        return ProcessingCommand(commandSender,cmd,s,strings);
    }

    public abstract boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String label, String[] strings);


    public abstract CommandWrap createCommand();

//    private static class Builder{
//        private String name;
//        private String description;
//        private String usage;
//        private List<String> aliases;
//        private PluginCommand cmd;
//        private String permission;
//
//        public Builder(){}
//        public Builder name(String name){
//            this.name = name;
//            return this;
//        }
//        public Builder description(String description){
//            this.description = description;
//            return this;
//        }
//        public Builder usage(String usage){
//            this.usage = usage;
//            return this;
//        }
//        public Builder aliases(List<String> aliases){
//            this.aliases = aliases;
//            return this;
//        }
//        public Builder permission(String permission){
//            this.permission = permission;
//            return this;
//        }
//
//        @SneakyThrows
//        public Commands build(){
//            Constructor<PluginCommand> pluginCommandConstructor = PluginCommand.class.getDeclaredConstructor(String.class,Plugin.class);
//            pluginCommandConstructor.setAccessible(true);
//            cmd = pluginCommandConstructor.newInstance(name, TemplatePlugin.getInstance());
//            cmd.setAliases(aliases);
//            cmd.setDescription(description);
//            cmd.setName(name);
//            cmd.setUsage(usage);
//            cmd.setPermission(permission);
//            Commands commands = new Commands(this);
//            cmd.setExecutor(commands);
//            commands.setCmd(cmd);
//            cmd.setTabCompleter();
//            return commands;
//        }
//
//    }
}
