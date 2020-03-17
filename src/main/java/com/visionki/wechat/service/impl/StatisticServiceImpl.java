package com.visionki.wechat.service.impl;

import com.visionki.wechat.mapper.WechatUserStatisticDetailMapper;
import com.visionki.wechat.mapper.WechatUserStatisticMapper;
import com.visionki.wechat.model.WechatUserStatistic;
import com.visionki.wechat.model.WechatUserStatisticDetail;
import com.visionki.wechat.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/17 13:58
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private WechatUserStatisticMapper wechatUserStatisticMapper;
    @Autowired
    private WechatUserStatisticDetailMapper wechatUserStatisticDetailMapper;

    @Override
    public WechatUserStatistic getYesterdayStatistic() {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sp.format(d);
        WechatUserStatistic wechatUserStatistic = new WechatUserStatistic();
        wechatUserStatistic.setRecordTime(dateStr);
        wechatUserStatistic = wechatUserStatisticMapper.selectOne(wechatUserStatistic);
        if (wechatUserStatistic == null){
            wechatUserStatistic = new WechatUserStatistic();
            wechatUserStatistic.setIncreaseNumber(0);
            wechatUserStatistic.setSubscribeNumber(0);
            wechatUserStatistic.setUnsubscribeNumber(0);
            wechatUserStatistic.setTotalNumber(0);
        }
        return wechatUserStatistic;
    }

    @Override
    public List<WechatUserStatistic> selectAll() {
        return wechatUserStatisticMapper.selectAll();
    }

    @Override
    public List<WechatUserStatisticDetail> getDetailList(String recordTime) {
        WechatUserStatisticDetail wechatUserStatisticDetail = new WechatUserStatisticDetail();
        wechatUserStatisticDetail.setRecordTime(recordTime);
        return wechatUserStatisticDetailMapper.select(wechatUserStatisticDetail);
    }
}
