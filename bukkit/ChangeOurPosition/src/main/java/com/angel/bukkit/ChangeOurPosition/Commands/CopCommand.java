package com.angel.bukkit.ChangeOurPosition.Commands;

import com.angel.bukkit.ChangeOurPosition.Main;
import com.angel.bukkit.ChangeOurPosition.Room;
import com.angel.bukkit.ChangeOurPosition.Store;
import com.angel.core.Annotation.Command;
import com.angel.core.Command.Commands;
import com.angel.core.Wrap.Cmd;
import com.angel.core.Wrap.CommandWrap;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:49 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 注册命令
 */

@Command
public class CopCommand extends Commands{

    public static boolean isDigital(String temp){
        if(temp == null||temp.equals("")||temp.isEmpty()) return false;
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(temp);
        return matcher.matches();
    }

    public boolean ProcessingCommand(CommandSender commandSender, Cmd cmd, String s, String[] strings) {
        if(strings.length == 0){
            commandSender.sendMessage(ChatColor.RED+"参数错误!");
            return false;
        }
        if(strings[0].equals("list")){
            commandSender.sendMessage(Store.getRooms().toString());
        }
        if(strings[0].equals("add")){
            if(commandSender instanceof Player){
                Player player = (Player) commandSender;
                if(!player.hasPermission("cop.add")){
                    player.sendMessage(ChatColor.RED+"你没有权限创建房间!");
                    return true;
                }

            }
            if(strings.length != 3){
                commandSender.sendMessage(ChatColor.RED+"参数错误!");
                return false;
            }

            if(!(isDigital(strings[2])) || strings[2].equals("0")){
                commandSender.sendMessage(ChatColor.RED+"间隔时间不是正整数!");
                return false;
            }
            int time = Integer.parseInt( strings[2] );
            Room room = new Room(strings[1],time);
            if(Store.addRoom(room)){
                commandSender.sendMessage(ChatColor.GREEN+"成功添加房间, 房间名称为: "+ strings[1]+" , 间隔时间为 "+ strings[2] + " 分钟");
            }else{
                commandSender.sendMessage(ChatColor.RED+"已存在相同房间!");
            }

        }


        if (strings[0].equals("join")){
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage("非玩家无法使用!");
            }
            Player player = (Player) commandSender;

            if(strings.length != 2){
                commandSender.sendMessage(ChatColor.RED+"参数错误!");
                return false;
            }
            if(Store.getInGamePlayers().containsKey(player)){
                player.sendMessage(ChatColor.GREEN+"您已在游戏中!");
                return true;
            }

            if(Store.getRooms().containsKey(strings[1])){
                Room room = Store.getRooms().get(strings[1]);
                if(room.addPlayer(player)){
                    player.sendMessage(ChatColor.GREEN+"已加入"+room.getName()+"房间!");
                    player.sendMessage(ChatColor.GREEN+"当前房间有" + room.getPlayers().size() + "人");
                    Store.getInGamePlayers().put(player,room.getName());
                }


            }else{
                commandSender.sendMessage(ChatColor.RED+"不存在此房间!");
            }
            return true;
        }


        if(strings[0].equals("quit")){
            if(strings.length != 1) {
                commandSender.sendMessage(ChatColor.RED+"参数错误!");
                return false;
            }
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage("非玩家无法使用!");
                return true;
            }
            Player player = (Player) commandSender;

            if(Store.getInGamePlayers().containsKey(player)){
                String roomName = Store.getInGamePlayers().get(player);
                Store.getInGamePlayers().remove(player);
                Store.getRooms().get(roomName).getPlayers().remove(player);
                player.sendMessage(ChatColor.GREEN+"您已退出" + roomName + "房间!");

                Room room = Store.getRooms().get(roomName);

                room.getPlayers().forEach(v->{
                    v.sendMessage(ChatColor.RED + "玩家"+ player.getName()+"退出游戏!");
                    v.sendMessage(ChatColor.GREEN + "当前剩下"+room.getPlayers().size()+"人");
                });


                if(room.getPlayers().size() == 1){
                    Player player1 = room.getPlayers().get(0);
                    Main.getInstance().getServer().broadcastMessage(ChatColor.GOLD+"玩家"+player1.getName()+"在"+roomName+"房间取得胜利!");
                    Store.getInGamePlayers().remove(player1);
                    Store.deleteRoom(roomName);
                }

                if(room.getPlayers().size() == 0){
                    Store.deleteRoom(roomName);
                }


            }else{
                player.sendMessage(ChatColor.RED+"不在游戏中!");
            }


        }



        return true;
    }
    public CommandWrap createCommand() {
        return CommandWrap.builder()
                .name("cop")
                .description("change our position 指令")
                .permission("cop.basic")
                .usage(ChatColor.GREEN+"/cop add roomName minute\n" +
                        ChatColor.GREEN+"/cop join roomName\n" +
                        ChatColor.GREEN+"/cop list\n" +
                        ChatColor.GREEN+"/cop quit")
                .permissionMessage(ChatColor.RED+"你没有权限游玩此游戏!")
                .build();
    }
}
