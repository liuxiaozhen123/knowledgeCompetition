package com.work.demo.service;

import com.work.demo.entity.LKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.demo.entity.request.AddKnowledgeRequest;
import com.work.demo.entity.request.CommonIdRequest;
import com.work.demo.entity.request.KnowledgeDetailRequest;
import com.work.demo.entity.request.SetKnowledgeKindRequest;
import com.work.demo.entity.vo.CommonIdVo;
import com.work.demo.entity.vo.KnowledgeDetailVo;
import com.work.demo.entity.vo.ViewKnowledgeVo;

import java.util.List;

/**
 * <p>
 * 知识推荐表 服务类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
public interface ILKnowledgeService extends IService<LKnowledge> {

    List<ViewKnowledgeVo> viewKnowledgeList(CommonIdRequest commonIdRequest) throws Exception;

    KnowledgeDetailVo viewKnowledgeDetail(CommonIdRequest commonIdRequest) throws Exception;

    void publishKnowledge(CommonIdRequest commonIdRequest) throws Exception;

    LKnowledge addKnowledge(AddKnowledgeRequest addKnowledgeRequest) throws Exception;

    void setKnowledgeKind(SetKnowledgeKindRequest setKnowledgeKindRequest);


    LKnowledge editKnowledge(CommonIdRequest commonIdRequest);

    LKnowledge submitKnowledge(KnowledgeDetailRequest knowledgeDetailRequest);

    void deleteKnowledge(CommonIdRequest commonIdRequest);

    List<CommonIdVo> selectCompetitionName();

    List<ViewKnowledgeVo> viewKnowledgeList1(CommonIdRequest commonIdRequest);
}
