package com.angel.core.Exception;

/**
 * @Author: Angel_zou
 * @Date: Created in 19:03 2020/8/12
 * @Connection: ahacgn@gmail.com
 * @Description: 转换失败异常
 */
public class FailTranslationException extends Exception{
    public FailTranslationException(){
        super();
    }
    public FailTranslationException(String msg){
        super(msg);
    }
}
