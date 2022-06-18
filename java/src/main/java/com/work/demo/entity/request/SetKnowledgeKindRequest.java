package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "设置知识推荐种类请求参数")
public class SetKnowledgeKindRequest {
    @ApiModelProperty(value = "知识推荐id")
    private String knowledgeId;

    @ApiModelProperty(value = "知识推荐种类")
    private String kind;
}
