package com.angel.core.Listener;

import com.angel.core.Configuration.GetConfiguration;
import com.angel.core.Entity.SignCommand;
import com.angel.core.Entity.SignShop;
import com.angel.core.Exception.ConfigNotExistException;
import com.angel.core.Exception.FailTranslationToIntException;
import com.angel.core.Main;
import com.angel.core.NetEvent.SignCommandBreakEvent;
import com.angel.core.NetEvent.SignCommandBuildEvent;
import com.angel.core.NetEvent.SignCommandEvent;
import com.angel.core.dao.Configuration.ConfigurationHandler;
import com.angel.core.dao.Configuration.Impl.ConfigurationHandlerImpl;
import com.angel.core.dao.Configuration.Impl.DefaultConfigurationHandlerImpl;
import com.angel.core.util.CheckUtil;
import com.angel.core.util.TranslationUtil;
import net.minecraft.server.v1_16_R1.IRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: Angel_zou
 * @Date: Created in 0:51 2020/8/9
 * @Connection: ahacgn@gmail.com
 * @Description: 木牌指令监听器
 */
public class SignCommandListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event){
       // System.out.println("触发SignBuild");
        //  触发SignCommentBuildEvent
        Bukkit.getServer().getPluginManager().callEvent(new SignCommandBuildEvent(event));
    }

    @EventHandler
    public void onSignCommandTrigger(SignCommandEvent event){
        List<String> commands = TranslationUtil.TranslateSignCommand(event);
        //  超级指令
        if(event.getSignCommand().getType() == 1){
            commands.forEach(v->{
                Main.getInstance().getServer().dispatchCommand(Main.getInstance().getServer().getConsoleSender(),v);
            });
        }else{
            commands.forEach(v->{
                event.getPlayer().chat(v);
            });
        }
    }

    @EventHandler
    public void onSignCommandBreak(@NotNull SignCommandBreakEvent event) throws IOException, ConfigNotExistException {
        if(event.getType() == 1){
            Block block = event.getEvent().getBlock();
            Location location = block.getLocation();
            String key = TranslationUtil.locationToKey(location);
            if(GetConfiguration.get("configs/signCommand.yml").contains(key)){
                SignCommand signCommand = GetConfiguration.get("configs/signCommand.yml").get(key);
                if(signCommand.getType() == 1 && !event.getEvent().getPlayer().isOp()){
                    event.getEvent().getPlayer().sendMessage("你不是op!");
                }else{
                    GetConfiguration.get("configs/signCommand.yml").set(key,null).save();
                    event.getEvent().getPlayer().sendMessage("已删除此命令木牌!");
                }
            }else{
                Main.getInstance().getLogger().info("不存在此命令木牌!");
            }
        }else{
            if(!event.getEvent().getPlayer().isOp()){
                event.getEvent().getPlayer().sendMessage("你不是op!");
                event.setCancelled(true);
                return;
            }

            Location location = event.getEvent().getBlock().getLocation();
            String key = TranslationUtil.locationToKey(location);
            System.out.println(key);
            if(GetConfiguration.get("configs/netShop.yml").contains(key)){
                GetConfiguration.get("configs/netShop.yml").set(key,null).save();
                event.getEvent().getPlayer().sendMessage("已删除此商店木牌!");
            }else{
                Main.getInstance().getLogger().info("不存在此商店木牌!");
            }
        }





    }

    @EventHandler
    public void onSignCommandBuild(SignCommandBuildEvent event) throws IOException, FailTranslationToIntException, ConfigNotExistException {
        //System.out.println("build");
        ConfigurationHandler configurationHandler = new DefaultConfigurationHandlerImpl();
        List<String> linesInConfig = (List<String>) configurationHandler.getList("signCommandStyle");

        if(linesInConfig == null){
            linesInConfig = Arrays.asList("[NetCommand]","[BUY]","[SELL]");
        }
        ///System.out.println("getLines");
        String[] lines = event.getEvent().getLines();
        Player player = event.getEvent().getPlayer();

        if(lines[0].equals(linesInConfig.get(0))){
            //player.sendMessage("This method has been deprecated");
            int type;
            type = 2;

            Location location = event.getEvent().getBlock().getLocation();
            List<Double> temp = Arrays.asList(location.getX(),location.getY(),location.getZ());

            //  key
            String key = TranslationUtil.locationToKey(location);

            String description = lines[1];
            if((lines[2]+lines[3]).isEmpty()){
                player.sendMessage("指令不能为空!");
                return;
            }
            String[] cmds =  (lines[2]+lines[3]).split(";");
            List<String> commands = Arrays.stream(cmds).filter(v -> !(v.isEmpty())).map(v->v.trim()).collect(Collectors.toList());
            SignCommand signCommand = SignCommand
                    .builder()
                    .description(description)
                    .location(temp)
                    .cmd(commands)
                    .type(type)
                    .build();

            GetConfiguration.get("configs/signCommand.yml").set(key,signCommand).save();

            player.sendMessage("已设置指令木牌!");
        }
        //  木牌商店
        else if(lines[0].equals(linesInConfig.get(1))||lines[0].equals(linesInConfig.get(2))){
            if(player.isOp()){
                int type;

                if(lines[0].equals(linesInConfig.get(1))){
                    type = 1;
                }else
                {
                    type = 2;
                }

                if((CheckUtil.isNull(lines[1]))||(!(CheckUtil.isDigital(lines[2])&& CheckUtil.isDigital(lines[3])))){
                    player.sendMessage("参数错误");
                }
                else{

                    String id = lines[1];
                    long cnt = Long.parseLong(lines[2]);
                    long cost = Long.parseLong(lines[3]);
                    System.out.println(id);
                    if(CheckUtil.isNull(id)){
                        player.sendMessage("参数错误");
                        return;
                    }

                    //  检验id
                    String itemName;

                    if(type == 1){
                        if(CheckUtil.isDigital(id)){
                            net.minecraft.server.v1_16_R1.Item item = net.minecraft.server.v1_16_R1.Item.getById(TranslationUtil.toInt(id));

                            if(item == null){
                                player.sendMessage("不存在此物品!");
                                return;
                            }
//                        temp = Item.getById(TranslationUtil.toInt(id)).getName().split("\\.");
//                        itemName = temp[temp.length-1];
                            String var1 = IRegistry.ITEM.getKey(item).getNamespace();
                            String var2 = IRegistry.ITEM.getKey(item).getKey();
//                        System.out.println(var1 + ":" +  var2);
                            itemName = var1 + ":" +  var2;
                        }else{
                            itemName = id;
                            if (isNotOriginalItems(player, itemName)) return;
                        }
                    }else{
                        itemName = id;
                        if(CheckUtil.isDigital(id)){

                            net.minecraft.server.v1_16_R1.Item item = net.minecraft.server.v1_16_R1.Item.getById(TranslationUtil.toInt(id));

                            if(item == null){
                                player.sendMessage("不存在此物品!");
                                return;
                            }
                        }else{
                            if (isNotOriginalItems(player, itemName))return;
                        }
                    }
                    Location location = event.getEvent().getBlock().getLocation();
                    //  key
                    String key = TranslationUtil.locationToKey(location);

                    //System.out.println(key);

                    List<Double> temp = Arrays.asList(location.getX(),location.getY(),location.getZ());
                    SignShop signShop = SignShop.builder()
                            .type(type)
                            .id(itemName)
                            .cnt(cnt)
                            .cost(cost)
                            .location(temp)
                            .build();

                    ConfigurationHandler configurationHandler1 = new ConfigurationHandlerImpl("configs/netShop.yml");
                    configurationHandler1.set(key,signShop).save();
                }
            }else{
                player.sendMessage("快捷商店只能op建造!");
                event.getEvent().getBlock().setType(Material.AIR);
            }

        }
    }
    private boolean isNotOriginalItems(Player player, String itemName) {
        Material material;
        try {
            material = Material.matchMaterial(itemName);
            if (material == null) {
                player.sendMessage("只能是原生物品名称!");
                return true;
            }



        } catch (Exception e) {
            player.sendMessage("只能是原生物品名称!");
            return true;
        }
        return false;
    }

}
