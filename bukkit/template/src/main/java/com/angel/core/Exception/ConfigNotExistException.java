package com.angel.core.Exception;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:47 2020/8/20
 * @Connection: ahacgn@gmail.com
 * @Description: 配置文件不存在的异常
 */
public class ConfigNotExistException extends Exception{
    public ConfigNotExistException(){super();}
    public ConfigNotExistException(String msg){super(msg);}
}

