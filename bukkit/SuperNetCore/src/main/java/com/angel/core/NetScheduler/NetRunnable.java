package com.angel.core.NetScheduler;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * @Author: Angel_zou
 * @Date: Created in 13:40 2020/8/15
 * @Connection: ahacgn@gmail.com
 * @Description: Runnable祖类
 */
public abstract class NetRunnable extends BukkitRunnable {
    @Override
    public abstract void run();

}
