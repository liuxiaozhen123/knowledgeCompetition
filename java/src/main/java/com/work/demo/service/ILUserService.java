package com.work.demo.service;

import com.work.demo.entity.LUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.demo.entity.request.ChangeInfoRequest;
import com.work.demo.entity.request.CommonIdRequest;
import com.work.demo.entity.vo.InfoVo;

import java.text.ParseException;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
public interface ILUserService extends IService<LUser> {

    InfoVo viewInfo(CommonIdRequest commonIdRequest);

    void changeInfo(ChangeInfoRequest changeInfoRequest) throws ParseException;
}
