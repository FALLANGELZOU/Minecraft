package com.angel.core.util;

import org.hibernate.validator.constraints.NotBlank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:23 2020/8/11
 * @Connection: ahacgn@gmail.com
 * @Description: 各种检测
 */
public class CheckUtil {
    public static boolean isDigital(String temp){
        if(temp == null||temp.equals("")) return false;
        Pattern pattern = Pattern.compile("^-?[0-9]*$");
        Matcher matcher = pattern.matcher(temp);
        return matcher.matches();
    }
    public static <T> boolean isNull(T temp){
        if(temp == null||temp.equals("")){
            return true;
        }
        return false;
    }
}
