package com.angel.core.dao.Configuration;

import com.angel.core.Exception.ConfigNotExistException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:19 2020/8/8
 * @Connection: ahacgn@gmail.com
 * @Description: 配置处理类接口
 */
public interface ConfigurationHandler {
    boolean contains(String key);
    FileConfiguration NetGetConfig();
    <T> T get(String keyName);
    <T> ConfigurationHandler set(String key,T obj);
    boolean save() throws IOException, ConfigNotExistException;
    <T> T getConfigurationSection(String keyName);
    List<?> getList(String keyName);
}
