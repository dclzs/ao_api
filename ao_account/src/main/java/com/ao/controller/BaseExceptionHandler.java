package com.ao.controller;

import com.ao.exception.AccountException;
import entity.Result;
import entity.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(AccountException.class)
    @ResponseBody
    public Result error(AccountException e){
        e.printStackTrace();
        return new Result(Objects.requireNonNull(ResultEnum.find(e.getMessage())));
    }

}
