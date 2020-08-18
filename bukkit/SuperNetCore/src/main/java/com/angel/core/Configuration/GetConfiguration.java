package com.angel.core.Configuration;

import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import com.angel.core.dao.Configuration.Impl.ConfigurationHandlerImpl;
import com.angel.core.dao.Configuration.Impl.DefaultConfigurationHandlerImpl;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:18 2020/8/13
 * @Connection: ahacgn@gmail.com
 * @Description: 获得配置实例
 */
public class GetConfiguration {
    private static Map<String, ConfigurationHandler> configMap = new HashMap<>();

    public static ConfigurationHandler get(@NotNull String path) throws ConfigNotExistException {
        if(configMap.containsKey(path)){
            return configMap.get(path);
        }else{
            ConfigurationHandler configurationHandler;
            if(path.equals("/")){
                configurationHandler = new DefaultConfigurationHandlerImpl();
                configMap.put("/",configurationHandler);
            }else{
                configurationHandler = new ConfigurationHandlerImpl(path);
                configMap.put(path,configurationHandler);
            }
            return configurationHandler;
        }

    }

    public static void reload(@NotNull String path) throws ConfigNotExistException{
        ConfigurationHandler configurationHandler;
        if(path.equals("/")){
            configurationHandler = new DefaultConfigurationHandlerImpl();
            configMap.put("/",configurationHandler);
        }else{
            configurationHandler = new ConfigurationHandlerImpl(path);
            configMap.put(path,configurationHandler);
        }
    }



}
