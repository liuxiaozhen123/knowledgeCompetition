package com.work.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.*;
import com.work.demo.entity.request.*;
import com.work.demo.entity.vo.GameDetailVo;
import com.work.demo.entity.vo.ViewGameIntroduceListVo;
import com.work.demo.entity.vo.ViewGameListOfCompetitorVo;
import com.work.demo.entity.vo.ViewGameVo;
import com.work.demo.mapper.*;
import com.work.demo.service.ILCompetitionService;
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
 * 赛题表 服务实现类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-19
 */
@Service
public class LCompetitionServiceImpl extends ServiceImpl<LCompetitionMapper, LCompetition> implements ILCompetitionService {

    @Resource
    LCompetitionMapper competitionMapper;

    @Resource
    LQuestionMapper lQuestionMapper;

    @Resource
    LSelectAnswerMapper lSelectAnswerMapper;

    @Resource
    LScoreMapper lScoreMapper;

    @Resource
    LCollectMapper lCollectMapper;

    @Override
    public List<ViewGameVo> viewGameList(CommonIdRequest commonIdRequest) throws Exception {
        List<ViewGameVo> viewGameVos=new ArrayList<>();
        List<LCompetition> competitions=new ArrayList<>();
        competitions=competitionMapper.selectList(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getNote ,commonIdRequest.getId())
                .eq(LCompetition::getStatus,"1")
        );
        if(competitions.isEmpty()){
            throw new BizException("未找到该竞赛组织者发布的竞赛信息！");
        }
        //通过循环将需要的数据放在List<ViewGameVo>
        for(LCompetition competition:competitions){
            ViewGameVo viewGameVo=new ViewGameVo();
            viewGameVo.setGameId(competition.getCompetitionId());
            viewGameVo.setGameName(competition.getCompetitionName());
            int kind=competition.getKind();
            if(kind==0){
                viewGameVo.setKind("未知种类");
            }else if(kind==1){
                viewGameVo.setKind("动物");
            }else if(kind==2){
                viewGameVo.setKind("植物");
            }else if(kind==3){
                viewGameVo.setKind("古代名人");
            }else if(kind==4){
                viewGameVo.setKind("建筑");
            }
            String time;
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
            time = dateformat.format(competition.getCreateTime());
            viewGameVo.setStartTime(time);
            viewGameVos.add(viewGameVo);
        }
        return viewGameVos;
    }

    @Override
    public List<ViewGameIntroduceListVo> viewGameIntroduceList() throws Exception {
        List<ViewGameIntroduceListVo> viewGameIntroduceListVos=new ArrayList<>();
        List<LCompetition> competitions=competitionMapper.selectList(
                Wrappers.lambdaQuery(LCompetition.class)
                .isNotNull(LCompetition::getId)
                .notLike(LCompetition::getStatus,"2")
                .eq(LCompetition::getIsDel,"0")
        );
        if(competitions==null){
            throw new BizException("没有任何竞赛信息！");
        }
        for(LCompetition competition:competitions){
            ViewGameIntroduceListVo viewGameIntroduceListVo=new ViewGameIntroduceListVo();
            viewGameIntroduceListVo.setGameId(competition.getCompetitionId());
            viewGameIntroduceListVo.setIntroduction(competition.getIntroduction());
            viewGameIntroduceListVo.setGameName(competition.getCompetitionName());
            int kind=competition.getKind();
            if(kind==0){
                viewGameIntroduceListVo.setKind("未知种类");
            }else if(kind==1){
                viewGameIntroduceListVo.setKind("动物");
            }else if(kind==2){
                viewGameIntroduceListVo.setKind("植物");
            }else if(kind==3){
                viewGameIntroduceListVo.setKind("古代名人");
            }else if(kind==4){
                viewGameIntroduceListVo.setKind("建筑");
            }
            String status=competition.getStatus();
            if("0".equals(status)){
                viewGameIntroduceListVo.setStatus("未发布");
            }else if("1".equals(status)){
                viewGameIntroduceListVo.setStatus("已发布");
            }else if("2".equals(status)){
                viewGameIntroduceListVo.setStatus("已删除");
            }
            String time;
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
            time = dateformat.format(competition.getCreateTime());
            viewGameIntroduceListVo.setChangeTime(time);
            viewGameIntroduceListVos.add(viewGameIntroduceListVo);
        }
        return viewGameIntroduceListVos;
    }

    @Override
    public void setGameKind(SetGameKindRequest setGameKindRequest) throws Exception {
        LCompetition competition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionId,setGameKindRequest.getCompetitionId())
        );
        if(competition==null){
            throw new BizException("该竞赛不存在！");
        }
        String kind=setGameKindRequest.getKind();
        if("动物".equals(kind)){
            competition.setKind(1);
            competitionMapper.updateById(competition);
        }else if("植物".equals(kind)){
            competition.setKind(2);
            competitionMapper.updateById(competition);
        }else if("古代名人".equals(kind)){
            competition.setKind(3);
            competitionMapper.updateById(competition);
        }else if("建筑".equals(kind)){
            competition.setKind(4);
            competitionMapper.updateById(competition);
        }
    }

    /**
     * 添加竞赛
     * @param addGameRequest
     * @return
     */
    @Override
    public LCompetition addGame(AddGameRequest addGameRequest) throws Exception {
        ViewGameIntroduceListVo viewGameIntroduceListVo=new ViewGameIntroduceListVo();
        LCompetition competition1=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                        .eq(LCompetition::getCompetitionName,addGameRequest.getCompetitionName())
                .notLike(LCompetition::getStatus,"2")
        );
        if(competition1!=null){
            throw new BizException("该竞赛题目与其他题目重复，请重新添加！");
        }
        LCompetition competition=new LCompetition();
        competition.setCompetitionId(UUID.randomUUID().toString());
        competition.setKind(0);
        competition.setIntroduction(addGameRequest.getIntroduction());
        competition.setCompetitionName(addGameRequest.getCompetitionName());
        competition.setStatus("0");
        competition.setCreateTime(new Date(System.currentTimeMillis()));
        competition.setUpdateTime(new Date(System.currentTimeMillis()));
        competitionMapper.insert(competition);
        return competition;
    }

    /**
     * 添加问题和选项
     * @param addGameRequest
     * @throws Exception
     */
    @Override
    public void addQuestion(AddQuestionRequest addGameRequest) throws Exception {
        if(addGameRequest.getQuestionName()==null||addGameRequest.getSelect1()==null||addGameRequest.getSelect2()==null
        ||addGameRequest.getSelect3()==null||addGameRequest.getSelect4()==null|| addGameRequest.getSelect5()==null){
            throw new BizException("提交的问题、选项、或答案有空的情况，请检查后重新提交！");
        }
        String s1=addGameRequest.getSelect1();
        String s2=addGameRequest.getSelect2();
        String s3=addGameRequest.getSelect3();
        String s4=addGameRequest.getSelect4();
        String s5=addGameRequest.getSelect5();
        if(s1.equals(s2)||s1.equals(s3)||s1.equals(s4)||s2.equals(s3)||s2.equals(s4)||s3.equals(s4)){
            throw new BizException("四个选项中有内容相同的选项，请检查后重新提交！");
        }
        if(!("A".equals(s5)||"B".equals(s5)||"C".equals(s5)||"D".equals(s5))){
            throw new BizException("答案中含有除A,B,C,D外的其他字符，请检查后重新提交");
        }
        LCompetition lCompetition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getStatus,"0")
                .eq(LCompetition::getCompetitionId,addGameRequest.getCompetitionId())
                .eq(LCompetition::getIsDel,"0")
        );
        if(lCompetition==null){
            throw new BizException("该竞赛已经发布或已删除，不能继续添加赛题！");
        }
        LQuestion lQuestion1=lQuestionMapper.selectOne(
                Wrappers.lambdaQuery(LQuestion.class)
                        .eq(LQuestion::getIsDel,"0")
                        .eq(LQuestion::getQuestionName,addGameRequest.getQuestionName())
                        .eq(LQuestion::getCompetitionId,addGameRequest.getCompetitionId())
        );
        if(lQuestion1!=null){
            throw new BizException("插入了重复的问题，请重新输入！");
        }
        //插入问题
        LQuestion question=new LQuestion();
        question.setQuestionId(UUID.randomUUID().toString());
        question.setCompetitionId(addGameRequest.getCompetitionId());
        question.setQuestionName(addGameRequest.getQuestionName());
        question.setNote(addGameRequest.getSelect5());
        question.setCreateTime(new Date(System.currentTimeMillis()));
        question.setUpdateTime(new Date(System.currentTimeMillis()));
        lQuestionMapper.insert(question);
        //选项
        LQuestion lQuestion=lQuestionMapper.selectOne(
                Wrappers.lambdaQuery(LQuestion.class)
                .eq(LQuestion::getQuestionName,addGameRequest.getQuestionName())
                .eq(LQuestion::getCompetitionId,addGameRequest.getCompetitionId())
                .eq(LQuestion::getIsDel,"0")
        );
        if(lQuestion==null){
            throw new BizException("问题没有插入成功！");
        }
        LSelectAnswer selectAnswer1 = new LSelectAnswer();
        selectAnswer1.setAnswerId(UUID.randomUUID().toString());
        selectAnswer1.setQuestionId(lQuestion.getQuestionId());
        selectAnswer1.setAnswerName(addGameRequest.getSelect1());
        selectAnswer1.setIsDel("0");
        selectAnswer1.setCreateTime(new Date(System.currentTimeMillis())) ;
        selectAnswer1.setUpdateTime(new Date(System.currentTimeMillis()));
        lSelectAnswerMapper.insert(selectAnswer1);
        LSelectAnswer selectAnswer2 = new LSelectAnswer();
        selectAnswer2.setAnswerId(UUID.randomUUID().toString());
        selectAnswer2.setQuestionId(lQuestion.getQuestionId());
        selectAnswer2.setAnswerName(addGameRequest.getSelect2());
        selectAnswer2.setIsDel("0");
        selectAnswer2.setCreateTime(new Date(System.currentTimeMillis())) ;
        selectAnswer2.setUpdateTime(new Date(System.currentTimeMillis()));
        lSelectAnswerMapper.insert(selectAnswer2);
        LSelectAnswer selectAnswer3 = new LSelectAnswer();
        selectAnswer3.setAnswerId(UUID.randomUUID().toString());
        selectAnswer3.setQuestionId(lQuestion.getQuestionId());
        selectAnswer3.setAnswerName(addGameRequest.getSelect3());
        selectAnswer3.setIsDel("0");
        selectAnswer3.setCreateTime(new Date(System.currentTimeMillis())) ;
        selectAnswer3.setUpdateTime(new Date(System.currentTimeMillis()));
        lSelectAnswerMapper.insert(selectAnswer3);
        LSelectAnswer selectAnswer4 = new LSelectAnswer();
        selectAnswer4.setAnswerId(UUID.randomUUID().toString());
        selectAnswer4.setQuestionId(lQuestion.getQuestionId());
        selectAnswer4.setAnswerName(addGameRequest.getSelect4());
        selectAnswer4.setIsDel("0");
        selectAnswer4.setCreateTime(new Date(System.currentTimeMillis())) ;
        selectAnswer4.setUpdateTime(new Date(System.currentTimeMillis()));
        lSelectAnswerMapper.insert(selectAnswer4);
    }

    @Override
    public void publishGame(CommonIdRequest commonIdRequest) throws Exception {
        LCompetition lCompetition=new LCompetition();
        lCompetition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionId,commonIdRequest.getId())
        );
        if(lCompetition==null){
            throw new BizException("竞赛未找到！请检查该竞赛是否存在！");
        }
        List<LQuestion> lQuestions=lQuestionMapper.selectList(
                Wrappers.lambdaQuery(LQuestion.class)
                .eq(LQuestion::getCompetitionId,commonIdRequest.getId())
                .eq(LQuestion::getIsDel,0)
        );
        if(lQuestions.size()==0){
            throw new BizException("该竞赛还未添加任何题目，请先添加题目后再发布");
        }
        lCompetition.setStatus("1");
        competitionMapper.updateById(lCompetition);
    }

    @Override
    public List<ViewGameIntroduceListVo> viewPublishedGame() throws Exception {
        List<ViewGameIntroduceListVo> viewGameIntroduceListVos=new ArrayList<>();
        List<LCompetition> competitions=competitionMapper.selectList(
                Wrappers.lambdaQuery(LCompetition.class)
                        .isNotNull(LCompetition::getId)
                        .eq(LCompetition::getStatus,'1')
        );
        if(competitions==null){
            throw new BizException("没有任何竞赛信息！");
        }
        for(LCompetition competition:competitions){
            ViewGameIntroduceListVo viewGameIntroduceListVo=new ViewGameIntroduceListVo();
            viewGameIntroduceListVo.setGameId(competition.getCompetitionId());
            viewGameIntroduceListVo.setIntroduction(competition.getIntroduction());
            viewGameIntroduceListVo.setGameName(competition.getCompetitionName());
            int kind=competition.getKind();
            if(kind==0){
                viewGameIntroduceListVo.setKind("未知种类");
            }else if(kind==1){
                viewGameIntroduceListVo.setKind("动物");
            }else if(kind==2){
                viewGameIntroduceListVo.setKind("植物");
            }else if(kind==3){
                viewGameIntroduceListVo.setKind("古代名人");
            }else if(kind==4){
                viewGameIntroduceListVo.setKind("建筑");
            }
            viewGameIntroduceListVo.setStatus("已发布");
            String time;
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
            time = dateformat.format(competition.getCreateTime());
            viewGameIntroduceListVo.setChangeTime(time);
            viewGameIntroduceListVos.add(viewGameIntroduceListVo);
        }
        return viewGameIntroduceListVos;
    }

    /**
     * 查看某一竞赛详情
     * @param commonIdRequest
     * @return
     * @throws Exception
     */
    @Override
    public List<GameDetailVo> viewGameDetail(CommonIdRequest commonIdRequest) throws Exception {


        List<GameDetailVo> gameDetailVos=new ArrayList<>();
        List<LQuestion> questions=new ArrayList<>();
        questions=lQuestionMapper.selectList(
                Wrappers.lambdaQuery(LQuestion.class)
                .eq(LQuestion::getCompetitionId,commonIdRequest.getId())
                .eq(LQuestion::getIsDel,"0")
        );
        if(questions==null){
            throw new BizException("该竞赛目前没有任何问题或者选项信息！");
        }
        for(LQuestion question:questions){
            GameDetailVo gameDetailVo=new GameDetailVo();
            gameDetailVo.setQuestionId(question.getQuestionId());
            gameDetailVo.setQuestionName(question.getQuestionName());
            gameDetailVo.setAnswer(question.getNote());
            List<LSelectAnswer> selectAnswers=new ArrayList<>();
            selectAnswers=lSelectAnswerMapper.selectList(
                    Wrappers.lambdaQuery(LSelectAnswer.class)
                    .eq(LSelectAnswer::getQuestionId,question.getQuestionId())
                    .eq(LSelectAnswer::getIsDel,"0")
            );
            if(selectAnswers==null){
                throw new BizException("该问题没有选项信息!");
            }
            int log=0;
            for(LSelectAnswer selectAnswer:selectAnswers){
                if(log==0){
                    gameDetailVo.setAnswerId1(selectAnswer.getAnswerId());
                    gameDetailVo.setAnswerName1(selectAnswer.getAnswerName());
                    log++;
                }else if(log==1){
                    gameDetailVo.setAnswerId2(selectAnswer.getAnswerId());
                    gameDetailVo.setAnswerName2(selectAnswer.getAnswerName());
                    log++;
                }else if(log==2){
                    gameDetailVo.setAnswerId3(selectAnswer.getAnswerId());
                    gameDetailVo.setAnswerName3(selectAnswer.getAnswerName());
                    log++;
                }else if(log==3) {
                    gameDetailVo.setAnswerId4(selectAnswer.getAnswerId());
                    gameDetailVo.setAnswerName4(selectAnswer.getAnswerName());
                    log++;
                }
            }
            gameDetailVos.add(gameDetailVo);
        }
        return gameDetailVos;
    }

    /**
     * 参赛者查看已参加竞赛列表
     * @param commonIdRequest
     * @return
     */
    @Override
    public List<ViewGameListOfCompetitorVo> viewGameListOfCompetitor(CommonIdRequest commonIdRequest) throws Exception {
        //commonIdRequest中的id是指明哪个人
        List<LScore> lScores1=lScoreMapper.selectList(
                Wrappers.lambdaQuery(LScore.class)
                .eq(LScore::getLoginId,commonIdRequest.getId())
                .eq(LScore::getIsDel,"0")
        );
        if(lScores1==null){
            throw new BizException("该参赛者还没有参加任何竞赛！");
        }
        List<LCompetition> competitions=new ArrayList<>();
        for(LScore lScore : lScores1){
            LCompetition lCompetition=competitionMapper.selectOne(
                    Wrappers.lambdaQuery(LCompetition.class)
                    .eq(LCompetition::getCompetitionId,lScore.getCompetitionId())
                    .eq(LCompetition::getIsDel,"0")
            );
            if(lCompetition==null){
                throw new BizException("没找到该成绩对应的竞赛！");
            }
            competitions.add(lCompetition);
        }
        //去竞赛表中找相应的信息
        List<ViewGameListOfCompetitorVo> viewGameListOfCompetitorVos=new ArrayList<>();
        for(LCompetition lCompetition:competitions){
            ViewGameListOfCompetitorVo viewGameListOfCompetitorVo=new ViewGameListOfCompetitorVo();
            viewGameListOfCompetitorVo.setGameId(lCompetition.getCompetitionId());
            viewGameListOfCompetitorVo.setGameName(lCompetition.getCompetitionName());
            //种类
            if(lCompetition.getKind()==1){
                viewGameListOfCompetitorVo.setKind("动物");
            }else if(lCompetition.getKind()==2){
                viewGameListOfCompetitorVo.setKind("植物");
            }else if(lCompetition.getKind()==3){
                viewGameListOfCompetitorVo.setKind("古代名人");
            }else if(lCompetition.getKind()==4){
                viewGameListOfCompetitorVo.setKind("建筑");
            }
            //总成绩
            String gameId=lCompetition.getCompetitionId();
            LScore lScore=lScoreMapper.selectOne(
                    Wrappers.lambdaQuery(LScore.class)
                    .eq(LScore::getCompetitionId,gameId)
                    .eq(LScore::getLoginId,commonIdRequest.getId())
                    .eq(LScore::getIsDel,"0")
            );
            if(lScore==null){
                throw new BizException("未找到该用户对应的该竞赛成绩记录！");
            }
            int totalScore=lScore.getScore();
            viewGameListOfCompetitorVo.setTotalScore(String.valueOf(lScore.getScore()));
            //排名
            List<LScore> lScores=lScoreMapper.selectList(
                    Wrappers.lambdaQuery(LScore.class)
                    .eq(LScore::getCompetitionId,lScore.getCompetitionId())
                    .notLike(LScore::getLoginId,commonIdRequest.getId())
            );
            int rank=1;
            for(LScore lScore1:lScores){
                if(lScore1.getScore()>totalScore){
                    rank+=1;
                }
            }
            viewGameListOfCompetitorVo.setRank(rank);
            viewGameListOfCompetitorVos.add(viewGameListOfCompetitorVo);
        }
        return viewGameListOfCompetitorVos;
    }

    /**
     * 删除知识竞赛
     * @param commonIdRequest
     */
    @Override
    public void deleteGame(CommonIdRequest commonIdRequest){
        LCompetition lCompetition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionId,commonIdRequest.getId())
                .eq(LCompetition::getIsDel,0)
        );
        if(lCompetition==null){
            throw new BizException("不存在该竞赛，无法删除！");
        }
        lCompetition.setStatus("2");
        lCompetition.setIsDel(1);
        competitionMapper.updateById(lCompetition);
        //将收藏表中已经收藏的 知识竞赛将状态调整为1
        List<LCollect> lCollects = lCollectMapper.selectList(
                Wrappers.lambdaQuery(LCollect.class)
                .eq(LCollect::getCompetitionId,commonIdRequest.getId())
                .eq(LCollect::getIsDel,"0")
        );
        for(LCollect lCollect:lCollects){
            lCollect.setIsDel("1");
            lCollectMapper.updateById(lCollect);
        }
    }

    @Override
    public GameDetailVo editGame(EditQuestionRequest editQuestionRequest){
        LCompetition competition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionId,editQuestionRequest.getId())
                .eq(LCompetition::getStatus,"1")
        );
        if(competition!=null){
            throw new BizException("该竞赛已经发布，不能再进行编辑！");
        }
        LCompetition lCompetition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionId,editQuestionRequest.getId())
                .eq(LCompetition::getStatus,"0")
        );
        if(lCompetition==null){
            throw new BizException("该竞赛已经删除，不能再进行编辑！");
        }
        //不需要返回list,只需要对应问题即可

        LQuestion question=lQuestionMapper.selectOne(
                Wrappers.lambdaQuery(LQuestion.class)
                        .eq(LQuestion::getCompetitionId,editQuestionRequest.getId())
                        .eq(LQuestion::getIsDel,"0")
                .eq(LQuestion::getQuestionId,editQuestionRequest.getQuestionId())
        );
        if(question==null){
            throw new BizException("当前数据库中没有该竞赛的该问题信息！");
        }
            GameDetailVo gameDetailVo=new GameDetailVo();
            gameDetailVo.setQuestionId(question.getQuestionId());
            gameDetailVo.setQuestionName(question.getQuestionName());
            gameDetailVo.setAnswer(question.getNote());
            List<LSelectAnswer> selectAnswers=new ArrayList<>();
            selectAnswers=lSelectAnswerMapper.selectList(
                    Wrappers.lambdaQuery(LSelectAnswer.class)
                            .eq(LSelectAnswer::getQuestionId,question.getQuestionId())
                            .eq(LSelectAnswer::getIsDel,"0"));

            if(selectAnswers==null){
                throw new BizException("该问题没有选项信息!");
            }
            int log=0;
            for(LSelectAnswer selectAnswer:selectAnswers){
                if(log==0){
                    gameDetailVo.setAnswerId1(selectAnswer.getAnswerId());
                    gameDetailVo.setAnswerName1(selectAnswer.getAnswerName());
                    log++;
                }else if(log==1){
                    gameDetailVo.setAnswerId2(selectAnswer.getAnswerId());
                    gameDetailVo.setAnswerName2(selectAnswer.getAnswerName());
                    log++;
                }else if(log==2){
                    gameDetailVo.setAnswerId3(selectAnswer.getAnswerId());
                    gameDetailVo.setAnswerName3(selectAnswer.getAnswerName());
                    log++;
                }else if(log==3) {
                    gameDetailVo.setAnswerId4(selectAnswer.getAnswerId());
                    gameDetailVo.setAnswerName4(selectAnswer.getAnswerName());
                    log++;
                }
            }
        return gameDetailVo;
    }

    /**
     * 提交问题实现
     * @param submitQuestionRequest
     */
    @Override
    public void submitQuestion(SubmitQuestionRequest submitQuestionRequest){
        //检查问题是否重复，选项是否重复，答案形式是否符合要求，问题答案，选项是否为空
        String question=submitQuestionRequest.getQuestionName();
        String s1=submitQuestionRequest.getAnswerName1();
        String s2=submitQuestionRequest.getAnswerName2();
        String s3=submitQuestionRequest.getAnswerName3();
        String s4=submitQuestionRequest.getAnswerName4();
        String s5=submitQuestionRequest.getAnswer();
        if(s1==null||s2==null||s3==null||s4==null||s5==null||question==null){
            throw new BizException("问题，选项，答案含有未填写的内容，请填写后再提交！");
        }
        //检查问题是否重复
        List<LQuestion> lQuestions=lQuestionMapper.selectList(
                Wrappers.lambdaQuery(LQuestion.class)
                .eq(LQuestion::getCompetitionId,submitQuestionRequest.getId())
                .eq(LQuestion::getIsDel,"0")
        );
        for(LQuestion lQuestion1:lQuestions){
            if(lQuestion1.getQuestionName().equals(submitQuestionRequest.getQuestionName())&&!(lQuestion1.getQuestionId().equals(submitQuestionRequest.getQuestionId()))){
                throw new BizException("编辑后的问题已经存在，请重新输入问题！");
            }
        }
        //检查选项是否重复
        if(s1.equals(s2)||s1.equals(s3)||s1.equals(s4)||s2.equals(s3)||s2.equals(s4)||s3.equals(s4)){
            throw new BizException("含有重复的选项，请检查后重新输入！");
        }
        //检查答案
        if(!("A".equals(s5)||"B".equals(s5)||"C".equals(s5)||"D".equals(s5))){
            throw new BizException("输入的答案不是A,B,C,D,请重新输入！");
        }
        LCompetition lCompetition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionId,submitQuestionRequest.getId())
                .eq(LCompetition::getIsDel,"0")
                .eq(LCompetition::getStatus,"1")
        );
        if(lCompetition!=null){
            throw new BizException("该知识竞赛已经发布，不能再修改问题！");
        }
        LCompetition lCompetition1=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                        .eq(LCompetition::getCompetitionId,submitQuestionRequest.getId())
                        .eq(LCompetition::getIsDel,"0")
                        .eq(LCompetition::getStatus,"0")
        );
        if(lCompetition1==null){
            throw new BizException("该知识竞赛已经删除，不能再修改问题！");
        }
        LQuestion lQuestion=lQuestionMapper.selectOne(
                Wrappers.lambdaQuery(LQuestion.class)
                .eq(LQuestion::getCompetitionId,submitQuestionRequest.getId())
                .eq(LQuestion::getQuestionId,submitQuestionRequest.getQuestionId())
                .eq(LQuestion::getIsDel,"0")
        );
        if(lQuestion==null){
            throw new BizException("未找到竞赛对应的该问题！");
        }
        lQuestion.setNote(submitQuestionRequest.getAnswer());
        lQuestion.setQuestionName(submitQuestionRequest.getQuestionName());
        lQuestionMapper.updateById(lQuestion);
        List<LSelectAnswer> lSelectAnswers=new ArrayList<>();
        lSelectAnswers=lSelectAnswerMapper.selectList(
                Wrappers.lambdaQuery(LSelectAnswer.class)
                .eq(LSelectAnswer::getQuestionId,submitQuestionRequest.getQuestionId())
                .eq(LSelectAnswer::getIsDel,"0")
        );
        if(lSelectAnswers==null){
            throw new BizException("该问题对应的选项为空！");
        }
        int log=0;
        for(LSelectAnswer lSelectAnswer:lSelectAnswers){
            if(log==0){
                lSelectAnswer.setAnswerName(submitQuestionRequest.getAnswerName1());
                log++;
            } else if(log==1){
                lSelectAnswer.setAnswerName(submitQuestionRequest.getAnswerName2());
                log++;
            } else if(log==2){
                lSelectAnswer.setAnswerName(submitQuestionRequest.getAnswerName3());
                log++;
            } else if(log==3){
                lSelectAnswer.setAnswerName(submitQuestionRequest.getAnswerName4());
                log++;
            }
            lSelectAnswerMapper.updateById(lSelectAnswer);
        }
    }
    /**
     * 查看竞赛状态
     */
    @Override
    public void viewStatus(CommonIdRequest commonIdRequest){
        LCompetition lCompetition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionId,commonIdRequest.getId())
                .eq(LCompetition::getStatus,"0")
                .eq(LCompetition::getIsDel,0)
        );
        if(lCompetition==null){
            throw new BizException("该竞赛已经发布，不能再添加题目");
        }
    }
}
