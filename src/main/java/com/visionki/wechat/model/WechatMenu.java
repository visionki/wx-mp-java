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
     * 上级菜单的id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 按钮类型（parent、view、click）
     */
    private String type;

    /**
     * 内容（keyword或url）
     */
    private String content;

    /**
     * 顺序编号，升序
     */
    private Integer sort;

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
     * 获取按钮类型（parent、view、click）
     *
     * @return type - 按钮类型（parent、view、click）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置按钮类型（parent、view、click）
     *
     * @param type 按钮类型（parent、view、click）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取内容（keyword或url）
     *
     * @return content - 内容（keyword或url）
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容（keyword或url）
     *
     * @param content 内容（keyword或url）
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取顺序编号，升序
     *
     * @return sort - 顺序编号，升序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置顺序编号，升序
     *
     * @param sort 顺序编号，升序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}