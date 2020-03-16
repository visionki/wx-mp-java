package com.visionki.wechat.service;

import com.visionki.wechat.model.WechatTag;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 14:59
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 用户标签逻辑处理类
 */
public interface TagService {
    /**
     * 获取全部的用户标签
     * @return
     */
    List<WechatTag> getList();

    /**
     * 新增微信用户标签,并生成带参数二维码
     * @param wechatTag
     * @return
     * @throws WxErrorException
     */
    int newTag(WechatTag wechatTag) throws WxErrorException;

    /**
     * 根据ID获取标签信息
     * @param id
     * @return
     */
    WechatTag getTag(String id);

    /**
     * 根据条件获取
     * @param wechatTag
     * @return
     */
    WechatTag getTag(WechatTag wechatTag);

    /**
     * 根据ID删除指定标签
     * @param id
     * @return
     */
    int deleteTag(String id) throws WxErrorException;

    /**
     * 更新标签
     * @param wechatTag
     * @return
     */
    int updateTag(WechatTag wechatTag) throws WxErrorException;
}
