package com.visionki.wechat.config;

import com.visionki.wechat.interceptor.ManageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: vision
 * @CreateDate: 2019/6/20 11:43
 * @Version: 1.0
 * @Description: 项目配置类
 * @Copyright: Copyright (c) 2019
 */
@Configuration
public class ManageConfig implements WebMvcConfigurer {
    @Autowired
    private ManageInterceptor manageInterceptor;

    /**
     * 配置CORS跨域规则，允许全部跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                .maxAge(3600);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加用户拦截器
        registry.addInterceptor(manageInterceptor)
                .addPathPatterns("/manage/**")
                .excludePathPatterns("/manage/admin/login");
        System.out.println("加入拦截器");
    }
}
