package com.ao.account.controller;

import com.ao.account.service.AccountService;
import com.feilong.core.Validator;
import com.feilong.core.bean.ConvertUtil;
import com.ao.common.entity.Constanct;
import com.ao.common.entity.Result;
import com.ao.common.entity.ResultEnum;
import org.springframework.web.bind.annotation.*;
import com.ao.common.util.JwtUtil;
import com.ao.account.entity.Account;

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
                ConvertUtil.toMap(Constanct.TOKEN, Constanct.TOKEN_PREFIX + token, "name", login.getUsername()));
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public Result alterUserName(@PathVariable("username") String username) {
        return accountService.AlterUserName(jwtUtil.getAccountId(), username) ?
                new Result(ResultEnum.SUCCESS) :
                new Result(ResultEnum.FAILED);
    }

}
