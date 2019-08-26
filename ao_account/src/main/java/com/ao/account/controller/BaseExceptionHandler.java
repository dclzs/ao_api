package com.ao.account.controller;

import com.ao.common.entity.Result;
import com.ao.common.exception.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(AccountException.class)
    @ResponseBody
    public Result error(AccountException e) {
        logger.error("================全局异常捕获================", e);
        return new Result(Objects.requireNonNull(e.getResultEnum()));
    }

}
