package com.visionki.wechat.task;

import com.visionki.wechat.mapper.WechatTagMapper;
import com.visionki.wechat.mapper.WechatUserMapper;
import com.visionki.wechat.mapper.WechatUserStatisticDetailMapper;
import com.visionki.wechat.mapper.WechatUserStatisticMapper;
import com.visionki.wechat.model.WechatTag;
import com.visionki.wechat.model.WechatUser;
import com.visionki.wechat.model.WechatUserStatistic;
import com.visionki.wechat.model.WechatUserStatisticDetail;
import com.visionki.wechat.util.DateUtils;
import com.visionki.wechat.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/16 20:53
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 每日凌晨1点执行统计
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class UserStatisticTask {



    @Autowired
    private WechatUserMapper wechatUserMapper;
    @Autowired
    private WechatUserStatisticMapper wechatUserStatisticMapper;
    @Autowired
    private WechatUserStatisticDetailMapper wechatUserStatisticDetailMapper;
    @Autowired
    private WechatTagMapper wechatTagMapper;

    /**
     * 每天凌晨12点05分运行定时任务，记录
     */
    @Scheduled(cron = "0 5 0 * * ?")
    private void configureTasks() {
        // 新增人数：subscribe_time = 昨天
        // 取关人数：subscribe = 0 并且 update_time = 昨天
        // 净增人数：新增人数 - 取关人数 （subscribe = 1 并且 subscribe_time = 昨天）

        // 获取昨天日期
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sp.format(d);
        // 新增人数
        int subscribeNumber = wechatUserMapper.getSubscribeCountByDate(dateStr);
        // 取关人数
        int unsubscribeNumber = wechatUserMapper.getUnsubscribeCountByDate(dateStr);
        // 净增人数
        int increaseNumber = subscribeNumber - unsubscribeNumber;
        WechatUserStatistic wechatUserStatistic = new WechatUserStatistic();
        wechatUserStatistic.setId(UUIDUtils.uuid());
        wechatUserStatistic.setRecordTime(dateStr);
        wechatUserStatistic.setSubscribeNumber(subscribeNumber);
        wechatUserStatistic.setUnsubscribeNumber(unsubscribeNumber);
        wechatUserStatistic.setIncreaseNumber(increaseNumber);
        wechatUserStatisticMapper.insert(wechatUserStatistic);
        // 接下来查询每天每个标签的关注人数
        List<WechatTag> wechatTags = wechatTagMapper.selectAll();
        for (WechatTag wechatTag : wechatTags){
            WechatUserStatisticDetail wechatUserStatisticDetail = new WechatUserStatisticDetail();
            wechatUserStatisticDetail.setId(UUIDUtils.uuid());
            wechatUserStatisticDetail.setTagName(wechatTag.getTagName());
            wechatUserStatisticDetail.setNumber(wechatUserMapper.getSubscribeCountByDateAndTagName(dateStr,wechatTag.getTagName()));
            wechatUserStatisticDetail.setRecordTime(dateStr);
            wechatUserStatisticDetailMapper.insert(wechatUserStatisticDetail);
        }
    }

}
