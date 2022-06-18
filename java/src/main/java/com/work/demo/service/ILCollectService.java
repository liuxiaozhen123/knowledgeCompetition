package com.work.demo.service;

import com.work.demo.entity.LCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.demo.entity.request.CollectGameRequest;
import com.work.demo.entity.request.ViewCollectRequest;
import com.work.demo.entity.vo.CollectListVo;

import java.util.List;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-05-20
 */
public interface ILCollectService extends IService<LCollect> {

    void collectGame(CollectGameRequest collectGameRequest);


    List<CollectListVo> viewCollectGame(ViewCollectRequest viewCollectRequest);
}
