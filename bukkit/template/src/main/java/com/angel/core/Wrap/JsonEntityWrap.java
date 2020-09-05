package com.angel.core.Wrap;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Angel_zou
 * @Date: Created in 13:02 2020/8/30
 * @Connection: ahacgn@gmail.com
 * @Description: json实体类Wrap
 */
@Data
@Deprecated
public class JsonEntityWrap {
    private String type;
    private Object obj;
    public JsonEntityWrap(Object obj){
        this.type = obj.getClass().getName();
        System.out.println(type);
        this.obj = obj;
    }
}
