package com.visionki.wechat.constant;

/**
 * @author vision
 * @date 2019-07-25
 * @describe 返回前端错误信息枚举类
 */
public enum REnum {
    /* 1~10000 系统级别错误 */
    ERROR(500,"出现错误，请联系管理员"),
    TOKEN_VALIDATION_FAILED(403, "验证失败"),

    /* 10000~20000段，数据相关错误码 */
    NOT_DATA(10001,"素材ID有误,请检查后重新输入"),
    TOO_MUCH_MATERIAL(10002,"图文消息条数过多,请检查后重试"),
    MEDIA_NOT_CONTENT(10003,"该图文消息(外链型)没有内容,请添加内容后在发送"),
    MEDIA_NOT_EXISTS(10004,"该图文消息(官方文章)不存在,请前往图文消息(官方文章)管理同步后再尝试"),
    TAG_NOT_EXISTS(10005,"该标签不存在,请刷新页面后重试"),
    TOO_MUCH_PARENT_MENU(10006,"一级菜单最多存在三个，已达上限"),
    PARENT_MENU_NOT_EXISTS(10007,"父级菜单不存在，请刷新后再试"),
    TOO_MUCH_CHILDREN_MENU(10006,"子菜单最多存在五个，已达上限"),
    CHILDREN_MENU_IS_EXISTS(10007,"存在子菜单，请删除后再改变类型"),
    MENU_CREATE_ERROR_NAME_TOO_LONG(10008,"父级菜单名字太长，最多支持5个字"),
    MENU_CREATE_ERROR_COUNT(10008,"子菜单数量异常，请检查后再试"),


    /* 20000~30000段，用户相关错误码 */
    LOGIN_FAILURE(20001,"账号或密码不正确"),



    /**
     * 数据不存在
     */
    DATA_NOT_EXISTS(301,"数据不存在"),


    ;

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    REnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
