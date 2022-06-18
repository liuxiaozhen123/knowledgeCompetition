package com.work.demo.common.exception;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.work.demo.common.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    /**
     * 构造函数
     * @param code  错误码
     * @param msg   错误信息
     */
    public CustomException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CustomException(String msg) {
        super(msg);
        this.code = 2;
        this.msg = msg;
    }

    public CustomException() {

    }

    public String getErrMessage(){
        return msg;
    }
    public CustomException(ErrorCodeEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg());
    }
}
