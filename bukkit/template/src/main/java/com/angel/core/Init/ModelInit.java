package com.angel.core.Init;

import com.angel.core.Annotation.Entity;
import com.angel.core.Command.CommandHandler;
import com.angel.core.Config.ConfigHandler;
import com.angel.core.Entity.EntityHandler;
import com.angel.core.Event.EventHandler;
import com.angel.core.Listener.ListenerHandler;
import lombok.Data;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:52 2020/8/19
 * @Connection: ahacgn@gmail.com
 * @Description: 模块初始化
 */
@Data
public class ModelInit {
    public static EntityHandler entityHandler;
    public static ListenerHandler listenerHandler;
    public static ConfigHandler configHandler;
    public static EventHandler eventHandler;
    public static CommandHandler commandHandler;
    public ModelInit(){
        entityHandler = new EntityHandler();    //  实体类处理器
        listenerHandler = new ListenerHandler();  //  监听模块处理器
        configHandler = new ConfigHandler();    //  配置模块处理器
        eventHandler = new EventHandler();     //  事件模块处理器
        commandHandler = new CommandHandler();   //  命令模块处理器
    }

}
