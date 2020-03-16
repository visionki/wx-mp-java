package com.visionki.wechat.tools.handler;

import com.github.pagehelper.PageHelper;
import com.visionki.wechat.mapper.WechatArticleContentMapper;
import com.visionki.wechat.mapper.WechatMessageMapper;
import com.visionki.wechat.mapper.WechatOfficialArticleMapper;
import com.visionki.wechat.mapper.WechatReplyMapper;
import com.visionki.wechat.model.WechatArticleContent;
import com.visionki.wechat.model.WechatMessage;
import com.visionki.wechat.model.WechatOfficialArticle;
import com.visionki.wechat.model.WechatReply;
import com.visionki.wechat.tools.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.builder.kefu.MpNewsBuilder;
import me.chanjar.weixin.mp.builder.outxml.NewsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {
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
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
//        try {
//            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
//                && weixinService.getKefuService().kfOnlineList()
//                .getKfOnlineList().size() > 0) {
//                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
//                    .fromUser(wxMessage.getToUser())
//                    .toUser(wxMessage.getFromUser()).build();
//            }
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
        if (wxMessage.getMsgType().equals(XmlMsgType.TEXT)){
            // 文字消息,匹配关键词
            // 先查询精确
            WechatReply wechatReply = new WechatReply();
            wechatReply.setMatchingType(0);
            wechatReply.setKeyWord(wxMessage.getContent());
            List<WechatReply> select = wechatReplyMapper.select(wechatReply);
            WechatReply reply = null;
            if (select.size() != 0){
                // 匹配到了,进行回复
                // 取第一个匹配到的关注回复
                reply = select.get(0);
            }else{
                // 没有精确匹配回复,进行模糊查询
                wechatReply = new WechatReply();
                wechatReply.setMatchingType(1);
                select = wechatReplyMapper.select(wechatReply);
                for (WechatReply w : select){
                    if (wxMessage.getContent().contains(w.getKeyWord())){
                        // 匹配到辽模糊类型,进行回复
                        reply = w;
                        break;
                    }
                }
                // for循环完了还没匹配到,直接返回空
                if (reply == null){
                    return null;
                }
            }
            // 取上面匹配到的进行回复
            if (reply.getMsgType() == 0){
                // 回复文本消息
                WechatMessage wechatMessage = wechatMessageMapper.selectByPrimaryKey(reply.getMessageId());
                return new TextBuilder().build(wechatMessage.getContent(), wxMessage, weixinService);
            }else if (reply.getMsgType() == 5){
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
            }else if (reply.getMsgType() == 6){
                // 走客服消息进行回复
                WxMpKefuMessage build = WxMpKefuMessage.MPNEWS()
                        .toUser(wxMessage.getFromUser())
                        .mediaId(reply.getMessageId())
                        .build();
                try {
                    weixinService.getKefuService().sendKefuMessage(build);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
