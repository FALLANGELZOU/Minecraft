package com.angel.core.Command;

import com.angel.core.Annotation.Command;
import com.angel.core.Exception.NotCommandException;
import com.angel.core.TemplatePlugin;
import com.angel.core.Wrap.CommandWrap;
import com.angel.core.util.ReflectionsUtil;
import lombok.SneakyThrows;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:38 2020/8/19
 * @Connection: ahacgn@gmail.com
 * @Description: 命令处理类
 */
public class CommandHandler {
    private Set<Class<?>> commandList;

    public CommandHandler(){
        commandList = ReflectionsUtil.getTypesAnnotatedWith(Command.class);
        commandList.forEach(v->{

            if(!Commands.class.isAssignableFrom(v)){
                try {
                    throw new NotCommandException(v.getName()+"不是命令类");
                } catch (NotCommandException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println(v.getSuperclass().getName().equals("com.angel.core.Command.Commands"));

            CommandMap map = null;
            try {
                Field field= SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);
                map = (CommandMap)field.get(TemplatePlugin.getInstance().getServer().getPluginManager());
            }catch (Exception e){
                e.printStackTrace();
            }


            try {
                Commands commands = (Commands) v.newInstance();
                CommandWrap commandWrap = commands.createCommand();
                Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
                c.setAccessible(true);
                PluginCommand pluginCommand = c.newInstance(commandWrap.getName(), TemplatePlugin.getInstance());

                pluginCommand.setName(commandWrap.getName());
                pluginCommand.setExecutor(commands);

                if(commandWrap.getTabCompleter()!=null)
                    pluginCommand.setTabCompleter(commandWrap.getTabCompleter());
                if(commandWrap.getPermission()!=null)
                    pluginCommand.setPermission(commandWrap.getPermission());
                if(commandWrap.getUsage()!=null)
                    pluginCommand.setUsage(commandWrap.getUsage());
                if(commandWrap.getDescription()!=null)
                    pluginCommand.setDescription(commandWrap.getDescription());
                if(commandWrap.getLabel()!=null)
                    pluginCommand.setLabel(commandWrap.getLabel());
                if(commandWrap.getAliases()!=null)
                    pluginCommand.setAliases(commandWrap.getAliases());
                if(commandWrap.getPermissionMessage()!=null)
                    pluginCommand.setPermissionMessage(commandWrap.getPermissionMessage());



                map.register(pluginCommand.getName(),pluginCommand);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

}
