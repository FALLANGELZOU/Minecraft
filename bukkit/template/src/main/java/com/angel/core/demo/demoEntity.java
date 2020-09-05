package com.angel.core.demo;

import com.angel.core.Annotation.Entity;
import com.angel.core.Entity.Entities;
import lombok.Data;

/**
 * @Author: Angel_zou
 * @Date: Created in 18:31 2020/8/29
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
@Entity
public class demoEntity extends Entities {
    private String name;
    private String password;
}
