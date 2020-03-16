package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_user_statistic")
public class WechatUserStatistic implements Serializable {
    /**
     * 记录id
     */
    @Id
    private String id;

    /**
     * 新增人数
     */
    @Column(name = "subscribe_number")
    private Integer subscribeNumber;

    /**
     * 取关人数
     */
    @Column(name = "unsubscribe_number")
    private Integer unsubscribeNumber;

    /**
     * 净增人数
     */
    @Column(name = "increase_number")
    private Integer increaseNumber;

    /**
     * 统计日期
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
     * 获取新增人数
     *
     * @return subscribe_number - 新增人数
     */
    public Integer getSubscribeNumber() {
        return subscribeNumber;
    }

    /**
     * 设置新增人数
     *
     * @param subscribeNumber 新增人数
     */
    public void setSubscribeNumber(Integer subscribeNumber) {
        this.subscribeNumber = subscribeNumber;
    }

    /**
     * 获取取关人数
     *
     * @return unsubscribe_number - 取关人数
     */
    public Integer getUnsubscribeNumber() {
        return unsubscribeNumber;
    }

    /**
     * 设置取关人数
     *
     * @param unsubscribeNumber 取关人数
     */
    public void setUnsubscribeNumber(Integer unsubscribeNumber) {
        this.unsubscribeNumber = unsubscribeNumber;
    }

    /**
     * 获取净增人数
     *
     * @return increase_number - 净增人数
     */
    public Integer getIncreaseNumber() {
        return increaseNumber;
    }

    /**
     * 设置净增人数
     *
     * @param increaseNumber 净增人数
     */
    public void setIncreaseNumber(Integer increaseNumber) {
        this.increaseNumber = increaseNumber;
    }

    /**
     * 获取统计日期
     *
     * @return record_time - 统计日期
     */
    public String getRecordTime() {
        return recordTime;
    }

    /**
     * 设置统计日期
     *
     * @param recordTime 统计日期
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
        sb.append(", subscribeNumber=").append(subscribeNumber);
        sb.append(", unsubscribeNumber=").append(unsubscribeNumber);
        sb.append(", increaseNumber=").append(increaseNumber);
        sb.append(", recordTime=").append(recordTime);
        sb.append("]");
        return sb.toString();
    }
}