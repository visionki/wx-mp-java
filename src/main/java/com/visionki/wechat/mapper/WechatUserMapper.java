package com.visionki.wechat.mapper;

import com.visionki.wechat.base.BaseMapper;
import com.visionki.wechat.model.WechatUser;
import com.visionki.wechat.param.UserSelectParam;

import java.util.List;

public interface WechatUserMapper extends BaseMapper<WechatUser> {
    /**
     * 更新所有粉丝标签
     * @param oldTag
     * @param newTag
     */
    void updateAllUserTag(String oldTag, String newTag);

    /**
     * 根据昵称搜索用户信息
     * @param nickName
     * @return
     */
    List<WechatUser> selectByNickName(String nickName);

    /**
     * 根据条件查询用户列表
     * @param userSelectParam
     * @return
     */
    List<WechatUser> selectByAttr(UserSelectParam userSelectParam);

    /**
     * 指定日期当天的关注人数
     * @param dateStr
     * @return
     */
    int getSubscribeCountByDate(String dateStr);

    /**
     * 指定日期当天的取关人数
     * @param dateStr
     * @return
     */
    int getUnsubscribeCountByDate(String dateStr);

    /**
     * 获取指定日期某标签关注的人数
     * @param dateStr
     * @param tagName
     * @return
     */
    int getSubscribeCountByDateAndTagName(String dateStr, String tagName);
}