package com.angel.core.demo;

import com.angel.core.Scheduler.Task;
import lombok.Data;

/**
 * @Author: Angel_zou
 * @Date: Created in 18:55 2020/8/29
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
public class MyTask implements Task {
    @Override
    public void run() {
        System.out.println("执行了一次task");
    }
}
