package com.angel.bukkit.ChangeOurPosition;

import com.angel.core.Scheduler.Task;
import com.angel.core.Scheduler.Timer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: Angel_zou
 * @Date: Created in 18:02 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 储存各种变量
 */
public class Store {
    public static Map<String,Room> rooms = new HashMap<>();
    public static Map<String, Timer> timerMap = new HashMap<>();
    public static Map<Player,String> inGamePlayers = new HashMap<>();
    public static Map<Player,String> getInGamePlayers(){
        return inGamePlayers;
    }
    public static Map<String,Room> getRooms(){
        return rooms;
    }
    public static Map<String, Timer> getTimerMap(){
        return timerMap;
    }
    public static boolean addRoom(Room room){
        if(rooms.containsKey(room.getName())){
            return false;
        }else{
            rooms.put(room.getName(),room);
            task t = new task(room.getTime(),room);
            Timer timer = new Timer(-1,20L,t);
            timerMap.put(room.getName(),timer);
            return true;
        }
    }

    public static boolean deleteRoom(String roomName){
        if(rooms.containsKey(roomName)){
            rooms.remove(roomName);
            timerMap.get(roomName).cancel();
            timerMap.remove(roomName);
            return true;
        }
        return false;

    }

    private static class task implements Task {
        private Room room;
        private int record;
        private int min;
        private int second;
        public task(int min,Room room){
            this.record = min;
            this.min = min;
            this.second = 0;
            this.room = room;

        }
        @Override
        public void run() {
            if(second == 0){
                if(min != 0){
                    List<Player> players = room.getPlayers();
                    players.forEach(v->{
                        v.sendMessage(ChatColor.RED + "还有"+ String.valueOf(min)+"分钟!");
                    });
                }
                if(min == 0){
                    min = record;
                    second = 0;
                    room.shuffle();
                    return;
                }
                min--;
                second = 59;
            }else{
                second--;
            }

            if(min == 0 && second <= 10){
                List<Player> players = room.getPlayers();
                players.forEach(v->{
                    v.sendMessage(ChatColor.RED + "还有" + second+"秒!");
                });
            }

        }
    }
}
