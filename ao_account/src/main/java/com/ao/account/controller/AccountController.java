package com.ao.account.controller;

import com.ao.account.service.AccountService;
import com.ao.common.entity.Constanct;
import com.ao.common.entity.Result;
import com.ao.common.entity.ResultEnum;
import org.springframework.web.bind.annotation.*;
import com.ao.common.util.JwtUtil;
import com.ao.account.entity.Account;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private JwtUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody Account account) {
        if (Objects.nonNull(account.getUsername())) {
            account.setUsername(account.getAccount());
        }
        Account login = accountService.login(account);
        String token = jwtUtil.createJWT(
                login.getId().toString(),
                login.getAccount(),
                Constanct.ACCOUNT_CLAIMS);
        return new Result(
                ResultEnum.SUCCESS,
                new HashMap(){{put(Constanct.TOKEN, Constanct.TOKEN_PREFIX + token);put("name", login.getUsername());}});
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public Result alterUserName(@PathVariable("username") String username) {
        return accountService.AlterUserName(jwtUtil.getAccountId(), username) ?
                new Result(ResultEnum.SUCCESS) :
                new Result(ResultEnum.FAILED);
    }

}
