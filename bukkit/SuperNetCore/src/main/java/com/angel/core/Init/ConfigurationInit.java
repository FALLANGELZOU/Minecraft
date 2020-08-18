package com.angel.core.Init;

import com.angel.core.Configuration.GetConfiguration;
import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.Main;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import com.angel.core.dao.Configuration.Impl.ConfigurationHandlerImpl;
import com.angel.core.dao.Configuration.Impl.DefaultConfigurationHandlerImpl;
import com.angel.core.util.ConstantUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:38 2020/8/9
 * @Connection: ahacgn@gmail.com
 * @Description: 配置初始化
 */
public class ConfigurationInit {
    private final String[] CONFIG =
            {
                "configs/user.yml",
                "configs/signCommand.yml",
                "configs/netShop.yml"

            };
    public ConfigurationInit() throws IOException, ConfigNotExistException {

        //  加载默认配置
        //System.out.println("加载默认配置文件");
        ConfigurationHandlerImpl configurationHandler = new ConfigurationHandlerImpl("config.yml");
        if(configurationHandler.getFileConfiguration() == null){
            //System.out.println("配置默认文件");
            Main.getInstance().saveDefaultConfig();    //  默认配置文件
        }

        //  初始化默认配置
        initDefaultConfig();



        //  加载自定义配置文件
        for (String path:CONFIG){
            saveAllConfig(path);
        }



    }

    private void saveAllConfig(String path) throws ConfigNotExistException {
        //System.out.println("加载"+path);
        ConfigurationHandlerImpl configurationHandler = new ConfigurationHandlerImpl(path);
        if(configurationHandler.getFileConfiguration() != null)return;
        //System.out.println("配置：" + path);
        Main.getInstance().saveResource(path,false);
    }

    private void initDefaultConfig() throws IOException, ConfigNotExistException {
        if(GetConfiguration.get("/").contains("signCommandStyle")){
            ConstantUtil.SIGN_COMMAND_MARK = GetConfiguration.get("/").get("signCommandStyle");
        }else {
            List<String> lines = Arrays.asList("[NetCommand]","[BUY]","[SELL]");
            GetConfiguration.get("/").set("signCommandStyle",lines).save();
            ConstantUtil.SIGN_COMMAND_MARK = lines;
        }
    }



}
