package com.ao.service;

import com.alibaba.fastjson.JSONObject;
import com.ao.entity.Account;
import com.ao.exception.AccountException;
import com.ao.mapper.AccountMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feilong.core.Validator;
import entity.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.annotation.Resource;

@Service
public class AccountService {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Resource
    AccountMapper accountMapper;

    @Resource
    BCryptPasswordEncoder encoder;

    @Resource
    IdWorker idWorker;

    public Account login(Account account) {
        Account login = accountMapper.selectOne(new QueryWrapper<Account>().eq("account", account.getAccount()));
        if (Validator.isNotNullOrEmpty(login)) {
            if (encoder.matches(account.getPasswd(), login.getPasswd())) {
                return login;
            }
            throw new AccountException(ResultEnum.FAILED);
        }
        Account register = new Account();
        register.setUsername(account.getUsername());
        register.setPasswd(encoder.encode(account.getPasswd()));
        register.setAccount(account.getAccount());
        register.setId(idWorker.nextId());
        accountMapper.insert(register);
        return register;
    }

    public Boolean AlterUserName(Long id, String username) {
        Account acc = new Account();
        acc.setUsername(username);
        acc.setId(id);
        return accountMapper.updateById(acc) > 0;
    }

}
