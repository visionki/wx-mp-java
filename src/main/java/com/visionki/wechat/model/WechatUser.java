package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_user")
public class WechatUser implements Serializable {
    /**
     * 主键ID
     */
    @Id
    private String id;

    /**
     * 0-未关注，1-关注
     */
    private Integer subscribe;

    /**
     * 对应公众号唯一id
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 0-未知，1-男，2-女
     */
    private Integer sex;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 语言
     */
    private String language;

    /**
     * 头像
     */
    @Column(name = "head_url")
    private String headUrl;

    /**
     * 关注时间
     */
    @Column(name = "subscribe_time")
    private String subscribeTime;

    /**
     * 开放平台上绑定的唯一id
     */
    @Column(name = "union_id")
    private String unionId;

    /**
     * 公众号运营者对粉丝的备注
     */
    private String remark;

    /**
     * 用户所在的分组ID
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 用户被打上的标签
     */
    @Column(name = "tag_name")
    private String tagName;

    /**
     * 创建时间（首次关注时间）
     */
    @Column(name = "created_time")
    private String createdTime;

    /**
     * 更新时间（取消关注时间）
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 0正常，1调试
     */
    private Integer debug;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取0-未关注，1-关注
     *
     * @return subscribe - 0-未关注，1-关注
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    /**
     * 设置0-未关注，1-关注
     *
     * @param subscribe 0-未关注，1-关注
     */
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * 获取对应公众号唯一id
     *
     * @return open_id - 对应公众号唯一id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置对应公众号唯一id
     *
     * @param openId 对应公众号唯一id
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取0-未知，1-男，2-女
     *
     * @return sex - 0-未知，1-男，2-女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置0-未知，1-男，2-女
     *
     * @param sex 0-未知，1-男，2-女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    /**
     * 获取头像
     *
     * @return head_url - 头像
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * 设置头像
     *
     * @param headUrl 头像
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    /**
     * 获取关注时间
     *
     * @return subscribe_time - 关注时间
     */
    public String getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * 设置关注时间
     *
     * @param subscribeTime 关注时间
     */
    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime == null ? null : subscribeTime.trim();
    }

    /**
     * 获取开放平台上绑定的唯一id
     *
     * @return union_id - 开放平台上绑定的唯一id
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 设置开放平台上绑定的唯一id
     *
     * @param unionId 开放平台上绑定的唯一id
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    /**
     * 获取公众号运营者对粉丝的备注
     *
     * @return remark - 公众号运营者对粉丝的备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置公众号运营者对粉丝的备注
     *
     * @param remark 公众号运营者对粉丝的备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取用户所在的分组ID
     *
     * @return group_id - 用户所在的分组ID
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置用户所在的分组ID
     *
     * @param groupId 用户所在的分组ID
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取用户被打上的标签
     *
     * @return tag_name - 用户被打上的标签
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置用户被打上的标签
     *
     * @param tagName 用户被打上的标签
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * 获取创建时间（首次关注时间）
     *
     * @return created_time - 创建时间（首次关注时间）
     */
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间（首次关注时间）
     *
     * @param createdTime 创建时间（首次关注时间）
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime == null ? null : createdTime.trim();
    }

    /**
     * 获取更新时间（取消关注时间）
     *
     * @return update_time - 更新时间（取消关注时间）
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间（取消关注时间）
     *
     * @param updateTime 更新时间（取消关注时间）
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * 获取0正常，1调试
     *
     * @return debug - 0正常，1调试
     */
    public Integer getDebug() {
        return debug;
    }

    /**
     * 设置0正常，1调试
     *
     * @param debug 0正常，1调试
     */
    public void setDebug(Integer debug) {
        this.debug = debug;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", subscribe=").append(subscribe);
        sb.append(", openId=").append(openId);
        sb.append(", nickName=").append(nickName);
        sb.append(", sex=").append(sex);
        sb.append(", city=").append(city);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", language=").append(language);
        sb.append(", headUrl=").append(headUrl);
        sb.append(", subscribeTime=").append(subscribeTime);
        sb.append(", unionId=").append(unionId);
        sb.append(", remark=").append(remark);
        sb.append(", groupId=").append(groupId);
        sb.append(", tagName=").append(tagName);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", debug=").append(debug);
        sb.append("]");
        return sb.toString();
    }
}