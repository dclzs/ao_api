package com.ao.controller;

import com.alibaba.fastjson.JSONObject;
import com.ao.exception.AccountException;
import entity.Result;
import entity.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
public class BaseExceptionHandler {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);
    
    @ExceptionHandler(AccountException.class)
    @ResponseBody
    public Result error(AccountException e){
        logger.error("================全局异常捕获================",e);
        return new Result(Objects.requireNonNull(e.getResultEnum()));
    }

}
