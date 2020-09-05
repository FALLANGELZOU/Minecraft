package com.angel.bukkit.Private.Commands;

import com.angel.bukkit.Private.Entitys.User;
import com.angel.core.Annotation.Command;
import com.angel.core.Annotation.Commands.PlayerOnly;
import com.angel.core.Command.Commands;
import com.angel.core.TemplatePlugin;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import com.angel.core.Wrap.FileConfigurationWrap;
import com.angel.core.util.ConfigurationUtil;
import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:50 2020/8/27
 * @Connection: ahacgn@gmail.com
 * @Description: 设置家
 */
@Command
public class SetHome extends Commands {

    @SneakyThrows
    @Override
    @PlayerOnly
    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String s, String[] strings) {
        Player player = (Player) commandSender;

        if(strings.length != 0) {
            player.sendMessage(ChatColor.RED + "参数错误!");
            return false;
        }

        ConfigurationUtil.get("configs/User.yml").set(player.getName(),player.getLocation()).save();
        player.sendMessage(ChatColor.GREEN+"成功设置家!");
        return true;
    }

    @Override
    public CommandWrap createCommand() {
        return CommandWrap.builder()
                .usage("/sethome")
                .name("sethome")
                .description("设置家")
                .permission("suda")
                .build();
    }
}
