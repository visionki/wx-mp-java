package com.visionki.wechat.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: vision
 * @CreateDate: 2019/5/31 16:30
 * @Version: 1.0
 * @Description: 拦截器拦截返回工具类
 * @Copyright: Copyright (c) 2019
 */
@Slf4j
public class InterceptorUtil {

    /**
     * 向浏览器输出json对象
     * @param response
     * @param json
     * @throws Exception
     */
    public static void returnJson(HttpServletResponse response, String json){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","sessionId, Origin, X-Requested-With, Content-Type, Accept, Authorization");
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }
}
