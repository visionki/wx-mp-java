package com.visionki.wechat.controller;

import com.visionki.wechat.util.FileUtil;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: vision
 * @CreateDate: 2020/3/5 22:23
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description:
 */
@RestController
@RequestMapping("/manage/file")
public class FileController {

    @Autowired
    private FileUtil fileUtil;

    @PostMapping("/upload")
    public R upload(MultipartFile file){
        return RUtil.success(fileUtil.upload(file));
    }
}
