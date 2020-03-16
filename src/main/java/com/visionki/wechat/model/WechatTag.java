package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_tag")
public class WechatTag implements Serializable {
    /**
     * 记录id
     */
    @Id
    private String id;

    /**
     * 微信分配的标签id
     */
    @Column(name = "tag_id")
    private Long tagId;

    /**
     * 标签名称
     */
    @Column(name = "tag_name")
    private String tagName;

    /**
     * 该标签对应的关注二维码
     */
    @Column(name = "qrcode_url")
    private String qrcodeUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录id
     *
     * @return id - 记录id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录id
     *
     * @param id 记录id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取微信分配的标签id
     *
     * @return tag_id - 微信分配的标签id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * 设置微信分配的标签id
     *
     * @param tagId 微信分配的标签id
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取标签名称
     *
     * @return tag_name - 标签名称
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签名称
     *
     * @param tagName 标签名称
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * 获取该标签对应的关注二维码
     *
     * @return qrcode_url - 该标签对应的关注二维码
     */
    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    /**
     * 设置该标签对应的关注二维码
     *
     * @param qrcodeUrl 该标签对应的关注二维码
     */
    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl == null ? null : qrcodeUrl.trim();
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

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tagId=").append(tagId);
        sb.append(", tagName=").append(tagName);
        sb.append(", qrcodeUrl=").append(qrcodeUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}