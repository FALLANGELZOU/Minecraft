package com.angel.bukkit.Private.Listener;

import com.angel.bukkit.Private.Entitys.User;
import com.angel.bukkit.Private.Store;
import com.angel.core.Annotation.Listener;
import com.angel.core.Listener.Listeners;
import com.angel.core.TemplatePlugin;
import com.angel.core.util.ConfigurationUtil;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:15 2020/8/27
 * @Connection: ahacgn@gmail.com
 * @Description: 玩家监听器
 */
@Listener
public class PlayerListener extends Listeners {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        event.setJoinMessage("§a§l欢迎"+event.getPlayer().getName()+"加入SUDA Private Server !");

        Player player = event.getPlayer();
        if(!ConfigurationUtil.get("configs/User.yml").contains(player.getName())){


            ConfigurationUtil.get("configs/User.yml").set(player.getName(),player.getLocation()).save();
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){

        Store.addDeathInformationWrap(event.getEntity());

        Player player = event.getEntity();
        World world = player.getWorld();

        //System.out.println(player.getTotalExperience());
        if (!event.getKeepInventory()){

            event.setKeepInventory(true);

            if (player.getLevel() > 20){
                event.setNewLevel(player.getLevel()-20);
            }else {
                Random random = new Random();
                ItemStack itemStack = new ItemStack(Material.AIR);
                PlayerInventory playerInventory = player.getInventory();
                for (int i = 0; i < 10 ; i++){
                    int index = Math.abs(random.nextInt()%41);

                    playerInventory.setItem(index, itemStack);
                }
            }
        }

    }
}
