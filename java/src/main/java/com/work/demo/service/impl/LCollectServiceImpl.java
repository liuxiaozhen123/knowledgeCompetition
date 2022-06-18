package com.work.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.LCollect;
import com.work.demo.entity.LCompetition;
import com.work.demo.entity.LKnowledge;
import com.work.demo.entity.request.CollectGameRequest;
import com.work.demo.entity.request.ViewCollectRequest;
import com.work.demo.entity.vo.CollectListVo;
import com.work.demo.mapper.LCollectMapper;
import com.work.demo.mapper.LCompetitionMapper;
import com.work.demo.mapper.LKnowledgeMapper;
import com.work.demo.service.ILCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-05-20
 */
@Service
public class LCollectServiceImpl extends ServiceImpl<LCollectMapper, LCollect> implements ILCollectService {

    @Resource
    LCollectMapper collectMapper;

    @Resource
    LCompetitionMapper competitionMapper;

    @Resource
    LKnowledgeMapper knowledgeMapper;

    /**
     * 收藏竞赛或知识
     * @param collectGameRequest
     */
    @Override
    public  void collectGame(CollectGameRequest collectGameRequest){
        //解决重复收藏问题
        LCollect collect1=collectMapper.selectOne(
                Wrappers.lambdaQuery(LCollect.class)
                .eq(LCollect::getLoginId,collectGameRequest.getLoginId())
                .eq(LCollect::getCompetitionId,collectGameRequest.getId())
                .eq(LCollect::getIsDel,"0")
        );
        LCollect collect2=collectMapper.selectOne(
                Wrappers.lambdaQuery(LCollect.class)
                .eq(LCollect::getLoginId,collectGameRequest.getLoginId())
                .eq(LCollect::getKnowledgeId,collectGameRequest.getId())
                .eq(LCollect::getIsDel,"0")
        );
        if(collect1!=null || collect2!=null){
            throw new BizException("该竞赛或小知识已经被收藏过，不允许重复收藏！");
        }
        LCollect lCollect=new LCollect();
        lCollect.setLoginId(collectGameRequest.getLoginId());
        lCollect.setIsDel("0");
        lCollect.setCreateDate(new Date(System.currentTimeMillis()));
        lCollect.setUpdateDate(new Date(System.currentTimeMillis()));
        //查看是竞赛还是知识
        LCompetition lCompetition=competitionMapper.selectOne(
                Wrappers.lambdaQuery(LCompetition.class)
                .eq(LCompetition::getCompetitionId,collectGameRequest.getId())
                .eq(LCompetition::getIsDel,0)
        );
        if(lCompetition!=null){
            lCollect.setCompetitionId(collectGameRequest.getId());
            lCollect.setType("0");
        }else{
            lCollect.setKnowledgeId(collectGameRequest.getId());
            lCollect.setType("1");
        }
        collectMapper.insert(lCollect);
    }

    /**
     * 查看收藏列表
     * @param viewCollectRequest
     * @return
     */
    @Override
    public List<CollectListVo> viewCollectGame(ViewCollectRequest viewCollectRequest){
        List<LCollect> lCollects=collectMapper.selectList(
                Wrappers.lambdaQuery(LCollect.class)
                .eq(LCollect::getLoginId,viewCollectRequest.getLoginId())
                .eq(LCollect::getType,viewCollectRequest.getType())
                .eq(LCollect::getIsDel,"0")
        );
        if(lCollects==null){
            throw new BizException("你还没有任何关于竞赛或知识的收藏！");
        }
        List<CollectListVo> collectListVos=new ArrayList<>();
        for(LCollect collect:lCollects){
            //查看竞赛
            if("0".equals(viewCollectRequest.getType())){
                LCompetition lCompetition=competitionMapper.selectOne(
                        Wrappers.lambdaQuery(LCompetition.class)
                        .eq(LCompetition::getCompetitionId,collect.getCompetitionId())
                        .notLike(LCompetition::getStatus,"2")
                );
                if(lCompetition==null){
                    throw new BizException("收藏的某一知识竞赛可能已经删除！");
                }
                CollectListVo collectListVo=new CollectListVo();
                collectListVo.setCollectName(lCompetition.getCompetitionName());
                collectListVo.setIntroduction(lCompetition.getIntroduction());
                int kind=lCompetition.getKind();
                if(kind==0){
                    collectListVo.setKind("未知种类");
                }else if(kind==1){
                    collectListVo.setKind("动物");
                }else if(kind==2){
                    collectListVo.setKind("植物");
                }else if(kind==3){
                    collectListVo.setKind("古代名人");
                }else if(kind==4){
                    collectListVo.setKind("建筑");
                }
                String status=lCompetition.getStatus();
                if("0".equals(status)){
                    collectListVo.setStatus("未发布");
                }else if("1".equals(status)){
                    collectListVo.setStatus("已发布");
                }else if("2".equals(status)){
                    collectListVo.setStatus("已删除");
                }
                String time;
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
                time = dateformat.format(lCompetition.getCreateTime());
                collectListVo.setChangeTime(time);
                collectListVos.add(collectListVo);
            } else if("1".equals(viewCollectRequest.getType())){
                LKnowledge lKnowledge=knowledgeMapper.selectOne(
                        Wrappers.lambdaQuery(LKnowledge.class)
                                .eq(LKnowledge::getKnowledgeId,collect.getKnowledgeId())
                                .notLike(LKnowledge::getIsDel,"2")
                );
                if(lKnowledge==null){
                    throw new BizException("收藏的某一知识可能已经删除！");
                }
                CollectListVo collectListVo1=new CollectListVo();
                collectListVo1.setCollectName(lKnowledge.getTitle());
                collectListVo1.setIntroduction(lKnowledge.getTitleDetail());
                String kind=lKnowledge.getNote();
                if("0".equals(kind)){
                    collectListVo1.setKind("未知种类");
                }else if("1".equals(kind)){
                    collectListVo1.setKind("动物");
                }else if("2".equals(kind)){
                    collectListVo1.setKind("植物");
                }else if("3".equals(kind)){
                    collectListVo1.setKind("古代名人");
                }else if("4".equals(kind)){
                    collectListVo1.setKind("建筑");
                }
                String status=lKnowledge.getIsDel();
                if("0".equals(status)){
                    collectListVo1.setStatus("未发布");
                }else if("1".equals(status)){
                    collectListVo1.setStatus("已发布");
                }else if("2".equals(status)){
                    collectListVo1.setStatus("已删除");
                }
                String time;
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
                time = dateformat.format(lKnowledge.getUpdateTime());
                collectListVo1.setChangeTime(time);
                collectListVos.add(collectListVo1);
            }
        }
        return collectListVos;
    }
}
