package com.work.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 刘小真
 */
@Data
@ApiModel(value="基础返回类",description="基础返回类")
public final class ApiResponse<T> {
    /**
     * 状态码
     * 0：成功
     * 1：失败
     * 其他状态码均代表失败
     */
    @ApiModelProperty(value = "返回响应码，0为成功，其他为失败")
    private int code;

    /**
     * 返回信息
     * 通用返回信息为success或error
     */
    @ApiModelProperty(value = "返回信息")
    private String msg;

    /**
     * 返回数据
     * T为模板类
     */
    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 构造函数
     * 用于装配对象并返回
     * @param code
     * @param msg
     * @param data
     */
    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 新建默认的成功对象和失败对象
     */
    private static final ApiResponse<Object> SUCCESS = new ApiResponse<>(0, "success", null);
    private static final ApiResponse<Object> ERROR = new ApiResponse<>(1, "success", null);

    /**
     * 失败返回方法
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> error(int code, String msg, T data) {
        return new ApiResponse<T>(code, msg, data);
    }

    /**
     * 只返回错误信息，使用默认code和data
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> error(String msg) {
        return new ApiResponse<T>(ERROR.getCode(), msg, null);
    }

    /**
     * 只返回错误信息和数据，使用默认code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> error(String msg, T data) {
        return new ApiResponse<T>(ERROR.getCode(), msg, data);
    }

    /**
     * 只返回code和msg
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> error(int code, String msg) {
        return new ApiResponse<T>(code, msg, null);
    }

    /**
     * 根据错误枚举返回
     * @param errorEnum
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> error(ErrorCodeEnum errorEnum) {
        return new ApiResponse<T>(errorEnum.getCode(), errorEnum.getMsg(), null);
    }

    /**
     * 成功返回方法
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> success(int code, String msg, T data) {
        return new ApiResponse<T>(code, msg, data);
    }

    /**
     * 只返回数据，使用默认的code和msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(SUCCESS.getCode(), SUCCESS.getMsg(), data);
    }

    /**
     * 只返回信息和数据，使用默认的code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<T>(SUCCESS.getCode(), msg, data);
    }

    /**
     * 只返回提示信息，使用默认的code和data
     * 风险提示：在重载{@link ApiResponse#success(T data)}方法时，不可以传入字符串
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> success(String msg) {
        return new ApiResponse<T>(SUCCESS.getCode(), msg, null);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<T>(SUCCESS.getCode(), SUCCESS.getMsg(), null);
    }
}
