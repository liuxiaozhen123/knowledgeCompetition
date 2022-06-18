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
 * 知识推荐表
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("L_knowledge")
@ApiModel(value="LKnowledge对象", description="知识推荐表")
public class LKnowledge extends Model<LKnowledge> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "推荐知识id")
    private String knowledgeId;

    @ApiModelProperty(value = "知识标题")
    private String title;

    @ApiModelProperty(value = "知识详情")
    private String titleDetail;

    @ApiModelProperty(value = "0:未删除 1:已删除")
    private String isDel;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "对应竞赛名称")
    private String competitionName;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
