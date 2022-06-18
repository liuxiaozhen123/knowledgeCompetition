package com.work.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("L_login")
@ApiModel(value="LLogin对象", description="")
public class LLogin extends Model<LLogin> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名/账号")
    private String loginId;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "创建时间（默认当前时间)")
    private Date createDate;

    @ApiModelProperty(value = "是否删除")
    private String isDel;

    @ApiModelProperty(value = "修改时间（默认当前时间）")
    private Date updateTime;

    @ApiModelProperty(value = "用户类型 0：竞赛组织者 1：参赛者 2：系统管理员")
    private String type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
