package com.work.demo.controller;


import com.work.demo.common.ApiResponse;
import com.work.demo.common.ErrorCodeEnum;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.LCompetition;
import com.work.demo.entity.LKnowledge;
import com.work.demo.entity.request.*;
import com.work.demo.entity.vo.*;
import com.work.demo.service.ILCompetitionService;
import com.work.demo.service.ILScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 赛题表 前端控制器
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/demo/l-competition")
@CrossOrigin
public class LCompetitionController {

    @Resource
    ILCompetitionService competitionService;

    /**
     * 竞赛组织者查看竞赛列表
     * @param commonIdRequest
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查看竞赛列表")
    @PostMapping(value="/viewGameList")
    public ApiResponse<List<ViewGameVo>> viewGameList(
            @RequestBody CommonIdRequest commonIdRequest
            ) {
        List<ViewGameVo> viewGameVos = new ArrayList<>();
        try{
            viewGameVos=competitionService.viewGameList(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(viewGameVos);
    }

    @ApiOperation(value="参赛者查看竞赛已参加列表")
    @PostMapping(value="/viewGameListOfCompetitor")
    public ApiResponse<List<ViewGameListOfCompetitorVo>> viewGameListOfCompetitor(
            @RequestBody CommonIdRequest commonIdRequest
    ) {
        List<ViewGameListOfCompetitorVo> viewGameListOfCompetitorVos = new ArrayList<>();
        try{
            viewGameListOfCompetitorVos=competitionService.viewGameListOfCompetitor(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(viewGameListOfCompetitorVos);
    }

    /**
     * 竞赛组织者查看主界面竞赛概述列表
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查看竞赛概述列表(主界面)")
    @PostMapping(value="/viewGameIntroduceList")
    public ApiResponse<List<ViewGameIntroduceListVo>> viewGameIntroduceList()
    {
        List<ViewGameIntroduceListVo> viewGameVos = new ArrayList<>();
        try{
            viewGameVos=competitionService.viewGameIntroduceList();
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(viewGameVos);
    }

    /**
     * 设置竞赛种类
     * @param setGameKindRequest
     * @return
     * @throws Exception
     */
    @ApiOperation(value="设置竞赛种类")
    @PostMapping(value="/setKind")
    public ApiResponse setGameKind(
            @RequestBody SetGameKindRequest setGameKindRequest
    )  {
        try {
            competitionService.setGameKind(setGameKindRequest);
        } catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

    /**
     * 添加竞赛概述
     * @param addGameRequest
     * @return
     */
    @ApiOperation(value="添加竞赛概述")
    @PostMapping(value="/addGame")
    public ApiResponse<LCompetition> setGameIntroduction(
            @RequestBody AddGameRequest addGameRequest
            ){
        try{
            competitionService.addGame(addGameRequest);
        } catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success("添加成功！");
    }

    /**
     * 添加问题和选项
     * @param addGameRequest
     * @return
     * @throws Exception
     */
    @ApiOperation(value="添加问题选项")
    @PostMapping(value="/addQuestion")
    public ApiResponse<LCompetition> setQuestion(
            @RequestBody AddQuestionRequest addGameRequest
    ) {
        try {
            competitionService.addQuestion(addGameRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }


    /**
     * 发布竞赛（修改竞赛状态）
     * @param commonIdRequest
     * @return
     * @throws Exception
     */
    @ApiOperation(value="发布竞赛")
    @PostMapping(value="/publishGame")
    public ApiResponse publishGame(
            @RequestBody CommonIdRequest commonIdRequest
    ) {
        try{
            competitionService.publishGame(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

    @ApiOperation(value="参赛者查看所有已发布知识竞赛")
    @PostMapping(value="/viewPublishedGame")
    public ApiResponse<List<ViewGameIntroduceListVo>> viewPublishedGame()
    {
        List<ViewGameIntroduceListVo> viewGameVos = new ArrayList<>();
        try{
            viewGameVos=competitionService.viewPublishedGame();
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(viewGameVos);
    }

    @ApiOperation(value="参赛者查看某一竞赛详情")
    @PostMapping(value="/viewGameDetail")
    //id改成竞赛名称
    public ApiResponse<List<GameDetailVo>> viewGameDetail(
            @RequestBody CommonIdRequest commonIdRequest
    )   {
        List<GameDetailVo> gameDetailVos = new ArrayList<>();
        try{
            gameDetailVos=competitionService.viewGameDetail(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(gameDetailVos);
    }

    /**
     * 删除竞赛
     * @param commonIdRequest
     * @return
     */
    @ApiOperation(value="删除竞赛")
    @PostMapping(value="/deleteGame")
    public ApiResponse deleteGame(
            @RequestBody CommonIdRequest commonIdRequest
    )   {
        try{
            competitionService.deleteGame(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

    @ApiOperation(value="编辑竞赛详情")
    @PostMapping(value="/editGame")
    public ApiResponse<GameDetailVo> editGame(
            @RequestBody EditQuestionRequest editQuestionRequest
    )  {
        GameDetailVo gameDetailVo = new GameDetailVo();
        try {
            gameDetailVo=competitionService.editGame(editQuestionRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success(gameDetailVo);
    }

    @ApiOperation(value="提交问题")
    @PostMapping(value="/submitQuestion")
    public ApiResponse submitQuestion(
            @RequestBody SubmitQuestionRequest submitQuestionRequest
    )  {
        try {
            competitionService.submitQuestion(submitQuestionRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

    @ApiOperation(value="查看竞赛状态")
    @PostMapping(value="/viewStatus")
    public ApiResponse viewStatus(
            @RequestBody CommonIdRequest commonIdRequest
    )  {
        try {
            competitionService.viewStatus(commonIdRequest);
        }catch (BizException e) {
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        return ApiResponse.success();
    }

}
