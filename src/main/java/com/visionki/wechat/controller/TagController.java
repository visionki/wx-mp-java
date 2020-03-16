package com.visionki.wechat.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.exceptions.BaseException;
import com.visionki.wechat.model.WechatTag;
import com.visionki.wechat.service.TagService;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 14:57
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@RestController
@RequestMapping("/manage/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 获取标签列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping
    public R list(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize) {
        Page<WechatTag> page = PageHelper.startPage(pageNo, pageSize);
        List<WechatTag> wechatTagList = tagService.getList();
        return RUtil.success(page,wechatTagList);
    }

    /**
     * 获取指定id的标签
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getTag(@PathVariable String id){
        WechatTag wechatTag = tagService.getTag(id);
        return RUtil.success(wechatTag);
    }

    /**
     * 新增用户标签
     * @param wechatTag
     * @return
     */
    @PostMapping
    public R newTag(@RequestBody WechatTag wechatTag){
        int count;
        try {
            count = tagService.newTag(wechatTag);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BaseException(REnum.ERROR);
        }
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public R deleteTag(@PathVariable String id){
        int count;
        try {
            count = tagService.deleteTag(id);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BaseException(REnum.ERROR);
        }
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @PutMapping
    public R updateTag(@RequestBody WechatTag wechatTag){
        int count;
        try {
            count = tagService.updateTag(wechatTag);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new BaseException(REnum.ERROR);
        }
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

}
