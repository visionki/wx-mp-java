package com.visionki.wechat.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.model.WechatMessage;
import com.visionki.wechat.model.WechatReply;
import com.visionki.wechat.service.ReplyService;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 15:06
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@RestController
@RequestMapping("/manage/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping
    public R list(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize) {
        Page<WechatReply> page = PageHelper.startPage(pageNo, pageSize);
        List<WechatReply> wechatReplyList = replyService.getList();
        return RUtil.success(page, wechatReplyList);
    }

    @PostMapping
    public R addReply(@RequestBody WechatReply wechatReply){
        int count = replyService.addReply(wechatReply);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @PutMapping
    public R updateReply(@RequestBody WechatReply wechatReply){
        int count = replyService.updateReply(wechatReply);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public R deleteReply(@PathVariable String id){
        int count = replyService.deleteReply(id);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }
}
