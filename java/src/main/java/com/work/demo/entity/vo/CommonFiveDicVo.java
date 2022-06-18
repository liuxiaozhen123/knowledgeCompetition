package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 刘小真
 */
@Data
@ApiModel(value = "常用五参字典")
public class CommonFiveDicVo {
    @ApiModelProperty(value = "键1")
    private String key1;

    @ApiModelProperty(value = "值1")
    private String value1;

    @ApiModelProperty(value = "键2")
    private String key2;

    @ApiModelProperty(value = "值2")
    private String value2;

    @ApiModelProperty(value = "键3")
    private String key3;

    @ApiModelProperty(value = "值3")
    private String value3;

    @ApiModelProperty(value = "键4")
    private String key4;

    @ApiModelProperty(value = "值4")
    private String value4;

    @ApiModelProperty(value = "键5")
    private String key5;

    @ApiModelProperty(value = "值5")
    private String value5;
}
