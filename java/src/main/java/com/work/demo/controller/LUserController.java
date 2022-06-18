package com.work.demo.controller;


import com.work.demo.common.ApiResponse;
import com.work.demo.common.ErrorCodeEnum;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.request.ChangeInfoRequest;
import com.work.demo.entity.request.CommonIdRequest;
import com.work.demo.entity.request.EditQuestionRequest;
import com.work.demo.entity.vo.GameDetailVo;
import com.work.demo.entity.vo.InfoVo;
import com.work.demo.service.ILUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@CrossOrigin
@RestController
@RequestMapping("/demo/l-user")
public class LUserController {

    @Resource
    ILUserService ilUserService;

    @ApiOperation(value="查看某一个人信息")
    @PostMapping(value="/viewInfo")
    public ApiResponse<InfoVo> viewInfo(
            @RequestBody CommonIdRequest commonIdRequest
    )   {
        InfoVo infoVo=new InfoVo();
        try{
            infoVo=ilUserService.viewInfo(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(infoVo);
    }

    @ApiOperation(value="修改个人信息")
    @PostMapping(value="/changeInfo")
    public ApiResponse changeInfo(
            @RequestBody ChangeInfoRequest changeInfoRequest
            )  {
        try {
            ilUserService.changeInfo(changeInfoRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

}
