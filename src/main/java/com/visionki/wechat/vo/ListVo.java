package com.visionki.wechat.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2019/7/25 12:08
 * @Version: 1.0
 * @Copyright: Copyright (c) 2019
 * @Description: 返回列表，包含页码，总页数，总条数，列表
 */
@Data
public class ListVo<T> {
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据列表
     */
    private List<T> list;
}
