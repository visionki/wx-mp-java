package com.visionki.wechat.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.visionki.wechat.model.WechatMessage;
import com.visionki.wechat.model.WechatUserStatistic;
import com.visionki.wechat.model.WechatUserStatisticDetail;
import com.visionki.wechat.service.StatisticService;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/17 13:29
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@RestController
@RequestMapping("/manage/statistic")
public class IndexController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/info")
    public R statisticInfo(){
        return RUtil.success(statisticService.getYesterdayStatistic());
    }

    @GetMapping
    public R getList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize){
        Page<WechatUserStatistic> page = PageHelper.startPage(pageNo, pageSize,"record_time desc");
        List<WechatUserStatistic> wechatUserStatisticList = statisticService.selectAll();
        return RUtil.success(page, wechatUserStatisticList);
    }

    @GetMapping("/detail")
    public R getDetailList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize,String recordTime){
        Page<WechatUserStatisticDetail> page = PageHelper.startPage(pageNo, pageSize,"record_time desc");
        List<WechatUserStatisticDetail> list = statisticService.getDetailList(recordTime);
        return RUtil.success(page, list);
    }

}
