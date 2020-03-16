package com.visionki.wechat.service;

import com.visionki.wechat.model.WechatSendRecord;
import com.visionki.wechat.param.SendKfMessageParam;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 23:10
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
public interface KfMessageService {
    /**
     * 向标签用户发送客服消息
     * @param sendKfMessageParam
     * @return
     */
    int sendToTag(SendKfMessageParam sendKfMessageParam);

    /**
     * 查询发送记录
     * @return
     */
    List<WechatSendRecord> getSendRecord();

}
