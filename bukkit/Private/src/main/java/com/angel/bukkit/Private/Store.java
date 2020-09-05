package com.angel.bukkit.Private;

import com.angel.bukkit.Private.Wrap.DeathInformationWrap;
import com.angel.core.Annotation.Commands.PlayerOnly;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:24 2020/8/27
 * @Connection: ahacgn@gmail.com
 * @Description: 储存变量
 */
public class Store {
    private static Map<String,DeathInformationWrap> deathInformationWraps = new HashMap<>();

    public static boolean containsInDeathInformationWraps(Player player){
        return deathInformationWraps.containsKey(player.getName());
    }

    public static void addDeathInformationWrap(Player player){
        DeathInformationWrap deathInformationWrap = DeathInformationWrap.builder()
                .playerName(player.getName())
                .location(player.getLocation())
                .build();
        deathInformationWraps.put(player.getName(),deathInformationWrap);
    }

    public static DeathInformationWrap getDeathInformationWrap(Player player){
        if(containsInDeathInformationWraps(player)){
            return deathInformationWraps.get(player.getName());
        }

        return null;
    }
}
