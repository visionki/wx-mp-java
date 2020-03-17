package com.visionki.wechat.util;


import com.github.pagehelper.Page;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.vo.ListVo;
import com.visionki.wechat.vo.R;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 返回json数据的工具类
 * User: vision
 * Date: 2018-11-28
 */
public class RUtil {

    /**
     * 数据处理成功时的返回处理（无参数模式）
     * @return
     */
    public static R success() {
        return success(null);
    }

    /**
     * 数据处理成功时的返回处理
     * @param object 返回对象
     * @return
     */
    public static<T> R<T> success(T object) {
        R<T> r = new R<>();
        r.setData(object);
        //200表示成功
        r.setCode(200);
        r.setMsg("ok");
        return r;
    }

    /**
     * 返回列表包装类
     * @param page 包含页码，总页数，总条数，数据列表
     * @param list 数据列表
     * @return
     */
    public static<T> R<ListVo> success(Page page, List<T> list) {
        // 建立列表的包装类
        ListVo<T> listVo = new ListVo<>();
        listVo.setTotal(page.getTotal());
        listVo.setPageNum(page.getPageNum());
        listVo.setPages(page.getPages());
        listVo.setPageSize(page.getPageSize());
        listVo.setList(list);
        R<ListVo> r = new R<>();
        r.setData(listVo);
        //200表示成功
        r.setCode(200);
        r.setMsg("ok");
        return r;
    }

    /**
     * 数据处理异常时的返回处理，传入枚举值
     * @param rEnum 枚举值
     * @return
     */
    public static R error(REnum rEnum) {
        R r = new R();
        r.setCode(rEnum.getCode());
        r.setMsg(rEnum.getMessage());
        return r;
    }
    /**
     * 数据处理异常时的返回处理，传入字符串
     * @param errorMsg 字符串
     * @return
     */
    public static R error(String errorMsg) {
        R r = new R();
        r.setCode(500);
        r.setMsg(errorMsg);
        return r;
    }

    /**
     * 数据处理成功时的返回处理（失败的另外一个编码，加上传输数据）
     * @param rEnum
     * @param object
     * @return
     */
    public static<T> R<T> errorTransData(REnum rEnum, T object) {
        R<T> r = new R<>();
        r.setData(object);
        //0表示成功
        r.setCode(rEnum.getCode());
        r.setMsg(rEnum.getMessage());
        return r;
    }
}
