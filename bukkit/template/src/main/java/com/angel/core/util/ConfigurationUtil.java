package com.angel.core.util;

import com.angel.core.Wrap.FileConfigurationWrap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 13:42 2020/8/21
 * @Connection: ahacgn@gmail.com
 * @Description: 配置文件工具类
 */
public class ConfigurationUtil implements Util{
    private static Map<String, FileConfigurationWrap> fileConfigurationWrapMap = new HashMap<>();
    public static boolean addFileConfigurationWrap(@NotNull String key,@NotNull FileConfigurationWrap fileConfiguration){
        if(!fileConfigurationWrapMap.containsKey(key)){
            fileConfigurationWrapMap.put(key,fileConfiguration);
            return true;
        }
        return false;

    }
    public static boolean contains(@NotNull String key){
        return fileConfigurationWrapMap.containsKey(key);
    }
    public static boolean reloadFileConfigurationWrap(@NotNull String key, @NotNull FileConfigurationWrap fileConfigurationWrap){
        if(fileConfigurationWrapMap.containsKey(key)){
            fileConfigurationWrapMap.put(key,fileConfigurationWrap);
            return true;
        }
        return false;

    }
    public static FileConfigurationWrap get(@NotNull String key){
        if(contains(key)){
            return fileConfigurationWrapMap.get(key);
        }else{
            return null;
        }
    }
}
