package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "提交赛题参数")
public class ViewGameDetailRequest {

    @ApiModelProperty(value = "用户名（可为昵称）loginId")
    private String loginId;

    @ApiModelProperty(value = "赛题id")
    private String competitionId;


}
