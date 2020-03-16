package com.visionki.wechat.tools.handler;

import com.github.pagehelper.PageHelper;
import com.visionki.wechat.mapper.*;
import com.visionki.wechat.model.*;
import com.visionki.wechat.service.TagService;
import com.visionki.wechat.service.UserService;
import com.visionki.wechat.tools.builder.TextBuilder;
import com.visionki.wechat.util.DateUtils;
import com.visionki.wechat.util.UUIDUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.builder.outxml.NewsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private WechatUserMapper wechatUserMapper;
    @Autowired
    private WechatTagMapper wechatTagMapper;
    @Autowired
    private WechatReplyMapper wechatReplyMapper;
    @Autowired
    private WechatMessageMapper wechatMessageMapper;
    @Autowired
    private WechatArticleContentMapper wechatArticleContentMapper;
    @Autowired
    private WechatOfficialArticleMapper wechatOfficialArticleMapper;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());
        // 获取微信用户基本信息
        WxMpUser wxMpUser = weixinService.getUserService().userInfo(wxMessage.getFromUser());
        WechatUser wechatUser = new WechatUser();
        wechatUser.setOpenId(wxMessage.getFromUser());
        wechatUser = wechatUserMapper.selectOne(wechatUser);
        boolean frist = false;
        if (wechatUser == null){
            frist = true;
            wechatUser = new WechatUser();
            // 设置首次关注相关的
            wechatUser.setCreatedTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
            wechatUser.setId(UUIDUtils.uuid());
        }
        wechatUser.setOpenId(wxMpUser.getOpenId());
        wechatUser.setCity(wxMpUser.getCity());
        wechatUser.setCountry(wxMpUser.getCountry());
        wechatUser.setDebug(0);
        wechatUser.setGroupId(wxMpUser.getGroupId());
        wechatUser.setHeadUrl(wxMpUser.getHeadImgUrl());
        wechatUser.setLanguage(wxMpUser.getLanguage());
        wechatUser.setNickName(wxMpUser.getNickname());
        wechatUser.setProvince(wxMpUser.getProvince());
        wechatUser.setRemark(wxMpUser.getRemark());
        wechatUser.setSex(wxMpUser.getSex());
        wechatUser.setSubscribe(1);
        wechatUser.setSubscribeTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        wechatUser.setUnionId("");
        wechatUser.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        // 设置标签
        if (!"".equals(wxMessage.getEventKey())){
            String tagId = wxMessage.getEventKey().replace("qrscene_","");
            WechatTag tag = wechatTagMapper.selectByPrimaryKey(tagId);
            if (tag != null){
                // 给用户打上标签
                weixinService.getUserTagService().batchTagging(tag.getTagId(),wxMpUser.getOpenId().split(","));
                wechatUser.setTagName(tag.getTagName());
            }
        }else{
            wechatUser.setTagName("");
        }
        if (frist){
            wechatUserMapper.insert(wechatUser);
        }else {
            wechatUserMapper.updateByPrimaryKey(wechatUser);
        }

        // 回复消息
        WechatReply wechatReply = new WechatReply();
        wechatReply.setKeyWord("subscribe");
        List<WechatReply> select = wechatReplyMapper.select(wechatReply);
        if (select.size() == 0){
            return null;
        }
        // 取第一个匹配到的关注回复
        wechatReply = select.get(0);
        if (wechatReply.getMsgType() == 0){
            // 回复文本消息
            WechatMessage wechatMessage = wechatMessageMapper.selectByPrimaryKey(wechatReply.getMessageId());
            return new TextBuilder().build(wechatMessage.getContent(), wxMessage, weixinService);
        }else if (wechatReply.getMsgType() == 5){
            // 图文消息(外链型)
            // 获取全部外链
            PageHelper.startPage(1,1000,"sort asc");
            List<WechatArticleContent> wechatArticleContentList = wechatArticleContentMapper.selectAll();
            NewsBuilder news = WxMpXmlOutMessage.NEWS();
            for (WechatArticleContent w : wechatArticleContentList){
                WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
                item.setDescription(w.getDescription());
                item.setPicUrl(w.getPicUrl());
                item.setTitle(w.getTitle());
                item.setUrl(w.getUrl());
                news.addArticle(item);
            }
            return news
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();
        }else if (wechatReply.getMsgType() == 6){
            // 走客服消息进行回复
            WxMpKefuMessage build = WxMpKefuMessage.MPNEWS()
                    .toUser(wxMessage.getFromUser())
                    .mediaId(wechatReply.getMessageId())
                    .build();
            try {
                weixinService.getKefuService().sendKefuMessage(build);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        //TODO
        System.out.println(wxMessage.getEventKey());
        return null;
    }

}
