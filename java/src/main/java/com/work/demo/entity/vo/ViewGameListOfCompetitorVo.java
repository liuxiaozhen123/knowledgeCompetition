package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "参赛者查看已参加的竞赛列表")
public class ViewGameListOfCompetitorVo {

    @ApiModelProperty(value = "竞赛名称")
    private String gameName;

    @ApiModelProperty(value = "竞赛id")
    private String gameId;

    @ApiModelProperty(value = "竞赛种类")
    private String kind;

    @ApiModelProperty(value = "得分")
    private String totalScore;

    @ApiModelProperty(value = "当前个人排名")
    private int rank;

}
