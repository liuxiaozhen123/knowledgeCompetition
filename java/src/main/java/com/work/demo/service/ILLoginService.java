package com.work.demo.service;

import com.work.demo.entity.LLogin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.demo.entity.request.RegisterRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-03-22
 */
public interface ILLoginService extends IService<LLogin> {

    /**
     * 注册功能
     * @param registerRequest
     */
    void register(RegisterRequest registerRequest) throws Exception;
}
