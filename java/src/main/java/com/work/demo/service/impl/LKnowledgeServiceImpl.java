package com.work.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.LCollect;
import com.work.demo.entity.LCompetition;
import com.work.demo.entity.LKnowledge;
import com.work.demo.entity.LLogin;
import com.work.demo.entity.request.*;
import com.work.demo.entity.vo.CommonIdVo;
import com.work.demo.entity.vo.KnowledgeDetailVo;
import com.work.demo.entity.vo.ViewKnowledgeVo;
import com.work.demo.mapper.LCollectMapper;
import com.work.demo.mapper.LCompetitionMapper;
import com.work.demo.mapper.LKnowledgeMapper;
import com.work.demo.mapper.LLoginMapper;
import com.work.demo.service.ILKnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 知识推荐表 服务实现类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Service
public class LKnowledgeServiceImpl extends ServiceImpl<LKnowledgeMapper, LKnowledge> implements ILKnowledgeService {

    @Resource
    LKnowledgeMapper lKnowledgeMapper;

    @Resource
    LCompetitionMapper lCompetitionMapper;

    @Resource
    LLoginMapper loginMapper;

    @Resource
    LCollectMapper collectMapper;

    @Override
    public List<ViewKnowledgeVo> viewKnowledgeList(CommonIdRequest commonIdRequest) throws Exception {
        //判断身份，是竞赛组织者还是参赛者
        LLogin lLogin=loginMapper.selectOne(
                Wrappers.lambdaQuery(LLogin.class)
                .eq(LLogin::getLoginId,commonIdRequest.getId())
                .eq(LLogin::getIsDel,"0")
        );
        if(lLogin==null){
            throw new BizException("未找到该用户！");
        }
        String type=lLogin.getType();
        List<LKnowledge> knowledges=new ArrayList<>();
        if("0".equals(type)){
            knowledges=lKnowledgeMapper.selectList(
                    Wrappers.lambdaQuery(LKnowledge.class)
                            .isNotNull(LKnowledge::getId)
                            .notLike(LKnowledge::getIsDel,"2")
            );
        } else if("1".equals(type)){
            knowledges=lKnowledgeMapper.selectList(
                    Wrappers.lambdaQuery(LKnowledge.class)
                            .isNotNull(LKnowledge::getId)
                            .eq(LKnowledge::getIsDel,"1")
            );
        }
        if(knowledges==null){
            throw new BizException("当前数据库中没有任何知识推荐记录！");
        }
        List<ViewKnowledgeVo> viewKnowledgeVos=new ArrayList<>();
        for(LKnowledge lKnowledge:knowledges){
            ViewKnowledgeVo viewKnowledgeVo=new ViewKnowledgeVo();
            viewKnowledgeVo.setKnowledgeId(lKnowledge.getKnowledgeId());
            viewKnowledgeVo.setKnowledgeName(lKnowledge.getTitle());
            String kind=lKnowledge.getNote() ;
            if("0".equals(kind)){
                viewKnowledgeVo.setKind("未设置");
            }else if("1".equals(kind)){
                viewKnowledgeVo.setKind("动物");
            } else if("2".equals(kind)){
                viewKnowledgeVo.setKind("植物");
            } else if("3".equals(kind)){
                viewKnowledgeVo.setKind("历史名人");
            } else if("4".equals(kind)){
                viewKnowledgeVo.setKind("建筑");
            }
            String status=lKnowledge.getIsDel();
            if("0".equals(status)){
                viewKnowledgeVo.setStatus("未发布");
            } else if("1".equals(status)){
                viewKnowledgeVo.setStatus("已发布");
            }else if("2".equals(status)){
                viewKnowledgeVo.setStatus("已删除");
            }
            String time;
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
            time = dateformat.format(lKnowledge.getUpdateTime());
            viewKnowledgeVo.setCreateTime(time);
            viewKnowledgeVo.setCompetitionName(lKnowledge.getCompetitionName());
            viewKnowledgeVos.add(viewKnowledgeVo);
        }
        return viewKnowledgeVos;
    }

    @Override
    public KnowledgeDetailVo viewKnowledgeDetail(CommonIdRequest commonIdRequest) throws Exception {
        LKnowledge lKnowledge=new LKnowledge();
        lKnowledge=lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getKnowledgeId,commonIdRequest.getId())
        );
        if(lKnowledge==null){
            throw new BizException("未找到该知识推荐的详细信息！");
        }
        KnowledgeDetailVo knowledgeDetailVo=new KnowledgeDetailVo();
        knowledgeDetailVo.setDetail(lKnowledge.getTitleDetail());
        knowledgeDetailVo.setKnowledgeName(lKnowledge.getTitle());
        knowledgeDetailVo.setKnowledgeId(lKnowledge.getKnowledgeId());
        return knowledgeDetailVo;
    }

    /**
     * 修改或者发布发布知识推荐
     * @param commonIdRequest
     * @throws Exception
     */
    @Override
    public void publishKnowledge(CommonIdRequest commonIdRequest) throws Exception {
        LKnowledge lKnowledge=new LKnowledge();
        lKnowledge=lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getKnowledgeId,commonIdRequest.getId())
                .eq(LKnowledge::getIsDel,"0")
        );
        //知识推荐状态为未发布和已经发布都以修改
        if(lKnowledge==null){
            throw new BizException("该小知识已经发布，不能重复发布！");
        }
        if(lKnowledge.getTitleDetail()==null){
            throw new BizException("该小知识详细内容为空，请添加详细内容后再发布！");
        }
        lKnowledge.setIsDel("1");
        lKnowledgeMapper.updateById(lKnowledge);
    }

    /**
     * 添加知识推荐概述
     * @param addKnowledgeRequest
     * @return
     */
    @Override
    public LKnowledge addKnowledge(AddKnowledgeRequest addKnowledgeRequest) throws Exception {
        LCompetition lCompetition=lCompetitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionName,addKnowledgeRequest.getCompetitionName())
                .notLike(LCompetition::getStatus,"2")
        );
        if(lCompetition==null){
            throw new BizException("该知识竞赛不存在，请重新输入！");
        }
        LKnowledge lKnowledge1=lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getTitle,addKnowledgeRequest.getId())
                .notLike(LKnowledge::getIsDel,"2")
        );
        if(lKnowledge1!=null&& !"2".equals(lKnowledge1.getIsDel())){
            throw new BizException("该知识名称已经存在，请重新添加相关知识！");
        }
        LKnowledge lKnowledge=new LKnowledge();
        lKnowledge.setKnowledgeId(UUID.randomUUID().toString());
        lKnowledge.setIsDel("0");
        lKnowledge.setTitle(addKnowledgeRequest.getId());
        lKnowledge.setNote("0");
        lKnowledge.setCompetitionName(addKnowledgeRequest.getCompetitionName());
        lKnowledge.setUpdateTime(new Date(System.currentTimeMillis()));
        lKnowledgeMapper.insert(lKnowledge);
        LKnowledge lKnowledge2 =new LKnowledge();
        lKnowledge2 =lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getTitle,addKnowledgeRequest.getId())

        );
        return lKnowledge2;
}

    @Override
    public void setKnowledgeKind(SetKnowledgeKindRequest setKnowledgeKindRequest) {
        LKnowledge lKnowledge = new LKnowledge();
        lKnowledge = lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                        .eq(LKnowledge::getKnowledgeId, setKnowledgeKindRequest.getKnowledgeId())
                        .notLike(LKnowledge::getIsDel, "2")
        );
        if (lKnowledge == null) {
            throw new BizException("该知识推荐已经删除，不能再设置种类");
        }
        String kind = setKnowledgeKindRequest.getKind();
        if ("动物".equals(kind)) {
            lKnowledge.setNote("1");
        } else if ("植物".equals(kind)) {
            lKnowledge.setNote("2");
        } else if ("古代名人".equals(kind)) {
            lKnowledge.setNote("3");
        } else if ("建筑".equals(kind)) {
            lKnowledge.setNote("4");
        } else{
            throw new BizException("输入非种类外的错误信息！");
        }
        lKnowledgeMapper.updateById(lKnowledge);
    }

    @Override
    public LKnowledge editKnowledge(CommonIdRequest commonIdRequest){
        LKnowledge lKnowledge=lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getKnowledgeId,commonIdRequest.getId())
                .eq(LKnowledge::getIsDel,"1")
        );
        if(lKnowledge!=null){
            throw new BizException("该知识推荐已经发布，不能再进行编辑！");
        }
        LKnowledge lKnowledge1=lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getKnowledgeId,commonIdRequest.getId())
                .eq(LKnowledge::getIsDel,"0")
        );
        if(lKnowledge1==null){
            throw new BizException("该知识推荐已经删除！");
        }
        return lKnowledge1;
    }

    @Override
    public LKnowledge submitKnowledge(KnowledgeDetailRequest knowledgeDetailRequest){
        LKnowledge lKnowledge=lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getKnowledgeId,knowledgeDetailRequest.getKnowledgeId())
                .eq(LKnowledge::getIsDel,"0")
        );
        if(lKnowledge==null){
            throw new BizException("该知识推荐已经发布，不能再进行提交！");
        }
        lKnowledge.setTitle(knowledgeDetailRequest.getKnowledgeName());
        lKnowledge.setTitleDetail(knowledgeDetailRequest.getDetail());
        lKnowledgeMapper.updateById(lKnowledge);
        LKnowledge lKnowledge1=lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getKnowledgeId,knowledgeDetailRequest.getKnowledgeId())
                .eq(LKnowledge::getIsDel,"0")
        );
        return lKnowledge1;
    }

    @Override
    public void deleteKnowledge(CommonIdRequest commonIdRequest){
        LKnowledge lKnowledge=lKnowledgeMapper.selectOne(
                Wrappers.lambdaQuery(LKnowledge.class)
                .eq(LKnowledge::getKnowledgeId,commonIdRequest.getId())
                .notLike(LKnowledge::getIsDel,"2")
        );
        if(lKnowledge==null){
            throw new BizException("该知识推荐已经删除，不能再进行删除！");
        }

        lKnowledge.setIsDel("2");
        lKnowledgeMapper.updateById(lKnowledge);
        List<LCollect> lCollectList=collectMapper.selectList(
                Wrappers.lambdaQuery(LCollect.class)
                        .eq(LCollect::getKnowledgeId,commonIdRequest.getId())
        );
        for(LCollect collect:lCollectList){
            collect.setIsDel("1");
            collectMapper.updateById(collect);
        }
    }

    /**
     * 搜集已经发布知识竞赛名称
     * @return
     */
    @Override
    public List<CommonIdVo> selectCompetitionName(){
        List<LCompetition> lCompetitions =lCompetitionMapper.selectList(
                Wrappers.lambdaQuery(LCompetition.class)
                .notLike(LCompetition::getStatus,"2")
        );
        if(lCompetitions==null){
            throw new BizException("数据库中不存在已经发布的知识竞赛");
        }
        List<CommonIdVo> competitionNames=new ArrayList<>();
        for(LCompetition lCompetition:lCompetitions){
            String competitionName=lCompetition.getCompetitionName();
            CommonIdVo commonIdVo=new CommonIdVo();
            commonIdVo.setCommonId(competitionName);
            competitionNames.add(commonIdVo);
        }
        return competitionNames;
    }

    @Override
    public List<ViewKnowledgeVo> viewKnowledgeList1(CommonIdRequest commonIdRequest){
        List<ViewKnowledgeVo> viewKnowledgeVos=new ArrayList<>();
        return viewKnowledgeVos;
    }
}
