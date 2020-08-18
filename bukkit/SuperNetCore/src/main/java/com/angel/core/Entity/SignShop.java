package com.angel.core.Entity;

import lombok.Builder;
import lombok.Data;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:08 2020/8/10
 * @Connection: ahacgn@gmail.com
 * @Description: 木牌商店实体类
 */

@Data
@Builder
public class SignShop extends NetEntity {
    //  1 buy 2 sell
    @NotNull
    private int type;
    @NotNull
    private String id;
    @NotNull
    private long cnt;
    @NotNull
    private long cost;
    @NotNull
    private List<Double> location;

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("type",type);
        map.put("id",id);
        map.put("cnt",cnt);
        map.put("cost",cost);
        map.put("location",location);
        return map;
    }
    public static SignShop deserialize(Map<String,Object> map){
        return SignShop.builder()
                .type((int)map.get("type"))
                .id((String)map.get("id"))
                .cnt(((Number)map.get("cnt")).longValue())
                .cost(((Number)map.get("cost")).longValue())
                .location((List<Double>) map.get("location"))
                .build();
    }
}
