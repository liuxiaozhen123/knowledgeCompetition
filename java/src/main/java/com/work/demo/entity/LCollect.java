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
 * 收藏表
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("L_collect")
@ApiModel(value="LCollect对象", description="收藏表")
public class LCollect extends Model<LCollect> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登录id")
    private String loginId;

    @ApiModelProperty(value = "竞赛id")
    private String competitionId;

    @ApiModelProperty(value = "知识id")
    private String knowledgeId;

    @ApiModelProperty(value = "是否删除：0：未删，1：删除")
    private String isDel;

    @ApiModelProperty(value = "0:竞赛，1：知识")
    private String type;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
