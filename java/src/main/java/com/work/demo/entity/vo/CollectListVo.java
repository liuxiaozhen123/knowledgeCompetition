package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "收集竞赛或知识列表返回参数")
public class CollectListVo {

    @ApiModelProperty(value = "收集名称")
    private String collectName;

    @ApiModelProperty(value = "种类")
    private String kind;

    @ApiModelProperty(value = "收集物状态")
    private String status;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "最近一次修改时间")
    private String changeTime;
}
