package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "参赛者提交竞赛请求参数")
public class SubmitGameRequest {
    @ApiModelProperty(value = "竞赛id")
    private String competitionId;

    @ApiModelProperty(value = "用户id")
    private String loginId;

    @ApiModelProperty(value = "作答列表")
    private List<String> answerRequestList;
}
