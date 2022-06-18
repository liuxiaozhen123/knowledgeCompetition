package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "竞赛组织者组织竞赛概述列表")
public class ViewGameIntroduceListVo {
    @ApiModelProperty(value = "竞赛名称")
    private String gameName;

    @ApiModelProperty(value = "竞赛id")
    private String gameId;

    @ApiModelProperty(value = "竞赛种类")
    private String kind;

    @ApiModelProperty(value = "竞赛状态")
    private String status;

    @ApiModelProperty(value = "竞赛简介")
    private String introduction;

    @ApiModelProperty(value = "最近一次修改时间")
    private String changeTime;

}
