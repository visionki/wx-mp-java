package com.visionki.wechat.tools.handler;

import com.visionki.wechat.mapper.WechatUserMapper;
import com.visionki.wechat.model.WechatUser;
import com.visionki.wechat.util.DateUtils;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

    @Autowired
    private WechatUserMapper wechatUserMapper;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        this.logger.info("取消关注用户 OPENID: " + openId);
        WechatUser wechatUser = new WechatUser();
        wechatUser.setOpenId(openId);
        wechatUser = wechatUserMapper.selectOne(wechatUser);
        if (wechatUser != null){
            wechatUser.setSubscribe(0);
            wechatUser.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
            wechatUserMapper.updateByPrimaryKey(wechatUser);
        }
        return null;
    }

}
