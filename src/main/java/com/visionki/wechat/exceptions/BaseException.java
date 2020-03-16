package com.visionki.wechat.exceptions;


import com.visionki.wechat.constant.REnum;

/**
 * @author liuboyi
 * @Description: 自定义异常类
 * @create 2019-04-11 18:06
 */
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private REnum rEnum;

    public BaseException(REnum rEnum) {
        this.rEnum = rEnum;
    }

    public BaseException(REnum rEnum, Throwable cause) {
        super(rEnum.getMessage(), cause);
    }

    public REnum getREnum() {
        return rEnum;
    }
}