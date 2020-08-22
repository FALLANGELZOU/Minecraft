package com.angel.core.Config;

import com.angel.core.Annotation.Configuration;
import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.TemplatePlugin;
import com.angel.core.Wrap.FileConfigurationWrap;
import com.angel.core.util.ConfigurationUtil;
import com.angel.core.util.ReflectionsUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:40 2020/8/19
 * @Connection: ahacgn@gmail.com
 * @Description: 配置处理类
 */
public class ConfigHandler {
    private static Set<Class<?>> configList;
    public static Set<Class<?>> getConfigList(){
        return configList;
    }
    public Set<Class<?>> getConfigs(){
        return configList;
    }
    public ConfigHandler(){
        configList = ReflectionsUtil.getTypesAnnotatedWith(Configuration.class);
        //TemplatePlugin.getInstance().saveDefaultConfig();
        check();
    }


    private void check(){
        getConfigList().forEach( v->{
            String path = v.getAnnotation(Configuration.class).value();
            //System.out.println(path);
            File file = new File(TemplatePlugin.getInstance().getDataFolder(),path);
            if(!(file.exists() && file != null)){
                TemplatePlugin.getInstance().saveResource(path,false);
                file = new File(TemplatePlugin.getInstance().getDataFolder(),path);
            }
            FileConfigurationWrap fileConfigurationWrap = new FileConfigurationWrap(file,path);
            ConfigurationUtil.addFileConfigurationWrap(path,fileConfigurationWrap);

        });
    }
}
