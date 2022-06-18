package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "添加竞赛概述请求参数")
public class AddGameRequest {
    @ApiModelProperty(value = "竞赛名称")
    private String competitionName;
    @ApiModelProperty(value = "竞赛简介")
    private String introduction;
}
