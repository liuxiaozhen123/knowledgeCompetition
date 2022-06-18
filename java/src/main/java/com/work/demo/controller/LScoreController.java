package com.work.demo.controller;


import com.work.demo.common.ApiResponse;
import com.work.demo.common.ErrorCodeEnum;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.request.CommonIdRequest;
import com.work.demo.entity.request.SubmitGameRequest;
import com.work.demo.entity.vo.CommonDicVo;
import com.work.demo.entity.vo.CommonDouDicVo;
import com.work.demo.entity.vo.ScoreDetailVo;
import com.work.demo.service.ILScoreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户得分表 前端控制器
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@CrossOrigin
@RestController
@RequestMapping("/demo/l-score")
public class LScoreController {

    @Resource
    ILScoreService scoreService;

    @ApiOperation(value="参赛者提交竞赛")
    @PostMapping(value="/submitGame")
    public ApiResponse<CommonDouDicVo> submitGame(
            @RequestBody SubmitGameRequest submitGameRequest
    )  {
        CommonDouDicVo commonDouDicVo=new CommonDouDicVo();
        try{
             commonDouDicVo=scoreService.submitGame(submitGameRequest);

        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(commonDouDicVo);
    }

    @ApiOperation(value="参赛者查看某一竞赛成绩详情")
    @PostMapping(value="/viewScoreDetail")
    public ApiResponse<List<ScoreDetailVo>> viewScoreDetail(
            @RequestBody CommonIdRequest commonIdRequest
    )  {
        List<ScoreDetailVo> scoreDetailVos=new ArrayList<>();
        try{
            scoreDetailVos= scoreService.viewScoreDetail(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(scoreDetailVos);
    }
}
