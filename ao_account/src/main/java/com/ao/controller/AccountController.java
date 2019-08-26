package com.ao.controller;

import com.ao.entity.Account;
import com.ao.service.AccountService;
import com.feilong.core.Validator;
import com.feilong.core.bean.ConvertUtil;
import entity.Constanct;
import entity.Result;
import entity.ResultEnum;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.annotation.Resource;

@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private JwtUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody Account account) {
        if (Validator.isNullOrEmpty(account.getUsername())) {
            account.setUsername(account.getAccount());
        }
        Account login = accountService.login(account);
        String token = jwtUtil.createJWT(
                login.getId().toString(),
                login.getAccount(),
                Constanct.ACCOUNT_CLAIMS);
        return new Result(
                ResultEnum.SUCCESS,
                ConvertUtil.toMap("token", Constanct.TOKEN_PREFIX + token, "name", login.getUsername()));
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public Result alterUserName(@PathVariable("username") String username) {
        return accountService.AlterUserName(jwtUtil.getAccountId(), username) ?
                new Result(ResultEnum.SUCCESS) :
                new Result(ResultEnum.FAILED);
    }

}
