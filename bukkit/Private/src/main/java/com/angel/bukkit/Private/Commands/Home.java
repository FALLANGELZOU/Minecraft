package com.angel.bukkit.Private.Commands;

import com.angel.bukkit.Private.Entitys.User;
import com.angel.core.Annotation.Command;
import com.angel.core.Annotation.Commands.PlayerOnly;
import com.angel.core.Command.Commands;
import com.angel.core.TemplatePlugin;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import com.angel.core.util.ConfigurationUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:00 2020/8/27
 * @Connection: ahacgn@gmail.com
 * @Description: 回家
 */
@Command
public class Home extends Commands {
    @Override
    @PlayerOnly
    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String s, String[] strings) {
        Player player = (Player) commandSender;
        if(strings.length != 0){
            player.sendMessage(ChatColor.RED+"参数错误!");
        }

        File file = new File(TemplatePlugin.getInstance().getDataFolder(),"configs/User.yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);


        File file2 = new File(TemplatePlugin.getInstance().getDataFolder(),"config.yml");
        FileConfiguration fileConfiguration2 = YamlConfiguration.loadConfiguration(file2);



        if(ConfigurationUtil.get("configs/User.yml").contains(player.getName())){
            Location location = ConfigurationUtil.get("configs/User.yml").get(player.getName());
            if(location != null){
                player.teleport(location);
                return true;
            }else{

                player.sendMessage(ChatColor.RED+"你还没设置家");
                return true;

            }
        }else{
            player.sendMessage(ChatColor.RED+"未知错误!");
        }



        return false;
    }

    @Override
    public CommandWrap createCommand() {
        return CommandWrap.builder()
                .name("home")
                .description("快速回家")
                .usage("/home")
                .permission("suda")
                .build();
    }
}
