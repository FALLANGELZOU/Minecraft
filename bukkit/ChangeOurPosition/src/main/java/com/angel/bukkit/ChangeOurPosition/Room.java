package com.angel.bukkit.ChangeOurPosition;

import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @Author: Angel_zou
 * @Date: Created in 18:05 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 游戏房间类
 */
@Data
public class Room {
    private List<Player> players;
    private String name;
    private Boolean isDone = false;
    private int time;

    public Room(String name, int time){
        this.name = name;
        players = new ArrayList<>();
        this.time = time;

    }
    public boolean addPlayer(Player player){
        if(players.contains(player)){
            player.sendMessage(ChatColor.GREEN+"你已经在此房间中!");
            return false;
        }else{
            players.add(player);
            //player.sendMessage(ChatColor.GREEN+"成功加入房间!");
            players.forEach(v->{
                v.sendMessage(ChatColor.GREEN+"玩家"+player.getName()+"已加入游戏!");
                v.sendMessage(ChatColor.GREEN+"当前剩余人数:" + players.size()+"人");
            });
            return true;
        }
    }
    public void shuffle(){
        if(players.size() == 0){
            Store.deleteRoom(this.name);
            return;
        }
        if (players.size() == 1){
            return;
        }
        List<Location> locations = new ArrayList<>();
        players.forEach(v->{
            locations.add(v.getLocation());
        });
        List<Location> temp = new ArrayList<>(locations);

        while (temp.equals(locations)){Collections.shuffle(temp);}

        for(int i = 0; i < players.size(); i++){
            players.get(i).teleport(temp.get(i));
        }
    }
}
