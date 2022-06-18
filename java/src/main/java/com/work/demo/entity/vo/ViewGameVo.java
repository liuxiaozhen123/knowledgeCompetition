package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "竞赛组织者组织竞赛列表")
public class ViewGameVo {

    @ApiModelProperty(value = "竞赛名称")
    private String gameName;

    @ApiModelProperty(value = "竞赛id")
    private String gameId;

    @ApiModelProperty(value = "竞赛种类")
    private String kind;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    //    @ApiModelProperty(value = "竞赛状态")
//    private String status;
}
