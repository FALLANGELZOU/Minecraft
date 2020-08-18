package com.angel.core.Entity;

import com.angel.core.NetEvent.NetEvent;
import lombok.Builder;
import lombok.Data;
import net.minecraft.server.v1_16_R1.ItemWrittenBook;
import net.minecraft.server.v1_16_R1.PlayerConnection;
import org.bukkit.block.Sign;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:32 2020/8/14
 * @Connection: ahacgn@gmail.com
 * @Description: 牌子指令实体类
 */
@Data
@Builder
public class SignCommand extends NetEntity{
    @NotNull
    private int type;// 普通指令和超级指令
    @NotNull
    private List<Double> location;
    @NotNull
    private List<String> cmd;
    @NotNull
    private String description;


    public static SignCommand deserialize(Map<String,Object> map){
        return SignCommand.builder()
                .type((int)map.get("type"))
                .location((List<Double>)map.get("location"))
                .cmd((List<String>)map.get("cmd"))
                .description((String)map.get("description"))
                .build();
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("location",location);
        map.put("cmd",cmd);
        map.put("description",description);
        return map;
    }
}
