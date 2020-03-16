package com.visionki.wechat.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.model.WechatSendRecord;
import com.visionki.wechat.model.WechatUser;
import com.visionki.wechat.param.UpdateUserInfoParam;
import com.visionki.wechat.param.UserSelectParam;
import com.visionki.wechat.service.UserService;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: vision
 * @CreateDate: 2020/3/16 11:46
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 公众号用户管理
 */
@RestController
@RequestMapping("/manage/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public R userList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "15") Integer pageSize, UserSelectParam userSelectParam){
        Page<WechatUser> page = PageHelper.startPage(pageNo, pageSize, "subscribe_time desc");
        List<WechatUser> wechatUserList = userService.selectByAttr(userSelectParam);
        return RUtil.success(page, wechatUserList);
    }

    @PutMapping
    public R updateUserInfo(@RequestBody UpdateUserInfoParam updateUserInfoParam){
        int count = userService.updateUserInfo(updateUserInfoParam);
        if (count == 1){
            return RUtil.success();
        }else {
            return RUtil.error(REnum.ERROR);
        }
    }
}
