package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zuozhiwei
 */
@Data
@ApiModel(value = "通用id请求")
public class CommonIdRequest {
    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不能为空")
    private String id;
}
