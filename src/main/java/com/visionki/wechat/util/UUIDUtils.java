package com.visionki.wechat.util;

import java.util.UUID;

/**
 * @author liuboyi
 * @Description:
 * @create 2019-04-10 14:52
 */
public class UUIDUtils {

    /**
     * 获取UUID，不含"-"
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
