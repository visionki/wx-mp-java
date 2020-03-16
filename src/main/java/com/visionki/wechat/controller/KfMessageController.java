package com.visionki.wechat.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.visionki.wechat.model.WechatSendRecord;
import com.visionki.wechat.param.SendKfMessageParam;
import com.visionki.wechat.service.KfMessageService;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/15 23:01
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@RestController
@RequestMapping("/manage/kfMessage")
public class KfMessageController {

    @Autowired
    private KfMessageService kfMessageService;

    @PostMapping("/send")
    public R sendKfMessage(@RequestBody SendKfMessageParam sendKfMessageParam){
        int count = kfMessageService.sendToTag(sendKfMessageParam);
        return RUtil.success("成功发送:" + count + "人,详细情况请前往发送记录查看");
    }

    @GetMapping("/record")
    public R record(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize){
        Page<WechatSendRecord> page = PageHelper.startPage(pageNo, pageSize, "send_time desc");
        List<WechatSendRecord> wechatSendRecordList = kfMessageService.getSendRecord();
        return RUtil.success(page, wechatSendRecordList);
    }

}
