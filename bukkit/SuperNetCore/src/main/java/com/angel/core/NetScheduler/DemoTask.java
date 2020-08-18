package com.angel.core.NetScheduler;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @Author: Angel_zou
 * @Date: Created in 13:48 2020/8/15
 * @Connection: ahacgn@gmail.com
 * @Description: demo
 */
public class DemoTask extends NetRunnable{
    private JavaPlugin plugin;
    public DemoTask(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getServer().broadcastMessage("hello");
    }
}
