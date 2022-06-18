package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "查看收藏请求参数")
public class ViewCollectRequest {
    @ApiModelProperty(value = "登录id")
    private String loginId;

    @ApiModelProperty(value = "类型0：查看竞赛，1：查看知识")
    private String type;
}
