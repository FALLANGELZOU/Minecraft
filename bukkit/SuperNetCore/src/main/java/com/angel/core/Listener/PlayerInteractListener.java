package com.angel.core.Listener;


import com.angel.core.Configuration.GetConfiguration;
import com.angel.core.Entity.SignCommand;
import com.angel.core.Entity.SignShop;
import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.Exception.FailTranslationToIntException;
import com.angel.core.Main;
import com.angel.core.NetEvent.BookScriptEvent;
import com.angel.core.NetEvent.BuyEvent;
import com.angel.core.NetEvent.SellEvent;
import com.angel.core.NetEvent.SignCommandEvent;
import com.angel.core.NetScheduler.DemoTask;
import com.angel.core.NetScheduler.Timer;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import com.angel.core.dao.Configuration.Impl.ConfigurationHandlerImpl;
import com.angel.core.util.CheckUtil;
import com.angel.core.util.ConstantUtil;
import com.angel.core.util.SimplifyUtil;
import com.angel.core.util.TranslationUtil;
import com.mojang.datafixers.types.templates.Check;
import com.mojang.datafixers.types.templates.List;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.concurrent.Future;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:50 2020/8/10
 * @Connection: ahacgn@gmail.com
 * @Description: 玩家交互
 */
public class PlayerInteractListener implements Listener {

    private static Timer timeTask = null;
    private void BookScriptTrigger(PlayerInteractEvent event){
        System.out.println("开始!");
        if(timeTask == null){
            System.out.println("创建！");
            timeTask =  new Timer(Main.getInstance(),5);
            timeTask.start();

        }else if(timeTask.isDone()){
            System.out.println("完成!");
            timeTask = new Timer(Main.getInstance(),5);
            timeTask.start();
        }else{
            System.out.println("冷却中!");
            event.getPlayer().sendMessage("魔法书冷却中!");
            event.setCancelled(true);
            return;
        }
        System.out.println("触发书本一次");
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) && playerInventory.getItemInOffHand().getType().equals(Material.WRITTEN_BOOK)) {

            player.sendMessage("取消打开!");
            event.setCancelled(true);
            SimplifyUtil.triggerEvent(new BookScriptEvent(event));
            return;
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) throws ConfigNotExistException {
        //new DemoTask(Main.getInstance()).runTask(Main.getInstance());
        //System.out.println("执行了一次点击事件");
        BookScriptTrigger(event);

        Player player = event.getPlayer();

        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){

            Block block = event.hasBlock()?event.getClickedBlock():null;
            //System.out.println(block.getType().toString());

            if(!CheckUtil.isNull(block)){
                    if(ConstantUtil.isInSignMaterials(block.getType())){
                        Location location = block.getLocation();
                        String key = TranslationUtil.locationToKey(location);
                        Sign sign = (Sign) block.getState();

                        if(ConstantUtil.isInSignCommandMark(sign.getLine(0))){

                            //  命令木牌
                            if(GetConfiguration.get("configs/signCommand.yml").contains(key)){
                                SignCommand signCommand = GetConfiguration.get("configs/signCommand.yml").get(key);
                                SimplifyUtil.triggerEvent(new SignCommandEvent(signCommand,player));
                            }else{

                                //  木牌商店
                                if(GetConfiguration.get("configs/netShop.yml").contains(key)){
                                    //System.out.println("存在");
                                    SignShop signShop = GetConfiguration.get("configs/netShop.yml").get(key);

                                    if(signShop.getType() == 1){
                                        //System.out.println("buy");
                                        BuyEvent buyEvent = new BuyEvent(signShop,player);
                                        Bukkit.getServer().getPluginManager().callEvent(buyEvent);
                                    }else if(signShop.getType() == 2){
                                        //System.out.println("sell");
                                        SimplifyUtil.triggerEvent(new SellEvent(signShop,player));
                                    }else{
                                        Main.getInstance().getLogger().info("未知商店类型!");
                                    }
                                }
                            }

                        }



                    }

            }
        }


    }


}
