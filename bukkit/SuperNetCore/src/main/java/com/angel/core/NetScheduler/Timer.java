package com.angel.core.NetScheduler;

import com.angel.core.NetEvent.SellEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: Angel_zou
 * @Date: Created in 23:25 2020/8/15
 * @Connection: ahacgn@gmail.com
 * @Description: 计时器
 */
public class Timer extends NetRunnable{
    JavaPlugin plugin;

    long ticks = 1L;
    int second;
    public Timer(JavaPlugin plugin, int second){
        this.plugin = plugin;
        this.second = second;
        this.ticks *= second;
    }

    public void start(){
        this.runTaskTimerAsynchronously(this.plugin,1,ticks);
    }

    public boolean isDone(){
        return (ticks == 0);
    }

    public long get() {
        return ticks;
    }

    @Override
    public void run() {
        System.out.println(ticks);
        ticks--;
        if(ticks==0){
            cancel();
        }
    }
}
