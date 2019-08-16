package com.ao.controller;

import com.ao.entity.Account;
import com.ao.service.AccountService;
import entity.Constanct;
import entity.Result;
import entity.ResultEnum;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;
import util.MapUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("token")
    public Result token(HttpServletRequest request) {
        String header = request.getHeader(Constanct.HEAD_AUTHORIZATION);
        Claims claims = jwtUtil.parseJWT(header);
        String token = jwtUtil.createJWT(
                claims.getId(),
                claims.getSubject(),
                Constanct.ACCOUNT_CLAIMS);
        return new Result(
                ResultEnum.SUCCESS,
                MapUtil.createMap(
                new String[]{"token"},
                new Object[]{token}));
    }

}
