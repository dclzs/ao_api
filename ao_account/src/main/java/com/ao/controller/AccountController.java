package com.ao.controller;

import com.ao.service.AccountService;
import entity.Result;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;


    public Result register(){
        return null;
    }

    public Result login(){
        return null;
    }

}
