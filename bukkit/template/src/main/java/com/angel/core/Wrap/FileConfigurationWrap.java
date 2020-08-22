package com.angel.core.Wrap;

import com.angel.core.TemplatePlugin;
import com.angel.core.util.ConfigurationUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;

/**
 * @Author: Angel_zou
 * @Date: Created in 13:56 2020/8/21
 * @Connection: ahacgn@gmail.com
 * @Description: 配置类的包装类
 */
public class FileConfigurationWrap {
    private File file;
    private String path;
    private FileConfiguration fileConfiguration;
    public FileConfigurationWrap(@NotNull File file,@NotNull String path){
        this.file = file;
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
        this.path = path;
    }

    public FileConfiguration getFileConfiguration(){
        return fileConfiguration;
    }

    public boolean contains(String key){
        return fileConfiguration.contains(key);
    }

    public <T> T get(String keyName) {
        System.out.println(fileConfiguration.getCurrentPath());
        return (T) fileConfiguration.get(keyName);
    }

    public <T> FileConfigurationWrap set(String key, T obj) {
        fileConfiguration.set(key,obj);
        return this;
    }

    public boolean save() throws IOException {
        fileConfiguration.save(file);
        file = new File(TemplatePlugin.getInstance().getDataFolder(),path);
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        ConfigurationUtil.reloadFileConfigurationWrap(path,this);
        return true;
    }

    public <T> T getConfigurationSection(String keyName) {
        return (T) fileConfiguration.getConfigurationSection(keyName);
    }
}
