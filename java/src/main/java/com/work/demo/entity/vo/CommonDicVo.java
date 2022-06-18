package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用单个字典
 * @author zuozhiwei
 */
@Data
@ApiModel(value = "单个字典")
public class CommonDicVo {

    @ApiModelProperty(value = "键")
    private String key;

    @ApiModelProperty(value = "值")
    private String value;


}
