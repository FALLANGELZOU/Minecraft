package com.angel.core.Listener;

import com.angel.core.Entity.User;
import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.NetEvent.SignCommandBreakEvent;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import com.angel.core.dao.Configuration.Impl.ConfigurationHandlerImpl;
import com.angel.core.util.ConstantUtil;
import com.angel.core.util.SimplifyUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 21:32 2020/8/7
 * @Connection: ahacgn@gmail.com
 * @Description: 用户监听器
 */
public class UserListener implements Listener {
    private List<String> userList = new ArrayList<>();
    private final List<String> ROLES = Arrays.asList("USER");
    @EventHandler
    public void onUserBreakBlock(BlockBreakEvent event){
        Material material = event.getBlock().getType();
        System.out.println("material:" + material.toString());

        if(ConstantUtil.isInSignMaterials(material)){
            BlockState blockState =  event.getBlock().getState();
            Sign sign = (Sign)blockState;
            //System.out.println("block:"+sign.toString());
            //System.out.println("line:"+sign.getLine(0));
            if(ConstantUtil.isInSignCommandMark(sign.getLine(0))){
                if(event.getPlayer() == null){
                    event.setCancelled(true);
                    return;
                }
                if(sign.getLine(0).equals(ConstantUtil.SIGN_COMMAND_MARK.get(0))){
                    SimplifyUtil.triggerEvent(new SignCommandBreakEvent(event,1));
                }
                else
                {
                    SimplifyUtil.triggerEvent(new SignCommandBreakEvent(event,2));
                }
            }
        }
    }
    @EventHandler
    public void onUserJoin(PlayerJoinEvent event) throws IOException, ConfigNotExistException {
        Player player = event.getPlayer();
        String username = event.getPlayer().getName();
        if (!userList.contains(username)){
            userList.add(username);

        }

        event.setJoinMessage("[Message] " + username + " has joined game!");
        //  尚未国际化

        //  储存user信息
        ConfigurationHandler configurationHandler = new ConfigurationHandlerImpl("configs/user.yml");
        if(!configurationHandler.contains(username)){

            User user = new User(username,player.getUniqueId().toString(),null,ROLES,0);
            configurationHandler.set(username,user).save();
        }

    }

    @EventHandler
    public void onUserQuit(PlayerQuitEvent event){
        if (!userList.contains(event.getPlayer().getName())){
            userList.remove(event.getPlayer().getName());
        }
        event.setQuitMessage("[Message] " + event.getPlayer().getName() + " has quited game!");


    }

    @EventHandler
    public void onUserDeath(PlayerDeathEvent event) throws IOException, ConfigNotExistException {
        Player player = event.getEntity();
        ConfigurationHandler configurationHandler = new ConfigurationHandlerImpl("configs/user.yml");
        User user = configurationHandler.get(player.getName());
        if(user != null){
            long score = user.getScore();
            //  未添加配置文件
            score = ((score-1000) > 0)?(score-1000):0;
            user.setScore(score);
            configurationHandler.set(player.getName(),user).save();
            player.sendMessage("您已死亡！扣除1000 N点,当前剩余:" + score);
        }
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) throws IOException, ConfigNotExistException {
        LivingEntity le = event.getEntity();
        Player player = le.getKiller();

        if(player == null)return;

        ConfigurationHandler configurationHandler = new ConfigurationHandlerImpl("configs/user.yml");
        User user = configurationHandler.get(player.getName());

        if(user == null)return;

        long score = user.getScore();
        if(le.getType() == EntityType.PLAYER){
            user.setScore(score + 1000);
        }else{
            user.setScore(score + 200);
        }

        configurationHandler.set(player.getName(),user).save();
    }
}
