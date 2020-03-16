package com.visionki.wechat.param;

import lombok.Data;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 14:47
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 登录参数
 */
@Data
public class LoginParams {
    private String account;
    private String password;
}
