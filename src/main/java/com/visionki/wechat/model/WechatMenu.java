package com.visionki.wechat.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "wechat_menu")
public class WechatMenu implements Serializable {
    /**
     * 记录id
     */
    @Id
    private String id;

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 按钮类型（详见工具包内WxConsts类）
     */
    private String type;

    /**
     * click等点击类型必须
     */
    private String key;

    /**
     * 网页链接（版本低时推小程序打不开的替代网址）
     */
    private String url;

    /**
     * 素材id
     */
    @Column(name = "media_id2")
    private String mediaId2;

    /**
     * 小程序的appid（仅认证公众号可配置）
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 小程序页面路径
     */
    @Column(name = "page_path")
    private String pagePath;

    /**
     * 上级菜单的id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 顺序编号，升序
     */
    @Column(name = "sort_number")
    private Integer sortNumber;

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
     * 获取按钮名称
     *
     * @return name - 按钮名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置按钮名称
     *
     * @param name 按钮名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取按钮类型（详见工具包内WxConsts类）
     *
     * @return type - 按钮类型（详见工具包内WxConsts类）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置按钮类型（详见工具包内WxConsts类）
     *
     * @param type 按钮类型（详见工具包内WxConsts类）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取click等点击类型必须
     *
     * @return key - click等点击类型必须
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置click等点击类型必须
     *
     * @param key click等点击类型必须
     */
    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    /**
     * 获取网页链接（版本低时推小程序打不开的替代网址）
     *
     * @return url - 网页链接（版本低时推小程序打不开的替代网址）
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置网页链接（版本低时推小程序打不开的替代网址）
     *
     * @param url 网页链接（版本低时推小程序打不开的替代网址）
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取素材id
     *
     * @return media_id2 - 素材id
     */
    public String getMediaId2() {
        return mediaId2;
    }

    /**
     * 设置素材id
     *
     * @param mediaId2 素材id
     */
    public void setMediaId2(String mediaId2) {
        this.mediaId2 = mediaId2 == null ? null : mediaId2.trim();
    }

    /**
     * 获取小程序的appid（仅认证公众号可配置）
     *
     * @return app_id - 小程序的appid（仅认证公众号可配置）
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置小程序的appid（仅认证公众号可配置）
     *
     * @param appId 小程序的appid（仅认证公众号可配置）
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 获取小程序页面路径
     *
     * @return page_path - 小程序页面路径
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * 设置小程序页面路径
     *
     * @param pagePath 小程序页面路径
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath == null ? null : pagePath.trim();
    }

    /**
     * 获取上级菜单的id
     *
     * @return parent_id - 上级菜单的id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上级菜单的id
     *
     * @param parentId 上级菜单的id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取顺序编号，升序
     *
     * @return sort_number - 顺序编号，升序
     */
    public Integer getSortNumber() {
        return sortNumber;
    }

    /**
     * 设置顺序编号，升序
     *
     * @param sortNumber 顺序编号，升序
     */
    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
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
        sb.append(", type=").append(type);
        sb.append(", key=").append(key);
        sb.append(", url=").append(url);
        sb.append(", mediaId2=").append(mediaId2);
        sb.append(", appId=").append(appId);
        sb.append(", pagePath=").append(pagePath);
        sb.append(", parentId=").append(parentId);
        sb.append(", sortNumber=").append(sortNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}