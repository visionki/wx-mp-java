package com.visionki.wechat.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.visionki.wechat.vo.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: vision
 * @CreateDate: 2020/3/14 16:19
 * @Version: 1.0
 * @Copyright: Copyright (c) 2020
 * @Description: 文件处理类
 */
@Component
public class FileUtil {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.path}")
    private String path;
    @Value("${aliyun.oss.bucketUrl}")
    private String bucketUrl;

    /**
     * 本地文件上传到阿里云oss
     * @param file
     * @return
     */
    public String upload(File file){
        // 文件后缀
        String fileExt = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        String fileName = UUIDUtils.uuid() + "." + fileExt;
        ossClient.putObject(bucketName, path + fileName, file);
        // 关闭OSSClient。
        ossClient.shutdown();
        return bucketUrl + path + fileName;
    }

    /**
     * 前端传来MultipartFile类上传到阿里云oss
     * @param file
     * @return
     */
    public String upload(MultipartFile file) {
        // 文件后缀
        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        String fileName = UUIDUtils.uuid() + "." + fileExt;
        try {
            ossClient.putObject(bucketName, path + fileName , file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return bucketUrl + path + fileName;
    }
}
