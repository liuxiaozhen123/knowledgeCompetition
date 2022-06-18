package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "批量添加问题选项请求参数")
public class AddQuestionRequest {

    @ApiModelProperty(value = "赛题id")
    private String competitionId;

    @ApiModelProperty(value = "问题1")
    private String questionName;

    @ApiModelProperty(value = "选项1")
    private String select1;

    @ApiModelProperty(value = "选项2")
    private String select2;

    @ApiModelProperty(value = "选项3")
    private String select3;

    @ApiModelProperty(value = "选项4")
    private String select4;

    @ApiModelProperty(value = "选项5")
    private String select5;
}
