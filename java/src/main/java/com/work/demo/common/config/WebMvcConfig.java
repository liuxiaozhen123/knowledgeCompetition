package com.work.demo.common.config;

import com.work.demo.common.aop.AccessTokenInterceptor;
import com.work.demo.common.aop.PerformanceInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * @author zuozhiwei
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.midPath}")
    String midPath;

    @Value("${file.uploadPath}")
    String uploadPath;

    /**
     * @return 登录验证拦截器
     * 自定义登录验证拦截器
     */
    @Bean
    public AccessTokenInterceptor needLoginInterceptor() {
        return new AccessTokenInterceptor();
    }

    /**
     * @param registry 配置静态资源放行
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler(midPath).addResourceLocations("file:"+uploadPath);
    }

    /**
     * @param registry 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加登录处理拦截器，拦截所有请求，登录请求除外
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(needLoginInterceptor());
        //排除配置
        interceptorRegistration.excludePathPatterns("/file/**");
        //配置拦截策略
        interceptorRegistration.addPathPatterns("/**");

        registry.addInterceptor(new PerformanceInterceptor());

    }
}