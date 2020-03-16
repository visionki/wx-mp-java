package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_message")
public class WechatMessage implements Serializable {
    /**
     * 记录id
     */
    @Id
    private String id;

    /**
     * 素材名称
     */
    private String name;

    /**
     * 消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；
     */
    @Column(name = "msg_type")
    private Integer msgType;

    /**
     * 内容（文本消息）
     */
    private String content;

    /**
     * 媒体id（图片消息、语音消息、视频消息、音乐消息）
     */
    @Column(name = "media_id")
    private String mediaId;

    /**
     * 标题（视频消息、音乐消息）
     */
    private String title;

    /**
     * 描述（视频消息、音乐消息）
     */
    private String description;

    /**
     * 音乐链接（音乐消息）
     */
    @Column(name = "music_url")
    private String musicUrl;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐（音乐消息）
     */
    @Column(name = "hq_music_url")
    private String hqMusicUrl;

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
     * 获取素材名称
     *
     * @return name - 素材名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置素材名称
     *
     * @param name 素材名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；
     *
     * @return msg_type - 消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；
     */
    public Integer getMsgType() {
        return msgType;
    }

    /**
     * 设置消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；
     *
     * @param msgType 消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；
     */
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    /**
     * 获取内容（文本消息）
     *
     * @return content - 内容（文本消息）
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容（文本消息）
     *
     * @param content 内容（文本消息）
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取媒体id（图片消息、语音消息、视频消息、音乐消息）
     *
     * @return media_id - 媒体id（图片消息、语音消息、视频消息、音乐消息）
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设置媒体id（图片消息、语音消息、视频消息、音乐消息）
     *
     * @param mediaId 媒体id（图片消息、语音消息、视频消息、音乐消息）
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    /**
     * 获取标题（视频消息、音乐消息）
     *
     * @return title - 标题（视频消息、音乐消息）
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题（视频消息、音乐消息）
     *
     * @param title 标题（视频消息、音乐消息）
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取描述（视频消息、音乐消息）
     *
     * @return description - 描述（视频消息、音乐消息）
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述（视频消息、音乐消息）
     *
     * @param description 描述（视频消息、音乐消息）
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取音乐链接（音乐消息）
     *
     * @return music_url - 音乐链接（音乐消息）
     */
    public String getMusicUrl() {
        return musicUrl;
    }

    /**
     * 设置音乐链接（音乐消息）
     *
     * @param musicUrl 音乐链接（音乐消息）
     */
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl == null ? null : musicUrl.trim();
    }

    /**
     * 获取高质量音乐链接，WIFI环境优先使用该链接播放音乐（音乐消息）
     *
     * @return hq_music_url - 高质量音乐链接，WIFI环境优先使用该链接播放音乐（音乐消息）
     */
    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    /**
     * 设置高质量音乐链接，WIFI环境优先使用该链接播放音乐（音乐消息）
     *
     * @param hqMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐（音乐消息）
     */
    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl == null ? null : hqMusicUrl.trim();
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
        sb.append(", name=").append(name);
        sb.append(", msgType=").append(msgType);
        sb.append(", content=").append(content);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", musicUrl=").append(musicUrl);
        sb.append(", hqMusicUrl=").append(hqMusicUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}