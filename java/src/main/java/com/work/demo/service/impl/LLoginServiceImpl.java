package com.work.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.work.demo.common.exception.BizException;
import com.work.demo.common.exception.CustomException;
import com.work.demo.entity.LLogin;
import com.work.demo.entity.request.RegisterRequest;
import com.work.demo.mapper.LLoginMapper;
import com.work.demo.service.ILLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-03-22
 */
@Service
public class LLoginServiceImpl extends ServiceImpl<LLoginMapper, LLogin> implements ILLoginService {

    @Resource
    LLoginMapper loginMapper;

    /**
     * 注册功能实现
     * @param registerRequest
     */
    @Override
    public void register(RegisterRequest registerRequest) throws Exception {
        if(!(registerRequest.getPassword1().equals(registerRequest.getPassword2()))){
            throw new BizException("两次输入的密码不相等,请重新输入！");
        }
        LLogin lLogin = new LLogin();
        lLogin = loginMapper.selectOne(
                Wrappers.lambdaQuery(LLogin.class)
                .eq(LLogin::getLoginId,registerRequest.getUserName())
                .eq(LLogin::getIsDel,"0")
        );
        if(lLogin != null){
            throw new BizException("该用户名已经被注册过，请重新输入！");
        }
        LLogin lLogin1 = new LLogin();
        lLogin1.setLoginId(registerRequest.getUserName());
        lLogin1.setPassword(registerRequest.getPassword1());
        lLogin1.setIsDel("0");
//        if("竞赛组织者".equals(registerRequest.getType())){
//            lLogin1.setType("0");
//        }else if("参赛者".equals(registerRequest.getType())){
//            lLogin1.setType("1");
//        } else if("系统管理员".equals(registerRequest.getType())){
//            lLogin1.setType("2");
//        }

        lLogin1.setType(registerRequest.getType());
        lLogin1.setType(registerRequest.getType());
        loginMapper.insert(lLogin1);
    }
}
