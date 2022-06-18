package com.work.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("L_file")
@ApiModel(value="LFile对象", description="文件表")
public class LFile extends Model<LFile> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件id")
    private String fileId;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "文件名+内层路径，外层路径在项目里配置")
    private String path;

    @ApiModelProperty(value = "是否删除(0:未删除；1:已删除)")
    private String isDel;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
