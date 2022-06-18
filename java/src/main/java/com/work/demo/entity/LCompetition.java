package com.work.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 赛题表
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("L_Competition")
@ApiModel(value="LCompetition对象", description="赛题表")
public class LCompetition extends Model<LCompetition> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "竞赛id")
    @TableField("Competition_id")
    private String competitionId;

    @ApiModelProperty(value = "赛题名称")
    @TableField("Competition_name")
    private String competitionName;

    @ApiModelProperty(value = "未发布:0 已发布:1 已删除:2")
    private String status;

    @ApiModelProperty(value = "0：未设置种类 1：动物 2：植物 3：古代名人 4：建筑")
    private Integer kind;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "0：未删除 1：已删除")
    private Integer isDel;

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
