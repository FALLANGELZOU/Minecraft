package com.angel.core;

import com.angel.core.Init.InitHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

/**
 * @Author: Angel_zou
 * @Date: Created in 15:40 2020/8/19
 * @Connection: ahacgn@gmail.com
 * @Description: 入口文件
 */
public abstract class TemplatePlugin extends JavaPlugin{
    private static TemplatePlugin GLOBAL;
    private Reflections reflections;

    public Reflections getReflections(){
        return reflections;
    }

    public static TemplatePlugin getInstance(){
        return GLOBAL;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        GLOBAL = this;
        this.reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackages(this.getClass().getPackage().getName())
                        .addScanners()
                        .addScanners(new SubTypesScanner()) // 添加子类扫描工具
                        .addScanners(new FieldAnnotationsScanner()) // 添加 属性注解扫描工具
                        .addScanners(new MethodAnnotationsScanner() ) // 添加 方法注解扫描工具
                        .addScanners(new MethodParameterScanner() ) // 添加方法参数扫描工具
        );
        new InitHandler();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

}
