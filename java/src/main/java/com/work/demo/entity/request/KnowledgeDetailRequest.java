package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "竞赛组织者修改或添加知识推荐请求参数")
public class KnowledgeDetailRequest {
    @ApiModelProperty(value = "知识推荐名称")
    private String knowledgeName;

    @ApiModelProperty(value = "知识推荐id")
    private String knowledgeId;

    @ApiModelProperty(value = "知识推荐详情")
    private String detail;
}
