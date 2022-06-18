package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "参赛者查看某一竞赛成绩详情")
public class ScoreDetailVo {
    @ApiModelProperty(value = "昵称")
    private String loginId;

    @ApiModelProperty(value = "分数")
    private String totalScore;

    @ApiModelProperty(value = "作答时间")
    private String createTime;

    @ApiModelProperty(value = "个人排名")
    private int rank;
}
