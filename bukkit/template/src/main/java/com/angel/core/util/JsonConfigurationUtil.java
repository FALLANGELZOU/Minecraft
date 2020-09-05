package com.angel.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.angel.core.Wrap.JsonConfigurationWrap;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:24 2020/8/30
 * @Connection: ahacgn@gmail.com
 * @Description: json持久化工具类
 */
public class JsonConfigurationUtil implements Util{

    private static Map<String,JsonConfigurationWrap> jsonConfigurationWrapMap = new HashMap<>();

    public static void addJsonConfigurationWrap(String path,JsonConfigurationWrap jsonConfigurationWrap){

        jsonConfigurationWrapMap.put(path,jsonConfigurationWrap);
    }
    public static boolean contains(@NotNull String path){
        return jsonConfigurationWrapMap.containsKey(path);
    }
    public static JsonConfigurationWrap get(@NotNull String path){
        return contains(path)?jsonConfigurationWrapMap.get(path):null;
    }
    public static String toPrettyFormat(@NotNull String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String pretty = JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);

        return pretty;
    }

    public static String toPrettyFormat(@NotNull JSONObject jsonObject){
        String pretty = JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        return pretty;
    }

}
