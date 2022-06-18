package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "参赛者查看某一竞赛详情")
public class GameDetailVo {
    @ApiModelProperty(value = "问题描述")
    private String questionName;

    @ApiModelProperty(value = "问题id")
    private String questionId;

    @ApiModelProperty(value = "选项1")
    private String answerName1;

    @ApiModelProperty(value = "选项1id")
    private String answerId1;

    @ApiModelProperty(value = "选项2")
    private String answerName2;

    @ApiModelProperty(value = "选项2id")
    private String answerId2;

    @ApiModelProperty(value = "选项3")
    private String answerName3;

    @ApiModelProperty(value = "选项3id")
    private String answerId3;

    @ApiModelProperty(value = "选项4")
    private String answerName4;

    @ApiModelProperty(value = "选项4id")
    private String answerId4;

    @ApiModelProperty(value = "答案")
    private String answer;

}
