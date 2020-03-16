package com.visionki.wechat.service;

import com.visionki.wechat.model.WechatArticleContent;
import com.visionki.wechat.model.WechatMessage;
import com.visionki.wechat.model.WechatOfficialArticle;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 3:31
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
public interface MediaService {
    /**
     * 根据条件查询素材
     * @param wechatMessage
     * @return
     */
    List<WechatMessage> select(WechatMessage wechatMessage);

    /**
     * 添加素材
     * @param wechatMessage
     * @return
     */
    int addMedia(WechatMessage wechatMessage);

    /**
     * 更新指定id的素材
     * @param wechatMessage
     * @return
     */
    int updateMedia(WechatMessage wechatMessage);

    /**
     * 删除指定id的素材
     * @param id
     * @return
     */
    int deleteMedia(String id);

    /**
     * 获取指定图文素材的素材内容
     * @param id
     * @return
     */
    List<WechatArticleContent> getArticleList(String id);

    /**
     * 添加图文素材内容
     * @param wechatArticleContent
     * @return
     */
    int addArticle(WechatArticleContent wechatArticleContent);

    /**
     * 更新图文素材内容
     * @param wechatArticleContent
     * @return
     */
    int updateArticle(WechatArticleContent wechatArticleContent);

    /**
     * 删除图文素材内容
     * @param id
     * @return
     */
    int deleteArticle(String id);

    /**
     * 获取官方图文列表
     * @return
     */
    List<WechatOfficialArticle> getOfficialArticle();

    /**
     * 同步官方图文列表
     * @return
     */
    int officialArticleSynchronization();

}
