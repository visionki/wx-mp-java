package com.visionki.wechat.param;

import lombok.Data;

/**
 * @Author: vision
 * @CreateDate: 2020/3/16 16:54
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 用户列表查询参数
 */
@Data
public class UserSelectParam {
    private String tagName;
    private String nickName;
}
