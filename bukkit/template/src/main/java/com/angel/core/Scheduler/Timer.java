package com.angel.core.Scheduler;

import com.angel.core.TemplatePlugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 15:07 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 定时器
 */
public class Timer{
    private TemplatePlugin plugin = TemplatePlugin.getInstance();
    private Task task;
    private int times;
    private long ticks;
    private boolean isDone = false;
    private BukkitTask bukkitTask;
    public Timer(@NotNull int times, @NotNull long ticks, @NotNull Task task){
        this.task = task;
        this.times = times;
        this.ticks = ticks;
        this.bukkitTask = new TaskRun().runTaskTimer(plugin,ticks,ticks);
    }

    public boolean isDone(){
        return isDone;
    }
    public int getTimes(){
        return times;
    }
    public boolean cancel(){
        bukkitTask.cancel();
        isDone = true;
        return true;
    }

    private class TaskRun extends BukkitRunnable{

        @Override
        public void run() {
            if(times > 0){
                times--;
            }

            task.run();

            if(times == 0){
                this.cancel();
                isDone = true;
            }



        }
    }


}
