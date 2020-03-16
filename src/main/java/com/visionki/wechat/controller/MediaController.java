package com.visionki.wechat.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.regexp.internal.RE;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.model.WechatArticleContent;
import com.visionki.wechat.model.WechatMessage;
import com.visionki.wechat.model.WechatOfficialArticle;
import com.visionki.wechat.service.MediaService;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 17:50
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 素材管理
 */
@RestController
@RequestMapping("/manage/media")
public class MediaController {
    @Autowired
    private MediaService mediaService;
    @Autowired
    private WxMpService wxMpService;

    /**
     * 根据条件获取素材列表
     * @param pageNo
     * @param pageSize
     * @param wechatMessage
     * @return
     */
    @GetMapping
    public R list(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize, WechatMessage wechatMessage) {
        Page<WechatMessage> page = PageHelper.startPage(pageNo, pageSize);
        List<WechatMessage> wechatMessageList = mediaService.select(wechatMessage);
        return RUtil.success(page, wechatMessageList);
    }

    @PostMapping
    public R addMedia(@RequestBody WechatMessage wechatMessage){
        int count = mediaService.addMedia(wechatMessage);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public R deleteMedia(@PathVariable String id){
        int count = mediaService.deleteMedia(id);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @PutMapping
    public R updateMedia(@RequestBody WechatMessage wechatMessage){
        int count = mediaService.updateMedia(wechatMessage);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    /**
     * 获取图文列表
     * @param id
     * @return
     */
    @GetMapping("/articleList/{id}")
    public R getArticleList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize, @PathVariable String id){
        Page<WechatArticleContent> page = PageHelper.startPage(pageNo, pageSize,"sort asc");
        List<WechatArticleContent> wechatArticleContentList = mediaService.getArticleList(id);
        return RUtil.success(page, wechatArticleContentList);
    }

    @PostMapping("/article")
    public R addArticle(@RequestBody WechatArticleContent wechatArticleContent){
        int count = mediaService.addArticle(wechatArticleContent);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @PutMapping("/article")
    public R updateArticle(@RequestBody WechatArticleContent wechatArticleContent){
        int count = mediaService.updateArticle(wechatArticleContent);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @DeleteMapping("/article/{id}")
    public R deleteArticle(@PathVariable String id){
        int count = mediaService.deleteArticle(id);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    /**
     * 获取官方图文列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/officialArticle")
    public R officialArticle(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize) {
        Page<WechatOfficialArticle> page = PageHelper.startPage(pageNo, pageSize);
        List<WechatOfficialArticle> wechatOfficialArticleList = mediaService.getOfficialArticle();
        return RUtil.success(page, wechatOfficialArticleList);
    }

    @GetMapping("/officialArticle/synchronization")
    public R officialArticleSynchronization(){
        int count = mediaService.officialArticleSynchronization();
        return RUtil.success("同步了" + count + "条官方图文");
    }


    @GetMapping("/test")
    public R test() throws WxErrorException {
        WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGetResult = wxMpService.getMaterialService().materialNewsBatchGet(0, 20);
        return RUtil.success(wxMpMaterialNewsBatchGetResult);
    }
}
