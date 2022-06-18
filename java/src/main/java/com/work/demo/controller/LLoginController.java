package com.work.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.work.demo.common.ApiResponse;
import com.work.demo.common.ErrorCodeEnum;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.LLogin;
import com.work.demo.entity.LUser;
import com.work.demo.entity.request.LoginRequest;
import com.work.demo.entity.request.RegisterRequest;
import com.work.demo.mapper.LLoginMapper;
import com.work.demo.mapper.LUserMapper;
import com.work.demo.service.ILLoginService;
import com.work.demo.service.ILUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-03-22
 */
@RestController
@RequestMapping("/demo/l-login")
@CrossOrigin
public class LLoginController {

    @Resource
    LLoginMapper loginMapper;

    @Resource
    ILLoginService loginService;

    @Resource
    LUserMapper lUserMapper;

    /**
     * 登录功能
     * @param loginRequest
     * @return
     */
    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public ApiResponse login(
            @RequestBody LoginRequest loginRequest
    ) {
        LLogin lLogin;
        try{
            lLogin = loginMapper.selectOne(
                    Wrappers.lambdaQuery(LLogin.class)
                            .eq(LLogin::getLoginId, loginRequest.getUserName())
                            .eq(LLogin::getPassword, loginRequest.getPassword())
                            .eq(LLogin::getType,loginRequest.getType())
            );
            if(lLogin==null){
                throw new BizException("用户密码错误，或者用户类型选则错误！");
            }
            LUser lUser1=lUserMapper.selectOne(
                    Wrappers.lambdaQuery(LUser.class)
                    .eq(LUser::getLoginId,loginRequest.getUserName())
            );
            if(lUser1==null){
                LUser lUser=new LUser();
                lUser.setLoginId(loginRequest.getUserName());
                //生日默认当前时间
                lUser.setBirthday(new Date(System.currentTimeMillis()));
                lUser.setPhone("请输入您的手机号码！");
                lUser.setUserId(UUID.randomUUID().toString());
                lUser.setAddress("请输入您的个人住址！");
                lUser.setGender("0");
                lUser.setOccupation("请输入您的职业！");
                lUser.setIsDel("0");
                lUser.setEmail("请输入您的邮箱！");
                lUser.setUserName("请输入您的昵称！");
                lUserMapper.insert(lUser);
            }

        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(123);
    }
    /**
     * 注册
     * @param registerRequest
     * @return
     */
    @ApiOperation(value="注册")
    @PostMapping(value="/register")
    public ApiResponse register(
            @RequestBody RegisterRequest registerRequest
            ) {
        try{
            loginService.register(registerRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success("注册成功");
    }
}
