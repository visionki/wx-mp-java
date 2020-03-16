package com.visionki.wechat.service;

import com.visionki.wechat.model.WechatMessage;
import com.visionki.wechat.model.WechatReply;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 15:08
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
public interface ReplyService {
    /**
     * 获取回复列表
     * @return
     */
    List<WechatReply> getList();

    /**
     * 新增回复
     * @param wechatReply
     * @return
     */
    int addReply(WechatReply wechatReply);

    /**
     * 更新回复
     * @param wechatReply
     * @return
     */
    int updateReply(WechatReply wechatReply);

    /**
     * 删除指定关键字回复
     * @param id
     * @return
     */
    int deleteReply(String id);
}
