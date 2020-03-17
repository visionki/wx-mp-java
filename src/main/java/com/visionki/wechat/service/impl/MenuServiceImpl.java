package com.visionki.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.visionki.wechat.constant.MenuTypeConst;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.exceptions.BaseException;
import com.visionki.wechat.mapper.WechatMenuMapper;
import com.visionki.wechat.model.WechatMenu;
import com.visionki.wechat.service.MenuService;
import com.visionki.wechat.util.DateUtils;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.util.UUIDUtils;
import com.visionki.wechat.vo.R;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/16 23:03
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private WechatMenuMapper wechatMenuMapper;
    @Autowired
    private WxMpService wxMpService;

    @Override
    public List<WechatMenu> getChildrenByParentId(String parentId) {
        WechatMenu wechatMenu = new WechatMenu();
        wechatMenu.setParentId(parentId);
        return wechatMenuMapper.select(wechatMenu);
    }

    @Override
    public int insert(WechatMenu wechatMenu) {
        int count;
        if (MenuTypeConst.PARENT_ID.equals(wechatMenu.getParentId())){
            // 新增的是父级菜单
            // 判断数量超不超过三个
            WechatMenu temp = new WechatMenu();
            temp.setParentId(MenuTypeConst.PARENT_ID);
            if (wechatMenuMapper.selectCount(temp) >= MenuTypeConst.PARENT_MENU_COUNT){
                throw new BaseException(REnum.TOO_MUCH_PARENT_MENU);
            }
            wechatMenu.setId(UUIDUtils.uuid());
            // 父级id设置为0
            wechatMenu.setParentId(MenuTypeConst.PARENT_ID);
            wechatMenu.setCreateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
            wechatMenu.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
            count = wechatMenuMapper.insert(wechatMenu);
        }else{
            // 新增的是子菜单
            // 查出父级菜单先
            WechatMenu parent = wechatMenuMapper.selectByPrimaryKey(wechatMenu.getParentId());
            if (parent == null){
                throw new BaseException(REnum.PARENT_MENU_NOT_EXISTS);
            }
            // 查查兄弟超过5个没
            WechatMenu temp = new WechatMenu();
            temp.setParentId(parent.getId());
            if (wechatMenuMapper.selectCount(temp) >= MenuTypeConst.CHILDREN_MENU_COUNT){
                throw new BaseException(REnum.TOO_MUCH_CHILDREN_MENU);
            }
            // 满足，入库
            wechatMenu.setId(UUIDUtils.uuid());
            wechatMenu.setCreateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
            wechatMenu.setUpdateTime(DateUtils.getCurrentDateTime(DateUtils.DATE_TIME_FORMAT));
            count = wechatMenuMapper.insert(wechatMenu);
        }
        return count;
    }

    @Override
    public int update(WechatMenu wechatMenu) {
        // 获取原来的菜单信息
        WechatMenu source = wechatMenuMapper.selectByPrimaryKey(wechatMenu.getId());
        if (MenuTypeConst.PARENT_ID.equals(source.getParentId()) && !source.getType().equals(wechatMenu.getType())){
            // 父级菜单类型，但是想改类型，检查是否有子菜单
            WechatMenu temp = new WechatMenu();
            temp.setParentId(source.getId());
            if (wechatMenuMapper.selectCount(temp) > 0){
                // 有子菜单，不允许改变类型
                throw new BaseException(REnum.CHILDREN_MENU_IS_EXISTS);
            }
        }
        // 能改的也就 名字、类型、内容，排序 字段
        source.setName(wechatMenu.getName());
        source.setType(wechatMenu.getType());
        source.setContent(wechatMenu.getContent());
        source.setSort(wechatMenu.getSort());
        return wechatMenuMapper.updateByPrimaryKey(source);
    }

    @Override
    public int delete(String id) {
        WechatMenu wechatMenu = wechatMenuMapper.selectByPrimaryKey(id);
        if (wechatMenu == null){
            throw new BaseException(REnum.DATA_NOT_EXISTS);
        }
        if (MenuTypeConst.PARENT_ID.equals(wechatMenu.getParentId())){
            // 如果是父菜单则删除所有子菜单
            WechatMenu temp = new WechatMenu();
            temp.setParentId(wechatMenu.getId());
            List<WechatMenu> select = wechatMenuMapper.select(temp);
            for (WechatMenu w : select){
                wechatMenuMapper.deleteByPrimaryKey(w.getId());
            }
        }
        return wechatMenuMapper.deleteByPrimaryKey(id);
    }


    /**
     * 上传菜单
     */
    @Override
    public void upload() {
        WxMenu menu = new WxMenu();
        List<WxMenuButton> buttonList = new ArrayList<>();
        menu.setButtons(buttonList);
        PageHelper.startPage(1,1000,"sort asc");
        List<WechatMenu> wechatMenus = wechatMenuMapper.selectAll();
        for (WechatMenu wechatMenu : wechatMenus){
            // 遍历菜单
            if (wechatMenu.getParentId().equals(MenuTypeConst.PARENT_ID)){
                // 如果是父级ID
                WxMenuButton parent = new WxMenuButton();
                parent.setName(wechatMenu.getName());
                if (wechatMenu.getType().equals(MenuTypeConst.PARENT)){
                    // 如果是父级菜单，则
                    // 遍历儿子们
                    for (WechatMenu temp : wechatMenus){
                        // 如果父级id等于外层id，则属于外层id的子菜单
                        if (temp.getParentId().equals(wechatMenu.getId())){
                            WxMenuButton children = new WxMenuButton();
                            children.setName(temp.getName());
                            children.setType(temp.getType());
                            if (temp.getType().equals(MenuTypeConst.VIEW)){
                                children.setUrl(temp.getContent());
                                children.setType(WxConsts.MenuButtonType.VIEW);
                            }else if (temp.getType().equals(MenuTypeConst.CLICK)){
                                children.setKey(temp.getContent());
                                children.setType(WxConsts.MenuButtonType.CLICK);
                            }
                            parent.getSubButtons().add(children);
                        }
                    }
                }else if (wechatMenu.getType().equals(MenuTypeConst.VIEW)){
                    parent.setUrl(wechatMenu.getContent());
                    parent.setType(WxConsts.MenuButtonType.VIEW);
                }else if (wechatMenu.getType().equals(MenuTypeConst.CLICK)){
                    parent.setKey(wechatMenu.getContent());
                    parent.setType(WxConsts.MenuButtonType.CLICK);
                }
                menu.getButtons().add(parent);
            }
        }
        try {
            wxMpService.getMenuService().menuCreate(menu);
        } catch (WxErrorException e) {
            e.printStackTrace();
            if (e.getError().getErrorCode() == 40018){
                throw new BaseException(REnum.MENU_CREATE_ERROR_NAME_TOO_LONG);
            }else if (e.getError().getErrorCode() == 40017){
                throw new BaseException(REnum.MENU_CREATE_ERROR_COUNT);
            }else {
                throw new BaseException(REnum.ERROR);
            }
        }
    }

}
