package com.work.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("L_user")
@ApiModel(value="LUser对象", description="用户信息表")
public class LUser extends Model<LUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户姓名:昵称")
    private String userName;

    @ApiModelProperty(value = "登录id")
    private String loginId;

    @ApiModelProperty(value = "0:未知 1:男 2:女")
    private String gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "0:未删除 1:已删除")
    private String isDel;

    @ApiModelProperty(value = "职业")
    private String occupation;

    @ApiModelProperty(value = "个人地址")
    private String address;

    @ApiModelProperty(value = "电子邮箱")
    private String email;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
