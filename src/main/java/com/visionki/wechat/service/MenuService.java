package com.visionki.wechat.service;

import com.visionki.wechat.model.WechatMenu;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/16 23:02
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
public interface MenuService {
    /**
     * 获取我的儿子们
     * @param parentId
     * @return
     */
    List<WechatMenu> getChildrenByParentId(String parentId);

    /**
     * 创建指定id菜单
     * @param wechatMenu
     * @return
     */
    int insert(WechatMenu wechatMenu);

    /**
     * 更新指定id菜单
     * @param wechatMenu
     * @return
     */
    int update(WechatMenu wechatMenu);

    /**
     * 删除指定id菜单
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 上传菜单
     */
    void upload();
}
