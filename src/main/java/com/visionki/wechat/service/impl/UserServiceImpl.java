package com.visionki.wechat.service.impl;

import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.exceptions.BaseException;
import com.visionki.wechat.mapper.WechatTagMapper;
import com.visionki.wechat.mapper.WechatUserMapper;
import com.visionki.wechat.model.WechatTag;
import com.visionki.wechat.model.WechatUser;
import com.visionki.wechat.param.UpdateUserInfoParam;
import com.visionki.wechat.param.UserSelectParam;
import com.visionki.wechat.service.UserService;
import com.visionki.wechat.util.DateUtils;
import com.visionki.wechat.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 16:47
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private WechatUserMapper wechatUserMapper;
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatTagMapper wechatTagMapper;

    @Override
    public List<WechatUser> selectByNickName(String nickName) {
        return wechatUserMapper.selectByNickName(nickName);
    }

    @Override
    public int updateUserInfo(UpdateUserInfoParam updateUserInfoParam) {
        WechatUser wechatUser = wechatUserMapper.selectByPrimaryKey(updateUserInfoParam.getId());
        if (wechatUser == null){
            throw new BaseException(REnum.DATA_NOT_EXISTS);
        }
        if (wechatUser.getTagName().equals(updateUserInfoParam.getTagName())){
            // 原标签与目标标签一样,啥事不干皆大欢喜
            // 都有或者都没
        }else if (!"".equals(updateUserInfoParam.getTagName())){
            // 判断用户是否有标签,有的话要先去掉
            if (!"".equals(wechatUser.getTagName())){
                // 用户存在标签
                WechatTag wechatTag = new WechatTag();
                wechatTag.setTagName(wechatUser.getTagName());
                wechatTag = wechatTagMapper.selectOne(wechatTag);
                if (wechatTag == null){
                    throw new BaseException(REnum.TAG_NOT_EXISTS);
                }
                try {
                    wxMpService.getUserTagService().batchUntagging(wechatTag.getTagId(), wechatUser.getOpenId().split(";"));
                } catch (WxErrorException e) {
                    log.error("删除标签错误,标签:{},用户:{}", wechatTag,wechatUser);
                    e.printStackTrace();
                }
            }
            // 添加标签
            WechatTag wechatTag = new WechatTag();
            wechatTag.setTagName(updateUserInfoParam.getTagName());
            wechatTag = wechatTagMapper.selectOne(wechatTag);
            if (wechatTag == null){
                throw new BaseException(REnum.TAG_NOT_EXISTS);
            }
            // 标签更新
            try {
                wxMpService.getUserTagService().batchTagging(wechatTag.getTagId(),wechatUser.getOpenId().split(","));
            } catch (WxErrorException e) {
                e.printStackTrace();
                log.error("给用户打上标签出现错误,标签:{},用户:{}", wechatTag,wechatUser);
                throw new BaseException(REnum.ERROR);
            }
        }else if ("".equals(updateUserInfoParam.getTagName()) && !"".equals(wechatUser.getTagName())){
            // 用户标签与目标标签不相等,但目标标签为空,就是得去掉标签了吧
            WechatTag wechatTag = new WechatTag();
            wechatTag.setTagName(wechatUser.getTagName());
            wechatTag = wechatTagMapper.selectOne(wechatTag);
            if (wechatTag == null){
                throw new BaseException(REnum.TAG_NOT_EXISTS);
            }
            try {
                wxMpService.getUserTagService().batchUntagging(wechatTag.getTagId(), wechatUser.getOpenId().split(";"));
            } catch (WxErrorException e) {
                log.error("删除标签错误,标签:{},用户:{}", wechatTag,wechatUser);
                e.printStackTrace();
            }
        }
        wechatUser.setTagName(updateUserInfoParam.getTagName());
        // 标签处理完了,处理备注就行
        try {
            wxMpService.getUserService().userUpdateRemark(wechatUser.getOpenId(), updateUserInfoParam.getRemark());
            wechatUser.setRemark(updateUserInfoParam.getRemark());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return wechatUserMapper.updateByPrimaryKey(wechatUser);
    }

    @Override
    public List<WechatUser> selectByAttr(UserSelectParam userSelectParam) {
        return wechatUserMapper.selectByAttr(userSelectParam);
    }

    @Override
    public int synchronization() {
        int count = 0;
        List<WechatTag> wechatTags = wechatTagMapper.selectAll();
        Map<Long,String> wechatTagMap = new HashMap<>(wechatTags.size());
        for (WechatTag wechatTag : wechatTags){
            wechatTagMap.put(wechatTag.getTagId(), wechatTag.getTagName());
        }
        String nextOpenid = "";
        while (true){
            WxMpUserList wxUserList;
            try {
                wxUserList = wxMpService.getUserService().userList(nextOpenid);
                if (wxUserList.getNextOpenid().length() == 0){
                    break;
                }
                List<WxMpUser> wxMpUsers = wxMpService.getUserService().userInfoList(wxUserList.getOpenids());
                for (WxMpUser wxMpUser : wxMpUsers){
                    try {
                        // 挨个入库
                        WechatUser wechatUser = new WechatUser();
                        wechatUser.setOpenId(wxMpUser.getOpenId());
                        wechatUser = wechatUserMapper.selectOne(wechatUser);
                        if (wechatUser == null){
                            wechatUser = new WechatUser();
                            wechatUser.setId(UUIDUtils.uuid());
                            wechatUser.setCreatedTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
                            wechatUser.setOpenId(wxMpUser.getOpenId());
                            wechatUser.setCity(wxMpUser.getCity());
                            wechatUser.setDebug(0);
                            wechatUser.setProvince(wxMpUser.getProvince());
                            wechatUser.setCountry(wxMpUser.getCountry());
                            wechatUser.setHeadUrl(wxMpUser.getHeadImgUrl());
                            wechatUser.setLanguage(wxMpUser.getLanguage());
                            wechatUser.setNickName(wxMpUser.getNickname());
                            wechatUser.setGroupId(wxMpUser.getGroupId());
                            wechatUser.setUnionId("");
                            wechatUser.setSubscribeTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
                            wechatUser.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
                            wechatUser.setRemark(wxMpUser.getRemark());
                            wechatUser.setSex(wxMpUser.getSex());
                            wechatUser.setSubscribe(1);
                            if (wxMpUser.getTagIds().length > 0){
                                wechatUser.setTagName(wechatTagMap.get(wxMpUser.getTagIds()[0]));
                            }else {
                                wechatUser.setTagName("");
                            }
                            int insert = wechatUserMapper.insert(wechatUser);
                            count+=insert;
                        }else if (wechatUser.getSubscribe() == 0){
                            wechatUser.setSubscribe(1);
                            if (wxMpUser.getTagIds().length > 0){
                                wechatUser.setTagName(wechatTagMap.get(wxMpUser.getTagIds()[0]));
                            }else {
                                wechatUser.setTagName("");
                            }
                            wechatUser.setRemark(wxMpUser.getRemark());
                            wechatUserMapper.updateByPrimaryKey(wechatUser);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (WxErrorException e) {
                e.printStackTrace();
                throw new BaseException(REnum.ERROR);
            }
            nextOpenid = wxUserList.getNextOpenid();
        }
        return count;
    }
}
