package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "设置竞赛种类请求参数")
public class SetGameKindRequest {
    @ApiModelProperty(value = "竞赛id")
    private String competitionId;
    @ApiModelProperty(value = "竞赛种类")
    private String kind;


}
