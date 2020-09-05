package com.angel.core.Wrap;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.angel.core.TemplatePlugin;
import com.angel.core.util.JsonConfigurationUtil;
import org.bukkit.plugin.java.JavaPlugin;


import javax.validation.constraints.NotNull;
import java.io.*;
import java.lang.reflect.Type;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:53 2020/8/30
 * @Connection: ahacgn@gmail.com
 * @Description: json配置包裹类
 */
public class JsonConfigurationWrap {
    @JSONField(
            serialize= false,
            deserialize = false
     )
    private String path;
    private JSONObject map;
    public JsonConfigurationWrap(String path,JSONObject jsonObject){
        this.path = path;
        map = jsonObject;
    }

    public boolean contains(String key){
        return map.containsKey(key);
    }
    public <T> T get(String key) throws ClassNotFoundException {
        if(!contains(key)) return null;

        return (T) map.get(key);
    }

    public JsonConfigurationWrap set(@NotNull String key, Object obj){

        if(obj == null&&contains(key)){
            map.remove(key);
        }
        //JsonEntityWrap jsonEntityWrap = new JsonEntityWrap(obj);


        map.put(key,obj);
        return this;
    }

    public void save() throws IOException {
        JsonConfigurationUtil.addJsonConfigurationWrap(path,this);
        File file = new File(TemplatePlugin.getInstance().getDataFolder(),path);
        String json = JsonConfigurationUtil.toPrettyFormat(map);
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        writer.write(json);
        writer.flush();
        writer.close();
    }

}
