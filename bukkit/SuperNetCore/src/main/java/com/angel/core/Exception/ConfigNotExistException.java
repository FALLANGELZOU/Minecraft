package com.angel.core.Exception;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:15 2020/8/13
 * @Connection: ahacgn@gmail.com
 * @Description: 配置文件找不到的错误
 */
public class ConfigNotExistException extends Exception{
    public ConfigNotExistException(){super();}
    public ConfigNotExistException(String msg){super(msg);}
}
