package com.angel.bukkit.Private.Entitys;

import com.angel.core.Annotation.Entity;
import com.angel.core.Entity.Entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Location;

import java.util.List;


/**
 * @Author: Angel_zou
 * @Date: Created in 11:41 2020/8/27
 * @Connection: ahacgn@gmail.com
 * @Description: user
 */

public class User extends Entities {
    public String name;
    public List<Object> home;
}
