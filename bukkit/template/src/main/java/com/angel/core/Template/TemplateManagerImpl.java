package com.angel.core.Template;

import com.angel.core.Init.InitHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import javax.validation.constraints.NotNull;

/**
 * @Author: Angel_zou
 * @Date: Created in 13:58 2020/9/5
 * @Connection: ahacgn@gmail.com
 * @Description: 具体实现
 */
public class TemplateManagerImpl implements TemplateManager{
    private Reflections reflections = null;
    private static TemplateManager TM = null;
    private JavaPlugin plugin = null;
    public TemplateManagerImpl(@NotNull JavaPlugin plugin){
        this.plugin = plugin;
        init();
        TM = this;
    }

    public static TemplateManager getInstance(){
        return TM;
    }


    /**
     * 全局化
     */
    private void init(){
        reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackages(plugin.getClass().getPackage().getName())
                        .addScanners()
                        .addScanners(new SubTypesScanner()) // 添加子类扫描工具
                        .addScanners(new FieldAnnotationsScanner()) // 添加 属性注解扫描工具
                        .addScanners(new MethodAnnotationsScanner() ) // 添加 方法注解扫描工具
                        .addScanners(new MethodParameterScanner() ) // 添加方法参数扫描工具
        );
        new InitHandler();
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }
}
