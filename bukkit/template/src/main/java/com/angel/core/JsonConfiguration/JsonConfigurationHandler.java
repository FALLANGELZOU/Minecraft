package com.angel.core.JsonConfiguration;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.angel.core.Annotation.JsonConfiguration;
import com.angel.core.Exception.NotEntityException;
import com.angel.core.TemplatePlugin;
import com.angel.core.Wrap.JsonConfigurationWrap;
import com.angel.core.util.JsonConfigurationUtil;
import com.angel.core.util.ReflectionsUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:03 2020/8/30
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
public class JsonConfigurationHandler {
    public JsonConfigurationHandler(){
        Set<Class<?>> handleList = ReflectionsUtil.getTypesAnnotatedWith(JsonConfiguration.class);
        handleList.forEach(v->{
            if(!JsonConfigurations.class.isAssignableFrom(v)){
                try {
                    throw new NotEntityException(v.getName()+"不是Json配置文件");
                } catch (NotEntityException e) {
                    e.printStackTrace();
                }
            }
            String path = v.getAnnotation(JsonConfiguration.class).value();
            File file = new File(TemplatePlugin.getInstance().getDataFolder(),path);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            if(!file.exists()){
                try {
                    file.createNewFile();

                    JSONObject jsonObject = new JSONObject();

                    String json = JsonConfigurationUtil.toPrettyFormat(JSON.toJSONString(jsonObject));
                    Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                    writer.write(json);
                    writer.flush();
                    writer.close();

                    file = new File(TemplatePlugin.getInstance().getDataFolder(),path);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String json = readJsonFile(file);
            JSONObject jsonObject = JSONObject.parseObject(json);
            JsonConfigurationWrap jsonConfigurationWrap = new JsonConfigurationWrap(path,jsonObject);
            //System.out.println(jsonConfigurationWrap == null);
            JsonConfigurationUtil.addJsonConfigurationWrap(path,jsonConfigurationWrap);
        });
    }


    private static String readJsonFile(File jsonFile) {
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
