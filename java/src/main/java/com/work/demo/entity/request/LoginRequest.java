package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 刘小真
 */
@Data
@ApiModel(value = "登录请求参数")
public class LoginRequest {
    @ApiModelProperty(value = "用户名（可为昵称）loginId")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户类型 0:竞赛组织者 1：参赛者 2：系统管理员")
    private String type;

}
