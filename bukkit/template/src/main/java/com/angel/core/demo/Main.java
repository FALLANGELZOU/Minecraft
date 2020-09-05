package com.angel.core.demo;

import com.angel.core.Annotation.JsonConfiguration;
import com.angel.core.Scheduler.Task;
import com.angel.core.Scheduler.Timer;
import com.angel.core.TemplatePlugin;
import com.angel.core.Wrap.CommandWrap;
import com.angel.core.Wrap.FileConfigurationWrap;
import com.angel.core.util.ConfigurationUtil;
import com.angel.core.util.EventUtil;
import com.angel.core.util.JsonConfigurationUtil;
import lombok.SneakyThrows;

/**
 * @Author: Angel_zou
 * @Date: Created in 17:44 2020/8/29
 * @Connection: ahacgn@gmail.com
 * @Description: 入口文件
 */
public class Main extends TemplatePlugin {
    @SneakyThrows
    @Override
    public void onEnable() {
        super.onEnable();
        CommandWrap commandWrap = CommandWrap
                .builder()
                .name("test")
                .permission("suda")
                .description("测试")
                .usage("/test")
                .build();

        JsonConfigurationUtil.get("configs/demo.json")
                .set("command",commandWrap)
                .set("string","str")
                .set("int",1)
                .save();

        CommandWrap commandWrap1 =  JsonConfigurationUtil.get("configs/demo.json").get("command");
        System.out.println(commandWrap1.getName());

    }
}
