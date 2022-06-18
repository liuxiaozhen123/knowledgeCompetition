package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "通用单条数据")
public class CommonIdVo {
    @ApiModelProperty(value = "单个字")
    private String commonId;

}
