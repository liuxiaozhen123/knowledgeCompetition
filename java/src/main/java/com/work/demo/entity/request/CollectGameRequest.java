package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "收藏竞赛或知识请求参数")
public class CollectGameRequest {
    @ApiModelProperty(value = "登录id")
    private String loginId;

    @ApiModelProperty(value = "竞赛或知识id")
    private String id;

}
