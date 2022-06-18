package com.work.demo.controller;


import com.work.demo.common.ApiResponse;
import com.work.demo.common.ErrorCodeEnum;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.request.CollectGameRequest;
import com.work.demo.entity.request.CommonIdRequest;
import com.work.demo.entity.request.ViewCollectRequest;
import com.work.demo.entity.vo.CollectListVo;
import com.work.demo.service.ILCollectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-05-20
 */
@RestController
@RequestMapping("/demo/l-collect")
@CrossOrigin
public class LCollectController {
    @Resource
    ILCollectService ilCollectService;

    /**
     * 收藏知识竞赛或知识
     * @param collectGameRequest
     * @return
     */
    @ApiOperation(value="收藏竞赛或知识")
    @PostMapping(value="/collect")
    public ApiResponse collectGame(
            @RequestBody CollectGameRequest collectGameRequest
            )   {
        try{
            ilCollectService.collectGame(collectGameRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

    @ApiOperation(value="查看收藏的竞赛或知识")
    @PostMapping(value="/viewCollect")
    public ApiResponse<List<CollectListVo>> viewCollectGame(
            @RequestBody ViewCollectRequest viewCollectRequest
            )   {
        List<CollectListVo> collectListVos=new ArrayList<>();
        try{
            collectListVos=ilCollectService.viewCollectGame(viewCollectRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(collectListVos);
    }




}
