package com.visionki.wechat.interceptor;

import com.alibaba.fastjson.JSON;
import com.visionki.wechat.constant.AppConst;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.constant.RedisConst;
import com.visionki.wechat.util.InterceptorUtil;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Author: vision
 * @CreateDate: 2019/9/2 14:28
 * @Version: 1.0
 * @Copyright: Copyright (c) 2019
 * @Description: 后台管理拦截器
 */
@Component
@Slf4j
public class ManageInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AppConst.REQUEST_TOKEN_KEY);
        if (StringUtils.isBlank(token) || !redisUtil.hasKey(RedisConst.LOGIN_ADMIN_PREFIX + token)){
            // token不存在或者redis中没有对应的记录，通知前端进行登录
            InterceptorUtil.returnJson(response, JSON.toJSONString(RUtil.error(REnum.TOKEN_VALIDATION_FAILED)));
            return false;
        }
        // 存在则延长2小时
        redisUtil.expire(RedisConst.LOGIN_ADMIN_PREFIX + token, 2, TimeUnit.HOURS);
        return true;
    }
}
