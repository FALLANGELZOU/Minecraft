package com.angel.core.Listener;

import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.Main;
import com.angel.core.NetEvent.BookScriptEvent;
import com.angel.core.util.SimplifyUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:19 2020/8/15
 * @Connection: ahacgn@gmail.com
 * @Description: 书本脚本监听器
 */
public class BookScriptListener implements Listener {

    @EventHandler
    public void onBookScript(BookScriptEvent event){

        if(event.getEvent().getClickedBlock() != null) {
            event.getEvent().getPlayer().sendMessage("点击了");
            //event.getEvent().getPlayer().sendMessage("点击坐标:" + event.getEvent().getClickedBlock().getLocation().toString());
        }
    }

}
