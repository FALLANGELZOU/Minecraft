package com.angel.core.Exception;

/**
 * @Author: Angel_zou
 * @Date: Created in 13:58 2020/8/22
 * @Connection: ahacgn@gmail.com
 * @Description: 不是命令类
 */
public class NotCommandException extends Exception{
    public NotCommandException(){super();}
    public NotCommandException(String msg){super(msg);}
}
