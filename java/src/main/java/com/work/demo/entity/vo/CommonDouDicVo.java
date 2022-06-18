package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 刘小真
 */
@Data
@ApiModel(value = "双字典")
public class CommonDouDicVo {
    @ApiModelProperty(value = "键1")
    private String key1;

    @ApiModelProperty(value = "值1")
    private String value1;

    @ApiModelProperty(value = "键2")
    private String key2;

    @ApiModelProperty(value = "值2")
    private String value2;

}
