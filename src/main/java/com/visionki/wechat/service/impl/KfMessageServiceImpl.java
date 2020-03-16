package com.visionki.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.exceptions.BaseException;
import com.visionki.wechat.mapper.*;
import com.visionki.wechat.model.*;
import com.visionki.wechat.param.SendKfMessageParam;
import com.visionki.wechat.service.KfMessageService;
import com.visionki.wechat.util.DateUtils;
import com.visionki.wechat.util.UUIDUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.builder.kefu.NewsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 23:10
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@Service
public class KfMessageServiceImpl implements KfMessageService {
    @Autowired
    private WechatMessageMapper wechatMessageMapper;
    @Autowired
    private WechatUserMapper wechatUserMapper;
    @Autowired
    private WechatTagMapper wechatTagMapper;
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatArticleContentMapper wechatArticleContentMapper;
    @Autowired
    private WechatOfficialArticleMapper wechatOfficialArticleMapper;
    @Autowired
    private WechatSendRecordMapper wechatSendRecordMapper;


    @Override
    public int sendToTag(SendKfMessageParam sendKfMessageParam) {
        WechatSendRecord wechatSendRecord = new WechatSendRecord();
        // 获取对应的用户列表
        List<WechatUser> wechatUserList;
        WechatUser wechatUser = new WechatUser();
        wechatUser.setSubscribe(1);
        if ("0".equals(sendKfMessageParam.getTagId())){
            wechatUserList = wechatUserMapper.select(wechatUser);
            wechatSendRecord.setTag("所有用户");
        }else {
            // 获取标签
            WechatTag wechatTag = wechatTagMapper.selectByPrimaryKey(sendKfMessageParam.getTagId());
            wechatSendRecord.setTag(wechatTag.getTagName());
            wechatUser.setTagName(wechatTag.getTagName());
            wechatUserList = wechatUserMapper.select(wechatUser);
        }
        // 创建三个计数
        int successCount = 0;
        int failCount = 0;
        int total = wechatUserList.size();
        // 遍历发送素材
        if (sendKfMessageParam.getMsgType() == 0){
            // 文本消息
            WechatMessage wechatMessage = wechatMessageMapper.selectByPrimaryKey(sendKfMessageParam.getMessageId());
            wechatSendRecord.setMediaName(wechatMessage.getName());
            for (WechatUser w : wechatUserList){
                WxMpKefuMessage message = WxMpKefuMessage.TEXT()
                        .toUser(w.getOpenId())
                        .content(wechatMessage.getContent())
                        .build();
                try {
                    wxMpService.getKefuService().sendKefuMessage(message);
                    successCount++;
                } catch (WxErrorException e) {
                    failCount++;
                    e.printStackTrace();
                }
            }
        }else if (sendKfMessageParam.getMsgType() == 5){
            // 图文消息(外链型)
            WechatMessage wechatMessage = wechatMessageMapper.selectByPrimaryKey(sendKfMessageParam.getMessageId());
            if (wechatMessage == null){
                throw new BaseException(REnum.NOT_DATA);
            }
            wechatSendRecord.setMediaName(wechatMessage.getName());
            WechatArticleContent wechatArticleContent = new WechatArticleContent();
            wechatArticleContent.setMessageId(sendKfMessageParam.getMessageId());
            PageHelper.startPage(1,1000,"sort asc");
            List<WechatArticleContent> wechatArticleContentList = wechatArticleContentMapper.select(wechatArticleContent);
            if (wechatArticleContentList.size() == 0){
                throw new BaseException(REnum.MEDIA_NOT_CONTENT);
            }

            for (WechatUser w : wechatUserList){
                NewsBuilder news = WxMpKefuMessage.NEWS()
                        .toUser(w.getOpenId());
                // 多图文默认只发第一条
                WechatArticleContent wac = wechatArticleContentList.get(0);
                WxMpKefuMessage.WxArticle article = new WxMpKefuMessage.WxArticle();
                article.setUrl(wac.getUrl());
                article.setPicUrl(wac.getPicUrl());
                article.setDescription(wac.getDescription());
                article.setTitle(wac.getTitle());
                news.addArticle(article);
                try {
                    wxMpService.getKefuService().sendKefuMessage(news.build());
                    successCount++;
                } catch (WxErrorException e) {
                    if (e.getError().getErrorCode() == 45008){
                        // 错误代码：45008, 错误信息：图文消息超过限制，微信原始报文：{"errcode":45008,"errmsg":"article size out of limit hint: [jAmrGa00033929]"}
                        throw new BaseException(REnum.TOO_MUCH_MATERIAL);
                    }
                    failCount++;
                    e.printStackTrace();
                }
            }
        }else if (sendKfMessageParam.getMsgType() == 6){
            // 图文消息(官方文章)
            WechatOfficialArticle wechatOfficialArticle = new WechatOfficialArticle();
            wechatOfficialArticle.setMediaId(sendKfMessageParam.getMessageId());
            wechatOfficialArticle = wechatOfficialArticleMapper.selectOne(wechatOfficialArticle);
            if (wechatOfficialArticle == null){
                throw new BaseException(REnum.MEDIA_NOT_EXISTS);
            }
            wechatSendRecord.setMediaName(wechatOfficialArticle.getTitles());
            for (WechatUser w : wechatUserList) {
                WxMpKefuMessage build = WxMpKefuMessage.MPNEWS()
                        .toUser(w.getOpenId())
                        .mediaId(wechatOfficialArticle.getMediaId())
                        .build();
                try {
                    wxMpService.getKefuService().sendKefuMessage(build);
                    successCount++;
                } catch (WxErrorException e) {
                    if (e.getError().getErrorCode() == 45008) {
                        // 错误代码：45008, 错误信息：图文消息超过限制，微信原始报文：{"errcode":45008,"errmsg":"article size out of limit hint: [jAmrGa00033929]"}
                        throw new BaseException(REnum.TOO_MUCH_MATERIAL);
                    }
                    failCount++;
                    e.printStackTrace();
                }
            }
        }
        // 存入发送记录
        wechatSendRecord.setId(UUIDUtils.uuid());
        wechatSendRecord.setFailCount(failCount);
        wechatSendRecord.setSuccessCount(successCount);
        wechatSendRecord.setTotal(total);
        wechatSendRecord.setMsgType(sendKfMessageParam.getMsgType());
        wechatSendRecord.setMediaId(sendKfMessageParam.getMessageId());
        wechatSendRecord.setSendTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        wechatSendRecordMapper.insert(wechatSendRecord);
        return successCount;
    }

    @Override
    public List<WechatSendRecord> getSendRecord() {
        return wechatSendRecordMapper.selectAll();
    }
}
