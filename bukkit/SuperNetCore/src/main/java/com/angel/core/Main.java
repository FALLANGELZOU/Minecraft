package com.angel.core;

import com.angel.core.Entity.User;
import com.angel.core.Init.ConfigurationInit;
import com.angel.core.Init.RegisterSerializeObjInit;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import com.angel.core.dao.Configuration.Impl.DefaultConfigurationHandlerImpl;
import com.mojang.datafixers.types.Type;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 17:58 2020/8/7
 * @Connection: ahacgn@gmail.com
 * @Description: 入口文件
 */
public class Main extends JavaPlugin {

    private static Main GLOBAL;

    //  获得运行实例
    public static Main getInstance(){
        return GLOBAL;
    }
    @SneakyThrows
    @Override
    public void onEnable() {
        //  获得主进程实例
        GLOBAL = this;

        //  注册所有要序列化对象
        new RegisterSerializeObjInit();
        //  registerSerializeObj();

        //  注册监听器
        ListenerHandler();

        //  初始化所有配置文件
        //System.out.println("初始化所有配置文件");
        new ConfigurationInit();




        //  加载成功
        this.getLogger().info("\033[1;32m**********\033[0m");
        this.getLogger().info("\033[1;32mSuperNetCore is running!\033[0m");
        this.getLogger().info("\033[1;32m**********\033[0m");


    }

    @Override
    public void onDisable() {
        super.onDisable();
        Bukkit.getScheduler().cancelTasks(this);    //关闭所有线程任务
    }

//    //  注册所有配置文件
//    private void registerConfiguration(){
//        saveDefaultConfig();    //  默认配置文件
//        saveResource("configs/user.yml",false);
//    }

    //  实体类名称
    private void registerSerializeObj(){
        ConfigurationSerialization.registerClass(User.class);
    }

    //  监听器名称,与类名相同
    private final List<String> listeners = Arrays.asList(
            "UserListener",
            "SignCommandListener",
            "PlayerInteractListener",
            "ShopListener",
            "BookScriptListener"
    );

    private void ListenerHandler() throws ClassNotFoundException {
        for (String listener:listeners){

            Class c = Class.forName("com.angel.core.Listener." + listener);
            try {
                Listener listenerInstance = (Listener) c.newInstance();
                Bukkit.getPluginManager().registerEvents(listenerInstance,this);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
