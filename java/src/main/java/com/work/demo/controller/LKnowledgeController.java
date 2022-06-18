package com.work.demo.controller;


import com.work.demo.common.ApiResponse;
import com.work.demo.common.ErrorCodeEnum;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.LCompetition;
import com.work.demo.entity.LKnowledge;
import com.work.demo.entity.request.*;
import com.work.demo.entity.vo.CommonIdVo;
import com.work.demo.entity.vo.KnowledgeDetailVo;
import com.work.demo.entity.vo.ViewGameVo;
import com.work.demo.entity.vo.ViewKnowledgeVo;
import com.work.demo.service.ILKnowledgeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 知识推荐表 前端控制器
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@RestController
@CrossOrigin
@RequestMapping("/demo/l-knowledge")
public class LKnowledgeController {

    @Resource
    ILKnowledgeService ilKnowledgeService;

    @ApiOperation(value="竞赛组织者查看所有知识推荐列表")
    @PostMapping(value="/viewKnowledgeList")
    public ApiResponse<List<ViewKnowledgeVo>> viewKnowledgeList(
            @RequestBody CommonIdRequest commonIdRequest
    ) {
        List<ViewKnowledgeVo> viewKnowledgeVos = new ArrayList<>();
        try{
            viewKnowledgeVos=ilKnowledgeService.viewKnowledgeList(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(viewKnowledgeVos);
    }


    @ApiOperation(value="竞赛组织者查看某一知识推荐详情")
    @PostMapping(value="/viewKnowledgeDetail")
    public ApiResponse<KnowledgeDetailVo> viewKnowledgeDetail(
            @RequestBody CommonIdRequest commonIdRequest
    ) {
        KnowledgeDetailVo viewKnowledgeVos = new KnowledgeDetailVo();
        try{
            viewKnowledgeVos=ilKnowledgeService.viewKnowledgeDetail(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(viewKnowledgeVos);
    }

    @ApiOperation(value="发布知识推荐")
    @PostMapping(value="/publishKnowledge")
    public ApiResponse publishKnowledge(
            @RequestBody CommonIdRequest commonIdRequest
            ){
     try{
        ilKnowledgeService.publishKnowledge(commonIdRequest);
    } catch (BizException e) {
        return ApiResponse.error(e.getErrMessage());
    } catch (Exception e) {
        return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
    }
        return ApiResponse.success();
    }

    @ApiOperation(value="添加知识推荐概述")
    @PostMapping(value="/addKnowledge")
    public ApiResponse<LKnowledge> setKnowledgeIntroduction(
            @RequestBody AddKnowledgeRequest addKnowledgeRequest
    ){
        LKnowledge lKnowledge=new LKnowledge();
        try{
            lKnowledge=ilKnowledgeService.addKnowledge(addKnowledgeRequest);
        } catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(lKnowledge);
    }

    @ApiOperation(value="设置知识推荐种类")
    @PostMapping(value="/setKnowledgeKind")
    public ApiResponse setKnowledgeKind(
            @RequestBody SetKnowledgeKindRequest setKnowledgeKindRequest
    )  {
        try {
            ilKnowledgeService.setKnowledgeKind(setKnowledgeKindRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

    @ApiOperation(value="编辑知识推荐详情")
    @PostMapping(value="/editKnowledge")
    public ApiResponse<LKnowledge> editKnowledge(
            @RequestBody CommonIdRequest commonIdRequest
    )  {
        LKnowledge lKnowledge=new LKnowledge();
        try {
            lKnowledge=ilKnowledgeService.editKnowledge(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(lKnowledge);
    }

    @ApiOperation(value="提交知识推荐详情")
    @PostMapping(value="/submitKnowledge")
    public ApiResponse<LKnowledge> submitKnowledge(
            @RequestBody KnowledgeDetailRequest knowledgeDetailRequest
    )  {
        LKnowledge lKnowledge=new LKnowledge();
        try {
            lKnowledge=ilKnowledgeService.submitKnowledge(knowledgeDetailRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(lKnowledge);
    }

    @ApiOperation(value="删除知识推荐")
    @PostMapping(value="/deleteKnowledge")
    public ApiResponse deleteKnowledge(
            @RequestBody CommonIdRequest commonIdRequest
    )   {
        try{
            ilKnowledgeService.deleteKnowledge(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

    @ApiOperation(value="知识竞赛名称搜集")
    @PostMapping(value="/selectCompetitionName")
    public ApiResponse<List<CommonIdVo>> selectCompetitionName()   {
        List<CommonIdVo> competitionNames=new ArrayList<>();
        try{
            competitionNames=ilKnowledgeService.selectCompetitionName();
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(competitionNames);
    }






}
