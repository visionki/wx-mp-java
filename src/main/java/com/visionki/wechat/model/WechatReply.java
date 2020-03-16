package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_reply")
public class WechatReply implements Serializable {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 关键词
     */
    @Column(name = "key_word")
    private String keyWord;

    /**
     * 匹配类型（0-精确，1-模糊）
     */
    @Column(name = "matching_type")
    private Integer matchingType;

    /**
     * 消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；6、图文（文章）
     */
    @Column(name = "msg_type")
    private Integer msgType;

    /**
     * 回复消息id
     */
    @Column(name = "message_id")
    private String messageId;

    /**
     * 素材名称
     */
    @Column(name = "message_name")
    private String messageName;

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
     * 获取关键词
     *
     * @return key_word - 关键词
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * 设置关键词
     *
     * @param keyWord 关键词
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    /**
     * 获取匹配类型（0-精确，1-模糊）
     *
     * @return matching_type - 匹配类型（0-精确，1-模糊）
     */
    public Integer getMatchingType() {
        return matchingType;
    }

    /**
     * 设置匹配类型（0-精确，1-模糊）
     *
     * @param matchingType 匹配类型（0-精确，1-模糊）
     */
    public void setMatchingType(Integer matchingType) {
        this.matchingType = matchingType;
    }

    /**
     * 获取消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；6、图文（文章）
     *
     * @return msg_type - 消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；6、图文（文章）
     */
    public Integer getMsgType() {
        return msgType;
    }

    /**
     * 设置消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；6、图文（文章）
     *
     * @param msgType 消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；6、图文（文章）
     */
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    /**
     * 获取回复消息id
     *
     * @return message_id - 回复消息id
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * 设置回复消息id
     *
     * @param messageId 回复消息id
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    /**
     * 获取素材名称
     *
     * @return message_name - 素材名称
     */
    public String getMessageName() {
        return messageName;
    }

    /**
     * 设置素材名称
     *
     * @param messageName 素材名称
     */
    public void setMessageName(String messageName) {
        this.messageName = messageName == null ? null : messageName.trim();
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
        sb.append(", keyWord=").append(keyWord);
        sb.append(", matchingType=").append(matchingType);
        sb.append(", msgType=").append(msgType);
        sb.append(", messageId=").append(messageId);
        sb.append(", messageName=").append(messageName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}