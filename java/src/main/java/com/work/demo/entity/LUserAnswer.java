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
 * 用户答题表
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("L_user_answer")
@ApiModel(value="LUserAnswer对象", description="用户答题表")
public class LUserAnswer extends Model<LUserAnswer> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户答题id")
    private String userAnswerId;

    @ApiModelProperty(value = "用户选项")
    private String userAnswerName;

    @ApiModelProperty(value = "问题id")
    private String questionId;

    @ApiModelProperty(value = "用户登录id")
    private String loginId;

    @ApiModelProperty(value = "参赛者总得分")
    private Integer totalScore;

    @ApiModelProperty(value = "0:未删除 1:已删除")
    private String isDel;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
