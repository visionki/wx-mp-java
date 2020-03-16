package com.visionki.wechat.service.impl;

import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.exceptions.BaseException;
import com.visionki.wechat.mapper.WechatMessageMapper;
import com.visionki.wechat.mapper.WechatOfficialArticleMapper;
import com.visionki.wechat.mapper.WechatReplyMapper;
import com.visionki.wechat.model.WechatMessage;
import com.visionki.wechat.model.WechatOfficialArticle;
import com.visionki.wechat.model.WechatReply;
import com.visionki.wechat.service.ReplyService;
import com.visionki.wechat.util.DateUtils;
import com.visionki.wechat.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 15:07
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private WechatReplyMapper wechatReplyMapper;
    @Autowired
    private WechatMessageMapper wechatMessageMapper;
    @Autowired
    private WechatOfficialArticleMapper wechatOfficialArticleMapper;

    @Override
    public List<WechatReply> getList() {
        return wechatReplyMapper.selectAll();
    }

    @Override
    public int addReply(WechatReply wechatReply) {
        wechatReply.setId(UUIDUtils.uuid());
        wechatReply.setCreateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        wechatReply.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        // 设置名称
        if (wechatReply.getMsgType() == 0 || wechatReply.getMsgType() == 5){
            WechatMessage wechatMessage = wechatMessageMapper.selectByPrimaryKey(wechatReply.getMessageId());
            if (wechatMessage == null || !wechatMessage.getMsgType().equals(wechatReply.getMsgType())){
                throw new BaseException(REnum.NOT_DATA);
            }
            wechatReply.setMessageName(wechatMessage.getName());
        }else if (wechatReply.getMsgType() == 6){
            WechatOfficialArticle wechatOfficialArticle = new WechatOfficialArticle();
            wechatOfficialArticle.setMediaId(wechatReply.getMessageId());
            wechatOfficialArticle = wechatOfficialArticleMapper.selectOne(wechatOfficialArticle);
            if (wechatOfficialArticle == null){
                throw new BaseException(REnum.NOT_DATA);
            }
            if (wechatOfficialArticle.getTitles().length() - wechatOfficialArticle.getTitles().replace(";","").length() > 1){
                throw new BaseException(REnum.TOO_MUCH_MATERIAL);
            }
            wechatReply.setMessageName(wechatOfficialArticle.getTitles());
        }
        return wechatReplyMapper.insert(wechatReply);
    }

    @Override
    public int updateReply(WechatReply wechatReply) {
        wechatReply.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        // 设置名称
        if (wechatReply.getMsgType() == 0 || wechatReply.getMsgType() == 5){
            WechatMessage wechatMessage = wechatMessageMapper.selectByPrimaryKey(wechatReply.getMessageId());
            if (wechatMessage == null || !wechatMessage.getMsgType().equals(wechatReply.getMsgType())){
                throw new BaseException(REnum.NOT_DATA);
            }
            wechatReply.setMessageName(wechatMessage.getName());
        }else if (wechatReply.getMsgType() == 6){
            WechatOfficialArticle wechatOfficialArticle = new WechatOfficialArticle();
            wechatOfficialArticle.setMediaId(wechatReply.getMessageId());
            wechatOfficialArticle = wechatOfficialArticleMapper.selectOne(wechatOfficialArticle);
            if (wechatOfficialArticle == null){
                throw new BaseException(REnum.NOT_DATA);
            }
            if (wechatOfficialArticle.getTitles().length() - wechatOfficialArticle.getTitles().replace(";","").length() > 1){
                throw new BaseException(REnum.TOO_MUCH_MATERIAL);
            }
            wechatReply.setMessageName(wechatOfficialArticle.getTitles());
        }
        return wechatReplyMapper.updateByPrimaryKeySelective(wechatReply);
    }

    @Override
    public int deleteReply(String id) {
        return wechatReplyMapper.deleteByPrimaryKey(id);
    }
}
