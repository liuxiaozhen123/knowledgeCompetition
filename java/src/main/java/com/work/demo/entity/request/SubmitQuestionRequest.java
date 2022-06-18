package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "提交问题请求参数")
public class SubmitQuestionRequest {
    @ApiModelProperty(value = "知识竞赛id")
    private String id;

    @ApiModelProperty(value = "问题id")
    private String questionId;

    @ApiModelProperty(value = "问题描述")
    private String questionName;

    @ApiModelProperty(value = "选项1")
    private String answerName1;

    @ApiModelProperty(value = "选项2")
    private String answerName2;

    @ApiModelProperty(value = "选项3")
    private String answerName3;

    @ApiModelProperty(value = "选项4")
    private String answerName4;

    @ApiModelProperty(value = "答案")
    private String answer;
}
