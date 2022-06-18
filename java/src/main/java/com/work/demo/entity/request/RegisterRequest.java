package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 刘小真
 */
@Data
@ApiModel(value = "注册请求参数")
public class RegisterRequest {
    @ApiModelProperty(value = "用户名（可为昵称）")
    private String userName;

    @ApiModelProperty(value = "第一次输入密码")
    private String password1;

    @ApiModelProperty(value = "第二次输入密码，确认密码")
    private String password2;

    @ApiModelProperty(value = "第二次输入密码，确认密码")
    private String type;


}
