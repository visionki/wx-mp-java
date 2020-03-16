package com.visionki.wechat.controller;

import com.visionki.wechat.param.LoginParams;
import com.visionki.wechat.service.AdminService;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 14:46
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@RestController
@RequestMapping("/manage/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public R login(@RequestBody LoginParams loginParams){
        String token = adminService.login(loginParams.getAccount(),loginParams.getPassword());
        return RUtil.success(token);
    }
}
