package com.work.demo.common.aop;

import com.work.demo.common.ApiResponse;
import com.work.demo.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 刘小真
 */
@ControllerAdvice
public class MyControllerAdvice {

    Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    /**
     * 参数校验异常封装
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse exceptionHander(HttpServletRequest req, MethodArgumentNotValidException e){
        List<ObjectError> errorList=e.getBindingResult().getAllErrors();
        String error="";
        for (ObjectError objectError : errorList) {
            logger.warn("请求参数校验失败，异常信息[{}]", JsonUtil.getGson().toJson(objectError));
            error=objectError.getDefaultMessage();
        }
        return ApiResponse.error("1001",error);
    }

}
