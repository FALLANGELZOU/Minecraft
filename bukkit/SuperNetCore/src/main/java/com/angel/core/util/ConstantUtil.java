package com.angel.core.util;

import lombok.Data;
import org.bukkit.Material;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:50 2020/8/13
 * @Connection: ahacgn@gmail.com
 * @Description: 常量初始化
 */
@Data
public class ConstantUtil {
    public static Material[] SIGN_MATERIALS = {
            Material.ACACIA_SIGN,
            Material.ACACIA_WALL_SIGN,
            Material.SPRUCE_SIGN,
            Material.SPRUCE_WALL_SIGN,
            Material.BIRCH_SIGN,
            Material.BIRCH_WALL_SIGN,
            Material.CRIMSON_SIGN,
            Material.CRIMSON_WALL_SIGN,
            Material.DARK_OAK_SIGN,
            Material.DARK_OAK_WALL_SIGN,
            Material.JUNGLE_SIGN,
            Material.JUNGLE_WALL_SIGN,
            Material.OAK_SIGN,
            Material.OAK_WALL_SIGN,
            Material.WARPED_SIGN,
            Material.WARPED_WALL_SIGN
    };
    public static List<String> CommandVariables = Arrays.asList("&p","&x","&y","&z");

    public static List<String> ROLES = Arrays.asList("USER","OP","ROOT");
    public static List<String> SIGN_COMMAND_MARK = Arrays.asList("[NetCommand]","[BUY]","[SELL]");

    public static boolean isInSignMaterials(@NotNull  Material type){
        for (Material material:SIGN_MATERIALS){
            if(material.equals(type)){
                return true;
            }
        }
        return false;
    }

    public static boolean isInSignCommandMark(@NotNull String commandName){
        return SIGN_COMMAND_MARK.contains(commandName);
    }

}
