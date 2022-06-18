package com.work.demo.common.exception;

import com.work.demo.common.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 异常类
 * @author 刘小真
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException{

    protected int errCode;
    protected String errMessage;
    protected Object data;

    public BizException(int errCode, String errMessage, Throwable cause) {
        super(errMessage, cause);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public BizException(ErrorCodeEnum errorEnum, Throwable cause) {
        this(errorEnum.getCode(), errorEnum.getMsg(), cause);
    }

    public BizException(ErrorCodeEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg(), null);
    }

    public BizException(String errMessage) {
        this(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR.getCode(), errMessage, null);
    }

    public BizException(int errCode, String errMessage) {
        this(errCode, errMessage, null);
    }

    public BizException(int errCode, String errMessage, Object data) {
        this.errCode = errCode;
        this.errMessage = errMessage;
        this.data = data;
    }
}
