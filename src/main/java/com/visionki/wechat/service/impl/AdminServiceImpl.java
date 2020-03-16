package com.visionki.wechat.service.impl;

import com.visionki.wechat.constant.AppConst;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.constant.RedisConst;
import com.visionki.wechat.exceptions.BaseException;
import com.visionki.wechat.mapper.ManageAdminMapper;
import com.visionki.wechat.model.ManageAdmin;
import com.visionki.wechat.service.AdminService;
import com.visionki.wechat.util.RedisUtil;
import com.visionki.wechat.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 14:49
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ManageAdminMapper manageAdminMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(String account, String password) {
        ManageAdmin manageAdmin = new ManageAdmin();
        manageAdmin.setAccount(account);
        manageAdmin.setPassword(password);
        manageAdmin = manageAdminMapper.selectOne(manageAdmin);
        if (manageAdmin == null){
            throw new BaseException(REnum.LOGIN_FAILURE);
        }
        String token = UUIDUtils.uuid();
        redisUtil.set(RedisConst.LOGIN_ADMIN_PREFIX + token, manageAdmin, AppConst.LOGIN_DURATION, TimeUnit.HOURS);
        return token;
    }
}
