package com.visionki.wechat.service;

import com.visionki.wechat.model.WechatUserStatistic;
import com.visionki.wechat.model.WechatUserStatisticDetail;
import com.visionki.wechat.vo.R;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/17 13:58
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
public interface StatisticService {
    /**
     * 获取昨天的数据记录
     * @return
     */
    WechatUserStatistic getYesterdayStatistic();

    /**
     * 查询全部
     * @return
     */
    List<WechatUserStatistic> selectAll();

    /**
     * 获取指定天数的标签新增详情
     * @param recordTime
     * @return
     */
    List<WechatUserStatisticDetail> getDetailList(String recordTime);
}
