package com.work.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxiaozhen
 */
@Data
@ApiModel(value = "竞赛组织者组织竞赛列表")
public class ViewKnowledgeVo {
    @ApiModelProperty(value = "知识推荐名称")
    private String knowledgeName;

    @ApiModelProperty(value = "知识推荐id")
    private String knowledgeId;

    @ApiModelProperty(value = "知识推荐种类")
    private String kind;

    @ApiModelProperty(value = "知识推荐状态")
    private String status;

    @ApiModelProperty(value = "知识竞赛名称")
    private String competitionName;

    @ApiModelProperty(value = "更新时间")
    private String createTime;


}
