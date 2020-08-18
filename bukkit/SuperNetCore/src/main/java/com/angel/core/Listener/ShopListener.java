package com.angel.core.Listener;

import com.angel.core.Configuration.GetConfiguration;
import com.angel.core.Entity.SignShop;
import com.angel.core.Entity.User;
import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.Exception.FailTranslationToIntException;
import com.angel.core.NetEvent.BuyEvent;
import com.angel.core.NetEvent.SellEvent;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import com.angel.core.dao.Configuration.Impl.ConfigurationHandlerImpl;
import com.angel.core.util.CheckUtil;
import com.angel.core.util.TranslationUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import java.io.IOException;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:36 2020/8/10
 * @Connection: ahacgn@gmail.com
 * @Description: 快捷商店监听器
 */
public class ShopListener implements Listener {
    @EventHandler
    public void onBuy(BuyEvent event) throws IOException, ConfigNotExistException {
        if(event.isCancel()){
            return;
        }
        long cost = event.getSignShop().getCost();
        Player player = event.getPlayer();
        ConfigurationHandler configurationHandler = new ConfigurationHandlerImpl("configs/user.yml");
        User user = configurationHandler.get(player.getName());
        if(user.getScore() > cost){
            if(event.give()){
                player.sendMessage("已购买!");
                user.setScore(user.getScore()-cost);
                configurationHandler.set(player.getName(),user).save();
            }
            player.sendMessage("当前剩余N点:" + String.valueOf(user.getScore()));
        }else{
            player.sendMessage("N点不足!");
        }
    }

    @EventHandler
    public void onSell(SellEvent event) throws FailTranslationToIntException, ConfigNotExistException, IOException {
        System.out.println("sellEvent");
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        System.out.println(inventory!= null);
        SignShop signShop = event.getSignShop();
        System.out.println(signShop.toString()+(signShop!=null));
        ItemStack itemStack;
        if(CheckUtil.isDigital(signShop.getId())){
            itemStack = TranslationUtil.idToItemStack(TranslationUtil.toInt(signShop.getId()));
        }else{
            Material material = Material.matchMaterial(signShop.getId());
            itemStack = new ItemStack(material);
        }

        System.out.println(itemStack !=null);
        System.out.println(itemStack.toString());


        int cnt = (int)signShop.getCnt();

        if(inventory.containsAtLeast(itemStack,cnt)){
            System.out.println("进入循环");
            for(int index = 0; index < 36; index++){
                if(inventory.getItem(index) == null){
                    continue;
                }
                ItemStack itemStack1 = inventory.getItem(index);
                if(itemStack1.isSimilar(itemStack)){
                    if(cnt - itemStack1.getAmount() >= 0){
                        cnt -= itemStack1.getAmount();
                        System.out.println(cnt);
                        itemStack1.setAmount(0);
                        inventory.setItem(index,itemStack1);
                        System.out.println("设置完毕");

                    }else{
                        itemStack1.setAmount(itemStack1.getAmount()-cnt);
                        inventory.setItem(index,itemStack1);
                        System.out.println(cnt + "...");
                        break;
                    }
                }
            }
            System.out.println("已经扣除完毕!");
            User user = GetConfiguration.get("configs/user.yml").get(player.getName());
            user.setScore(user.getScore() + signShop.getCost());
            GetConfiguration.get("configs/user.yml").set(player.getName(),user).save();
            player.sendMessage("当前剩余N点:" + String.valueOf(user.getScore()));

        }else{
            player.sendMessage("数量不足，无法出售!");
        }
    }


}
