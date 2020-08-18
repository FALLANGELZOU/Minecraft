package com.angel.core.dao.Configuration.Impl;

import com.angel.core.Configuration.GetConfiguration;
import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.Main;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import lombok.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:45 2020/8/8
 * @Connection: ahacgn@gmail.com
 * @Description: user配置数据操作类
 */
@Data
public class ConfigurationHandlerImpl implements ConfigurationHandler {
    private File file;
    private FileConfiguration fileConfiguration = null;
    private String path;
    public ConfigurationHandlerImpl(String path) throws ConfigNotExistException {
        //System.out.println("加载文件"+path);
        file = new File(Main.getInstance().getDataFolder(),path);
        if(file.exists() && file != null){
            //System.out.println("文件存在，加载配置文件");
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
            this.path = path;
        }else{
            Main.getInstance().getLogger().info("没有"+path+"配置文件!");
            throw new ConfigNotExistException("没有此配置文件!");
        }



    }

    @Override
    public boolean contains(String key) {
        return fileConfiguration.contains(key);
    }

    @Override
    public FileConfiguration NetGetConfig() {
        return fileConfiguration;
    }

    @Override
    public <T> T get(String keyName) {
        System.out.println(fileConfiguration.getCurrentPath());
        return (T) fileConfiguration.get(keyName);
    }

    @Override
    public <T> ConfigurationHandler set(String key, T obj) {
        fileConfiguration.set(key,obj);
        return this;
    }

    @Override
    public boolean save() throws IOException, ConfigNotExistException {
        if(fileConfiguration == null)return false;

        fileConfiguration.save(file);
        GetConfiguration.reload(this.path);
        return true;
    }

    @Override
    public <T> T getConfigurationSection(String keyName) {
        return (T) fileConfiguration.getConfigurationSection(keyName);

    }
    @Override
    public List<?> getList(String keyName){
        return fileConfiguration.getList(keyName);
    }
}
