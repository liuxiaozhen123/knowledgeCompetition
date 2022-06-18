package com.work.demo.service;

import com.work.demo.entity.LCompetition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.demo.entity.request.*;
import com.work.demo.entity.vo.GameDetailVo;
import com.work.demo.entity.vo.ViewGameIntroduceListVo;
import com.work.demo.entity.vo.ViewGameListOfCompetitorVo;
import com.work.demo.entity.vo.ViewGameVo;

import java.util.List;

/**
 * <p>
 * 赛题表 服务类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-19
 */
public interface ILCompetitionService extends IService<LCompetition> {

    List<ViewGameVo> viewGameList(CommonIdRequest commonIdRequest) throws Exception;

    List<ViewGameIntroduceListVo> viewGameIntroduceList() throws Exception;

    void setGameKind(SetGameKindRequest setGameKindRequest) throws Exception;


    LCompetition addGame(AddGameRequest addGameRequest) throws Exception;

    void addQuestion(AddQuestionRequest addGameRequest) throws Exception;

    void publishGame(CommonIdRequest commonIdRequest) throws Exception;

    List<ViewGameIntroduceListVo> viewPublishedGame() throws Exception;

    List<GameDetailVo> viewGameDetail(CommonIdRequest commonIdRequest) throws Exception;

    List<ViewGameListOfCompetitorVo> viewGameListOfCompetitor(CommonIdRequest commonIdRequest) throws Exception;

    void deleteGame(CommonIdRequest commonIdRequest);

    GameDetailVo editGame(EditQuestionRequest editQuestionRequest);

    void submitQuestion(SubmitQuestionRequest submitQuestionRequest);

    void viewStatus(CommonIdRequest commonIdRequest);
}
