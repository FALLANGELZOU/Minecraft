package com.angel.core.Template;

import org.bukkit.plugin.java.JavaPlugin;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 13:50 2020/9/5
 * @Connection: ahacgn@gmail.com
 * @Description: 入口工厂
 */
public interface TemplateManager {
    static TemplateManager getInstance(@NotNull JavaPlugin plugin){
        return new TemplateManagerImpl(plugin);
    }
    public JavaPlugin getPlugin();
}
