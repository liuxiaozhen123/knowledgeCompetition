package com.work.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 问题表
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("L_question")
@ApiModel(value="LQuestion对象", description="问题表")
public class LQuestion extends Model<LQuestion> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "问题id")
    private String questionId;

    @ApiModelProperty(value = "问题内容")
    private String questionName;

    @ApiModelProperty(value = "竞赛id")
    @TableField("Competition_id")
    private String competitionId;

    @ApiModelProperty(value = "0:未删除 1:已删除")
    private String isDel;

    @ApiModelProperty(value = "答案")
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
