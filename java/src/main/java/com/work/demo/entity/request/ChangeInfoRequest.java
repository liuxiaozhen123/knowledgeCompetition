package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "用户个人信息请求信息")
public class ChangeInfoRequest {
    @ApiModelProperty(value = "用户姓名:昵称")
    private String userName;

    @ApiModelProperty(value = "登录id")
    private String loginId;

    @ApiModelProperty(value = "0:未知 1:男 2:女")
    private String gender;

    @ApiModelProperty(value = "生日：年")
    private String year;

    @ApiModelProperty(value = "生日：月")
    private String month;

    @ApiModelProperty(value = "生日：日")
    private String day;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "职业")
    private String occupation;

    @ApiModelProperty(value = "个人地址")
    private String address;

    @ApiModelProperty(value = "电子邮箱")
    private String email;
}
