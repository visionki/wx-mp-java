package com.visionki.wechat.param;

import lombok.Data;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 23:02
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 发送客服消息包装类
 */
@Data
public class SendKfMessageParam {
    private Integer msgType;
    private String messageId;
    private String tagId;
}
