package com.angel.bukkit.Private.Wrap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Location;


/**
 * @Author: Angel_zou
 * @Date: Created in 11:25 2020/8/27
 * @Connection: ahacgn@gmail.com
 * @Description: 死亡信息包裹类
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeathInformationWrap {
    private String playerName;
    private Location location;
}
