package com.visionki.wechat.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.visionki.wechat.constant.MenuTypeConst;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.model.WechatMenu;
import com.visionki.wechat.service.MenuService;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/16 23:02
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 菜单管理
 */
@RestController
@RequestMapping("/manage/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;


    @GetMapping("/{parentId}/children")
    public R list(@PathVariable String parentId){
        // 0 是父级
        Page<WechatMenu> page = PageHelper.startPage(1, 100, "sort asc");
        List<WechatMenu> wechatMenuList = menuService.getChildrenByParentId(parentId);
        return RUtil.success(page, wechatMenuList);
    }

    @PostMapping
    public R addMenu(@RequestBody WechatMenu wechatMenu){
        int count = menuService.insert(wechatMenu);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @PutMapping
    public R updateMenu(@RequestBody WechatMenu wechatMenu){
        int count = menuService.update(wechatMenu);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id){
        int count = menuService.delete(id);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }


    @GetMapping("/upload")
    public R upload(){
        menuService.upload();
        return RUtil.success();
    }





}
