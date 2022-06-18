package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "问题编辑请求")
public class EditQuestionRequest {
    @ApiModelProperty(value = "知识竞赛id")
    private String id;

    @ApiModelProperty(value = "问题id")
    private String questionId;
}
