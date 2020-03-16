package com.visionki.wechat.service.impl;

import com.visionki.wechat.mapper.WechatArticleContentMapper;
import com.visionki.wechat.mapper.WechatMessageMapper;
import com.visionki.wechat.mapper.WechatOfficialArticleMapper;
import com.visionki.wechat.model.WechatArticleContent;
import com.visionki.wechat.model.WechatMessage;
import com.visionki.wechat.model.WechatOfficialArticle;
import com.visionki.wechat.service.MediaService;
import com.visionki.wechat.util.DateUtils;
import com.visionki.wechat.util.UUIDUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 3:31
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private WechatMessageMapper wechatMessageMapper;
    @Autowired
    private WechatArticleContentMapper wechatArticleContentMapper;
    @Autowired
    private WechatOfficialArticleMapper wechatOfficialArticleMapper;
    @Autowired
    private WxMpService wxMpService;

    @Override
    public List<WechatMessage> select(WechatMessage wechatMessage) {
        return wechatMessageMapper.select(wechatMessage);
    }

    @Override
    public int addMedia(WechatMessage wechatMessage) {
        wechatMessage.setId(UUIDUtils.uuid());
        wechatMessage.setCreateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        wechatMessage.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        return wechatMessageMapper.insert(wechatMessage);
    }

    @Override
    public int updateMedia(WechatMessage wechatMessage) {
        wechatMessage.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        return wechatMessageMapper.updateByPrimaryKeySelective(wechatMessage);
    }

    @Override
    public int deleteMedia(String id) {
        WechatMessage wechatMessage = wechatMessageMapper.selectByPrimaryKey(id);
        if (wechatMessage.getMsgType() == 5){
            // 图文素材,还需要删除内容
            WechatArticleContent wechatArticleContent = new WechatArticleContent();
            wechatArticleContent.setMessageId(id);
            wechatArticleContentMapper.delete(wechatArticleContent);
        }
        return wechatMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WechatArticleContent> getArticleList(String id) {
        WechatArticleContent wechatArticleContent = new WechatArticleContent();
        wechatArticleContent.setMessageId(id);
        return wechatArticleContentMapper.select(wechatArticleContent);
    }

    @Override
    public int addArticle(WechatArticleContent wechatArticleContent) {
        wechatArticleContent.setId(UUIDUtils.uuid());
        wechatArticleContent.setCreateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        wechatArticleContent.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
        return wechatArticleContentMapper.insert(wechatArticleContent);
    }

    @Override
    public int updateArticle(WechatArticleContent wechatArticleContent) {
        return wechatArticleContentMapper.updateByPrimaryKeySelective(wechatArticleContent);
    }

    @Override
    public int deleteArticle(String id) {
        return wechatArticleContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WechatOfficialArticle> getOfficialArticle() {
        return wechatOfficialArticleMapper.selectAll();
    }

    @Override
    public int officialArticleSynchronization() {
        // 删除全部
        wechatOfficialArticleMapper.delete(new WechatOfficialArticle());
        for (int i = 0;;i++){
            try {
                WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGetResult = wxMpService.getMaterialService().materialNewsBatchGet(i * 20, 20);
                List<WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem> items = wxMpMaterialNewsBatchGetResult.getItems();
                for (WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem newsItem : items){
                    WechatOfficialArticle wechatOfficialArticle = new WechatOfficialArticle();
                    // 取第一个作为封面
                    wechatOfficialArticle.setId(UUIDUtils.uuid());
                    wechatOfficialArticle.setCoverUrl(newsItem.getContent().getArticles().get(0).getThumbUrl());
                    wechatOfficialArticle.setMediaId(newsItem.getMediaId());
                    wechatOfficialArticle.setCreateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
                    wechatOfficialArticle.setTitles("");
                    // 遍历每篇文章的标题
                    List<WxMpMaterialNews.WxMpMaterialNewsArticle> articles = newsItem.getContent().getArticles();
                    int j = 1;
                    for (WxMpMaterialNews.WxMpMaterialNewsArticle a : articles){
                        wechatOfficialArticle.setTitles(wechatOfficialArticle.getTitles() + j + "." + a.getTitle() + ";");
                        j++;
                    }
                    wechatOfficialArticleMapper.insert(wechatOfficialArticle);
                }
                if (wxMpMaterialNewsBatchGetResult.getItemCount() < 20){
                    break;
                }
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return wechatArticleContentMapper.selectCount(new WechatArticleContent());
    }
}
