package com.visionki.wechat.util;

/**
 * @Author: vision
 * @CreateDate: 2019/12/27 17:08
 * @Version: 1.0
 * @Copyright: Copyright (c) 2019
 * @Description: 数字格式化
 */
public class NumberToString {

    public static String format(Long number){
        if (number < 1000){
            // 小于一千直接显示
            return number + "";
        }else if(number < 10000){
            // 大于一千小于一万以K作为单位
            return String.format("%.1f", number / 1000f) + "k+";
        }else{
            // 1W ~ 10
            return String.format("%.1f", number / 10000f) + "w+";
        }
    }

}
