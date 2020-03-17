package com.visionki.wechat.service;

import com.visionki.wechat.model.WechatUser;
import com.visionki.wechat.param.UpdateUserInfoParam;
import com.visionki.wechat.param.UserSelectParam;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 16:46
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
public interface UserService {

    /**
     * 根据昵称搜索用户信息
     * @param nickName
     * @return
     */
    List<WechatUser> selectByNickName(String nickName);

    /**
     * 更新用户的标签和备注
     * @param updateUserInfoParam
     * @return
     */
    int updateUserInfo(UpdateUserInfoParam updateUserInfoParam);

    /**
     * 根据参数查询
     * @param userSelectParam
     * @return
     */
    List<WechatUser> selectByAttr(UserSelectParam userSelectParam);

    /**
     * 同步用户
     * @return
     */
    int synchronization();

}
