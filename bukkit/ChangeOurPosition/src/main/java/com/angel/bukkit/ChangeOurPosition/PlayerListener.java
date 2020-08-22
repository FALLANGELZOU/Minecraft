package com.angel.bukkit.ChangeOurPosition;

import com.angel.core.Annotation.Listener;
import com.angel.core.Listener.Listeners;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @Author: Angel_zou
 * @Date: Created in 19:55 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 玩家监听器
 */
@Listener
public class PlayerListener extends Listeners {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        if(Store.getInGamePlayers().containsKey(player)){
            player.sendMessage(ChatColor.RED + "您已死亡，失败!");
            String roomName = Store.getInGamePlayers().get(player);
            Room room = Store.getRooms().get(roomName);
            room.getPlayers().remove(player);

            room.getPlayers().forEach(v->{
                v.sendMessage(ChatColor.RED + "玩家"+ player.getName()+"死亡!");
                v.sendMessage(ChatColor.GREEN + "当前剩下"+room.getPlayers().size()+"人");
            });

            Store.getInGamePlayers().remove(player);

            if(room.getPlayers().size() == 1){
                Player player1 = room.getPlayers().get(0);
                Main.getInstance().getServer().broadcastMessage(ChatColor.GOLD+"玩家"+player1.getName()+"在"+roomName+"房间取得胜利!");
                Store.getInGamePlayers().remove(player1);
                Store.deleteRoom(roomName);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(Store.getInGamePlayers().containsKey(player)){

            String roomName = Store.getInGamePlayers().get(player);
            Room room = Store.getRooms().get(roomName);
            room.getPlayers().remove(player);

            room.getPlayers().forEach(v->{
                v.sendMessage(ChatColor.RED + "玩家"+ player.getName()+"退出游戏!");
                v.sendMessage(ChatColor.GREEN + "当前剩下"+room.getPlayers().size()+"人");
            });

            Store.getInGamePlayers().remove(player);

            if(room.getPlayers().size() == 1){
                Player player1 = room.getPlayers().get(0);
                Main.getInstance().getServer().broadcastMessage(ChatColor.GOLD+"玩家"+player1.getName()+"在"+roomName+"房间取得胜利!");
                Store.getInGamePlayers().remove(player1);
                Store.deleteRoom(roomName);
            }

        }
    }
}
