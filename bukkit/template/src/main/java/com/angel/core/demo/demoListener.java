package com.angel.core.demo;

import com.angel.core.Annotation.Event;
import com.angel.core.Annotation.Listener;
import com.angel.core.Event.Events;
import com.angel.core.Listener.Listeners;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @Author: Angel_zou
 * @Date: Created in 18:48 2020/8/29
 * @Connection: ahacgn@gmail.com
 * @Description:
 */

@Listener
public class demoListener extends Listeners {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

    }
}
