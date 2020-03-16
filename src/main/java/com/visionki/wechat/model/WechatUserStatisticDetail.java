package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_user_statistic_detail")
public class WechatUserStatisticDetail implements Serializable {
    /**
     * 记录id
     */
    @Id
    private String id;

    /**
     * 标签
     */
    @Column(name = "tag_name")
    private String tagName;

    /**
     * 人数
     */
    private Integer number;

    /**
     * 创建日期
     */
    @Column(name = "record_time")
    private String recordTime;

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
     * 获取标签
     *
     * @return tag_name - 标签
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签
     *
     * @param tagName 标签
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * 获取人数
     *
     * @return number - 人数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置人数
     *
     * @param number 人数
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取创建日期
     *
     * @return record_time - 创建日期
     */
    public String getRecordTime() {
        return recordTime;
    }

    /**
     * 设置创建日期
     *
     * @param recordTime 创建日期
     */
    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime == null ? null : recordTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tagName=").append(tagName);
        sb.append(", number=").append(number);
        sb.append(", recordTime=").append(recordTime);
        sb.append("]");
        return sb.toString();
    }
}