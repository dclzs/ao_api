package com.ao.controller;

import com.ao.entity.Account;
import com.ao.service.AccountService;
import entity.Constanct;
import entity.Result;
import entity.ResultEnum;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;
import util.MapUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private JwtUtil jwtUtil;

    @RequestMapping("login")
    public Result login(@RequestBody Account account) {
        if(account.getUsername()==null){
            account.setUsername(account.getAccount());
        }
        Account login = accountService.login(account);
        String token = jwtUtil.createJWT(
                login.getId().toString(),
                login.getAccount(),
                Constanct.ACCOUNT_CLAIMS);
        return new Result(
                ResultEnum.SUCCESS,
                MapUtil.createMap(
                new String[]{"token","name"},
                new Object[]{token,login.getUsername()}));
    }

}
