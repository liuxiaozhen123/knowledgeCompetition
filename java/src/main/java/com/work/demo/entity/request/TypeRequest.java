package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 刘小真
 */
@Data
@ApiModel(value = "前后端类型请求参数")
public class TypeRequest {
    @ApiModelProperty(value = "按前后端查找")
    private String type;

    @ApiModelProperty(value = "分页信息")
    private PageRequest pageRequest;
}
