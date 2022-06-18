package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "添加相关知识概述请求参数")
public class AddKnowledgeRequest {

    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "竞赛名称")
    private String competitionName;

}
