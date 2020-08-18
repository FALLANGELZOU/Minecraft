package com.angel.core.Entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: Angel_zou
 * @Date: Created in 21:28 2020/8/7
 * @Connection: ahacgn@gmail.com
 * @Description: 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User extends NetEntity {

    @NotNull
    private String nickname;
    @NotNull
    private String uuid;
    private String password;
    private List<String> roles;
    @NotNull
    private long score;


    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> map = new HashMap<>();

        map.put("nickname",nickname);
        map.put("uuid",uuid);
        map.put("password",password);
        map.put("roles",roles);
        map.put("score",score);
        return map;

    }
//    public User(Map<String,Object> map){
//        nickname = (String) map.get("nickname");
//        uuid = (String) map.get("uuid");
//        password = (String) map.get("password");
//        roles = (List<String>) map.get("roles");
//        score = ((Number)map.get("score")).longValue();
//    }

    public static User deserialize(Map<String,Object> map){
        String nickname = (String) map.get("nickname");
        String uuid = (String) map.get("uuid");
        String password = (String) map.get("password");
        List<String> roles = (List<String>) map.get("roles");
        long score = ((Number)map.get("score")).longValue();

        return new User(nickname,uuid,password,roles,score);
    }
}

