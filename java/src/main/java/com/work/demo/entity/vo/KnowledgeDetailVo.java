package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "竞赛组织者查看某一知识推荐详情")
public class KnowledgeDetailVo {
    @ApiModelProperty(value = "知识推荐名称")
    private String knowledgeName;

    @ApiModelProperty(value = "知识推荐id")
    private String knowledgeId;

    @ApiModelProperty(value = "知识推荐详情")
    private String detail;
}
