package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_official_article")
public class WechatOfficialArticle implements Serializable {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 微信素材id
     */
    @Column(name = "media_id")
    private String mediaId;

    /**
     * 封面
     */
    @Column(name = "cover_url")
    private String coverUrl;

    /**
     * 标题
     */
    private String titles;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取微信素材id
     *
     * @return media_id - 微信素材id
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设置微信素材id
     *
     * @param mediaId 微信素材id
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    /**
     * 获取封面
     *
     * @return cover_url - 封面
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置封面
     *
     * @param coverUrl 封面
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    /**
     * 获取标题
     *
     * @return titles - 标题
     */
    public String getTitles() {
        return titles;
    }

    /**
     * 设置标题
     *
     * @param titles 标题
     */
    public void setTitles(String titles) {
        this.titles = titles == null ? null : titles.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", coverUrl=").append(coverUrl);
        sb.append(", titles=").append(titles);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}