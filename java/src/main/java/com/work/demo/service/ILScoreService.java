package com.work.demo.service;

import com.work.demo.entity.LScore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.demo.entity.request.CommonIdRequest;
import com.work.demo.entity.request.SubmitGameRequest;
import com.work.demo.entity.vo.CommonDicVo;
import com.work.demo.entity.vo.CommonDouDicVo;
import com.work.demo.entity.vo.ScoreDetailVo;

import java.util.List;

/**
 * <p>
 * 用户得分表 服务类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
public interface ILScoreService extends IService<LScore> {

    CommonDouDicVo submitGame(SubmitGameRequest submitGameRequest) throws Exception;

    List<ScoreDetailVo> viewScoreDetail(CommonIdRequest commonIdRequest) throws Exception;
}
