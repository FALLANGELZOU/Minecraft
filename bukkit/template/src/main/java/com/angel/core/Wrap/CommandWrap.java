package com.angel.core.Wrap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:56 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 命令包裹类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandWrap {
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private String permission;
    private String permissionMessage;
    private TabCompleter tabCompleter;
    private String label;
}
