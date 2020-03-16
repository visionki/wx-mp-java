package com.visionki.wechat.param;

import lombok.Data;

/**
 * @Author: vision
 * @CreateDate: 2020/3/16 14:47
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 修改用户信息参数类
 */
@Data
public class UpdateUserInfoParam {
    private String id;
    private String tagName;
    private String remark;
}
