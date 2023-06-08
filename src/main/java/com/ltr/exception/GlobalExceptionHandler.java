package com.ltr.exception;

import com.ltr.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* 全局异常处理器 */
    @ExceptionHandler(Exception.class)
    public Result ex(Exception e) {
        e.printStackTrace();
        return Result.error("操作失败");
    }

}
