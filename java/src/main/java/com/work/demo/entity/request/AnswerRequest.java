package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "单个题目请求参数")
public class AnswerRequest {

//    @ApiModelProperty(value = "问题Id")
//    private String questionId;

    @ApiModelProperty(value = "答题内容")
    private String answer;
}
