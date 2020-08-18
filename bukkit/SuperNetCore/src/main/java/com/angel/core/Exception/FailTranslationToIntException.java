package com.angel.core.Exception;

/**
 * @Author: Angel_zou
 * @Date: Created in 19:05 2020/8/12
 * @Connection: ahacgn@gmail.com
 * @Description: 转换int异常
 */
public class FailTranslationToIntException extends FailTranslationException{
    public FailTranslationToIntException(){
        super();
    }
    public FailTranslationToIntException(String msg){
        super(msg);
    }
}
