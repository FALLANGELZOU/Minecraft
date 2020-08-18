package com.angel.core.util;

import com.angel.core.Entity.SignCommand;
import com.angel.core.Exception.FailTranslationToIntException;
import com.angel.core.NetEvent.SignCommandEvent;
import net.minecraft.server.v1_16_R1.Item;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Angel_zou
 * @Date: Created in 14:01 2020/8/10
 * @Connection: ahacgn@gmail.com
 * @Description: 把方块坐标转化成key
 */
public class TranslationUtil {
    //  把方块坐标转化成key
    public static String locationToKey(@NotNull Location location){
        long x = location.getBlockX();
        long y = location.getBlockY();
        long z = location.getBlockZ();
        String s_x = (x < 0 ? "_":"") + String.valueOf(Math.abs(x));
        String s_y = (y < 0 ? "_":"") + String.valueOf(Math.abs(y));
        String s_z = (z < 0 ? "_":"") + String.valueOf(Math.abs(z));

        String res = "loc" + s_x + s_y + s_z;
        return res;
    }


    public static int toInt(@NotNull String temp) throws FailTranslationToIntException {
        if(CheckUtil.isDigital(temp)){
            return Integer.parseInt( temp );
        }
        else{
            throw new FailTranslationToIntException("转换int失败");
        }

    }

    public static ItemStack idToItemStack(@NotNull int id){
        Item item = Item.getById(id);
        if(item == null){
            return null;
        }

        net.minecraft.server.v1_16_R1.ItemStack itemStack = new net.minecraft.server.v1_16_R1.ItemStack(item);

        ItemStack itemStack1 = CraftItemStack.asCraftMirror(itemStack);
        return itemStack1;
    }

    public static List<String> TranslateSignCommand(SignCommandEvent event){
        Player player = event.getPlayer();
        SignCommand signCommand = event.getSignCommand();

        List<String> commands = signCommand.getCmd();

        Pattern pattern = Pattern.compile("(&.+?)");

        for (int i = 0; i < commands.size(); i++){
            String v = commands.get(i);

            Matcher matcher = pattern.matcher(v);
            while (matcher.find()){
                String var = matcher.group(1);
                switch (var){
                    case "&p":
                        v = v.replaceFirst("&p",player.getName());
                        break;
                    case "&x":
                        v = v.replaceFirst("&x",String.valueOf(signCommand.getLocation().get(0)));
                        break;
                    case "&y":
                        v = v.replaceFirst("&y",String.valueOf(signCommand.getLocation().get(1)));
                        break;
                    case "&z":
                        v = v.replaceFirst("&z",String.valueOf(signCommand.getLocation().get(3)));
                        break;
                    default:
                        break;
                }

            }
            commands.set(i,v);
        }

        return commands;
    }

}

