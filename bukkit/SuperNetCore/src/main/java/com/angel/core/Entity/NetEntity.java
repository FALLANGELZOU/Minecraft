package com.angel.core.Entity;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:15 2020/8/10
 * @Connection: ahacgn@gmail.com
 * @Description: SuperNet实体类的祖类
 */
 abstract class NetEntity implements ConfigurationSerializable {
 @Override
 public abstract Map<String, Object> serialize();
}
