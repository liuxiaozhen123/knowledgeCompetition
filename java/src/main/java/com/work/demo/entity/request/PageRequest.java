package com.work.demo.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zuozhiwei
 */
@Data
@ApiModel(value = "分页参数")
public class PageRequest {
    @ApiModelProperty(value = "当前第几页")
    private int currentPage;
    @ApiModelProperty(value = "每页多少条")
    private int pageSize;

    public PageRequest(){
        this.currentPage = 1;
        this.pageSize = 10;
    }

    public PageRequest(int currentPage, int pageSize){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
