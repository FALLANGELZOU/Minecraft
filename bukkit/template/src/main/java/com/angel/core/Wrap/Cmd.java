package com.angel.core.Wrap;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.command.Command;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:46 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: command的包装类，避免命名冲突
 */
@Data
@AllArgsConstructor
public class Cmd {
    private Command command;
}
