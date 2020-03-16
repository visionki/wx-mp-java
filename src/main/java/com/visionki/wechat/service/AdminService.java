package com.visionki.wechat.service;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 14:49
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
public interface AdminService {
    /**
     * 登录返回token
     * @param account
     * @param password
     * @return
     */
    String login(String account, String password);
}
