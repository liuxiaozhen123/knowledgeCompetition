package com.work.demo.service.impl;

import com.work.demo.entity.LQuestion;
import com.work.demo.mapper.LQuestionMapper;
import com.work.demo.service.ILQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题表 服务实现类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Service
public class LQuestionServiceImpl extends ServiceImpl<LQuestionMapper, LQuestion> implements ILQuestionService {

}
