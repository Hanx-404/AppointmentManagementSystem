package com.niit.adminservice.controller;

import com.niit.adminservice.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-07-02)
 */


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        if (e instanceof io.jsonwebtoken.JwtException) {
            return Result.fail("令牌验证失败: " + e.getMessage());
        }
        return Result.fail("服务器错误: " + e.getMessage());
    }
}
