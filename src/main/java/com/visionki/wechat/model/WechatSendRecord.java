package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_send_record")
public class WechatSendRecord implements Serializable {
    /**
     * 记录id
     */
    @Id
    private String id;

    /**
     * 发送对象标签
     */
    private String tag;

    /**
     * 消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；
     */
    @Column(name = "msg_type")
    private Integer msgType;

    /**
     * 素材ID
     */
    @Column(name = "media_id")
    private String mediaId;

    /**
     * 素材名称
     */
    @Column(name = "media_name")
    private String mediaName;

    /**
     * 发送成功数量
     */
    @Column(name = "success_count")
    private Integer successCount;

    /**
     * 发送失败数量
     */
    @Column(name = "fail_count")
    private Integer failCount;

    /**
     * 总人数
     */
    private Integer total;

    /**
     * 发送时间
     */
    @Column(name = "send_time")
    private String sendTime;

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
     * 获取发送对象标签
     *
     * @return tag - 发送对象标签
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置发送对象标签
     *
     * @param tag 发送对象标签
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
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
     * 获取素材ID
     *
     * @return media_id - 素材ID
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设置素材ID
     *
     * @param mediaId 素材ID
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    /**
     * 获取素材名称
     *
     * @return media_name - 素材名称
     */
    public String getMediaName() {
        return mediaName;
    }

    /**
     * 设置素材名称
     *
     * @param mediaName 素材名称
     */
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName == null ? null : mediaName.trim();
    }

    /**
     * 获取发送成功数量
     *
     * @return success_count - 发送成功数量
     */
    public Integer getSuccessCount() {
        return successCount;
    }

    /**
     * 设置发送成功数量
     *
     * @param successCount 发送成功数量
     */
    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    /**
     * 获取发送失败数量
     *
     * @return fail_count - 发送失败数量
     */
    public Integer getFailCount() {
        return failCount;
    }

    /**
     * 设置发送失败数量
     *
     * @param failCount 发送失败数量
     */
    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    /**
     * 获取总人数
     *
     * @return total - 总人数
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置总人数
     *
     * @param total 总人数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取发送时间
     *
     * @return send_time - 发送时间
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * 设置发送时间
     *
     * @param sendTime 发送时间
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tag=").append(tag);
        sb.append(", msgType=").append(msgType);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", mediaName=").append(mediaName);
        sb.append(", successCount=").append(successCount);
        sb.append(", failCount=").append(failCount);
        sb.append(", total=").append(total);
        sb.append(", sendTime=").append(sendTime);
        sb.append("]");
        return sb.toString();
    }
}