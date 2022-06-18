package com.work.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.LCompetition;
import com.work.demo.entity.LQuestion;
import com.work.demo.entity.LScore;
import com.work.demo.entity.LUserAnswer;
import com.work.demo.entity.request.CommonIdRequest;
import com.work.demo.entity.request.SubmitGameRequest;
import com.work.demo.entity.vo.CommonDicVo;
import com.work.demo.entity.vo.CommonDouDicVo;
import com.work.demo.entity.vo.ScoreDetailVo;
import com.work.demo.mapper.LCompetitionMapper;
import com.work.demo.mapper.LQuestionMapper;
import com.work.demo.mapper.LScoreMapper;
import com.work.demo.mapper.LUserAnswerMapper;
import com.work.demo.service.ILScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户得分表 服务实现类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Service
public class LScoreServiceImpl extends ServiceImpl<LScoreMapper, LScore> implements ILScoreService {

    @Resource
    LUserAnswerMapper userAnswerMapper;

    @Resource
    LScoreMapper lScoreMapper;

    @Resource
    LQuestionMapper lQuestionMapper;

    @Resource
    LCompetitionMapper lCompetitionMapper;

    @Override
    public CommonDouDicVo submitGame(SubmitGameRequest submitGameRequest) throws Exception {

        /**
         * 1.某人答某赛题得分以及赛题总分情况
         * 2.通过竞赛id找到所有问题id
         * 3.循环问题列表，主要查看每个问题的答案，根据答案计算总得分，记录在分数表中
         */
        List<LQuestion> lQuestions=lQuestionMapper.selectList(
                Wrappers.lambdaQuery(LQuestion.class)
                .eq(LQuestion::getCompetitionId,submitGameRequest.getCompetitionId())
                .eq(LQuestion::getIsDel,"0")
        );

        if(lQuestions==null){
            throw new BizException("该试题不包含任何问题！");
        }
        int number=lQuestions.size();
        int totalScore=0;
        //整套试题的分值
        int fullScore=5*number;
        int i=0;
        for(LQuestion question:lQuestions){
            if(question.getNote().equals(submitGameRequest.getAnswerRequestList().get(i))){
                System.out.println(submitGameRequest.getAnswerRequestList().get(i));
                totalScore+=5;
            }
            i++;
        }
        LScore lScore1=lScoreMapper.selectOne(
                Wrappers.lambdaQuery(LScore.class)
                .eq(LScore::getCompetitionId,submitGameRequest.getCompetitionId())
                .eq(LScore::getLoginId,submitGameRequest.getLoginId())
        );
        if(lScore1!=null){
            if(lScore1.getScore()<totalScore){
                lScore1.setScore(totalScore);
                lScoreMapper.updateById(lScore1);
            }
        }else{
            LScore lScore=new LScore();
            lScore.setScore(totalScore);
            lScore.setLoginId(submitGameRequest.getLoginId());
            lScore.setCompetitionId(submitGameRequest.getCompetitionId());
            String score=String.valueOf(fullScore);
            lScore.setNote(score);
            lScore.setIsDel("0");
            lScore.setCreateTime(new Date(System.currentTimeMillis()));
            lScore.setUpdateTime(new Date(System.currentTimeMillis()));
            lScoreMapper.insert(lScore);
        }
        CommonDouDicVo commonDouDicVo=new CommonDouDicVo();
        commonDouDicVo.setKey1("你的得分:");
        commonDouDicVo.setValue1(""+totalScore);
        commonDouDicVo.setKey2("整套试题分数:");
        commonDouDicVo.setValue2(""+fullScore);
        return commonDouDicVo;
    }

    /**
     * 参赛者查看某一竞赛成绩详情实现
     * @param commonIdRequest
     * @return
     */
    @Override
    public List<ScoreDetailVo> viewScoreDetail(CommonIdRequest commonIdRequest) throws Exception {
                LCompetition lCompetition=lCompetitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionName,commonIdRequest.getId())
                .eq(LCompetition::getIsDel,"0")
        );
        if(lCompetition==null){
            throw new BizException("未找到该竞赛！");
        }
        String competitionId=lCompetition.getCompetitionId();
        List<LScore> lScores=lScoreMapper.selectList(
                Wrappers.lambdaQuery(LScore.class)
                .eq(LScore::getCompetitionId,competitionId)
                .eq(LScore::getIsDel,"0")
        );
        if(lScores==null){
            throw new BizException("没有找到参加该竞赛的用户");
        }
        List<ScoreDetailVo> scoreDetailVos=new ArrayList<>();
        for(LScore lScore : lScores){
            ScoreDetailVo scoreDetailVo=new ScoreDetailVo();
            scoreDetailVo.setTotalScore(String.valueOf(lScore.getScore()));
            //答题时间
            String time;
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
            time = dateformat.format(lScore.getCreateTime());
            scoreDetailVo.setCreateTime(time);
            scoreDetailVo.setLoginId(lScore.getLoginId());
            //排名rank
            int rank=1;
            for(LScore lScore1:lScores){
                if(lScore1.getScore()>lScore.getScore()){
                    rank+=1;
                }
            }
            scoreDetailVo.setRank(rank);
            scoreDetailVos.add(scoreDetailVo);
        }
        return scoreDetailVos;
    }
}
