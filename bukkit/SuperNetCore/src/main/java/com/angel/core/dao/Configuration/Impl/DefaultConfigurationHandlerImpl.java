package com.angel.core.dao.Configuration.Impl;

import com.angel.core.Configuration.GetConfiguration;
import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.Main;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:50 2020/8/8
 * @Connection: ahacgn@gmail.com
 * @Description: 默认配置文件操作类
 */
public class DefaultConfigurationHandlerImpl implements ConfigurationHandler {
    private FileConfiguration fileConfiguration;
    public DefaultConfigurationHandlerImpl(){
        this.fileConfiguration = Main.getInstance().getConfig();
    }

    @Override
    public boolean contains(String key) {
        return  fileConfiguration.contains(key);
    }

    @Override
    public FileConfiguration NetGetConfig() {
        return fileConfiguration;
    }

    @Override
    public <T> T get(String keyName) {
        if(fileConfiguration.contains(keyName)){
            return (T) fileConfiguration.get(keyName);
        }
        return null;
    }

    @Override
    public <T> DefaultConfigurationHandlerImpl set(String key, T obj) {
        fileConfiguration.set(key,obj);
        return this;
    }

    @Override
    public boolean save() throws ConfigNotExistException {
        Main.getInstance().saveConfig();

            GetConfiguration.reload("/");


        return true;
    }

    @Override
    public <T> T getConfigurationSection(String keyName) {
        return (T) fileConfiguration.getConfigurationSection(keyName);
    }

    @Override
    public List<?> getList(String keyName) {
        return fileConfiguration.getList(keyName);
    }
}
