package com.ao.service;

import com.ao.entity.Account;
import com.ao.exception.AccountException;
import com.ao.mapper.AccountMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import entity.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.annotation.Resource;

@Service
public class AccountService {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Resource
    AccountMapper accountMapper;

    @Resource
    BCryptPasswordEncoder encoder;

    @Resource
    IdWorker idWorker;

    public Account login(Account account) {
        Account login = accountMapper.selectOne(new QueryWrapper<Account>().eq("account", account.getAccount()));
        if (login != null) {
            if (encoder.matches(account.getPasswd(), login.getPasswd())) {
                return login;
            }else{
                throw new AccountException(ResultEnum.FAILED.getMsg());
            }
        }else{
            Account register = new Account();
            register.setUsername(account.getUsername());
            register.setPasswd(encoder.encode(account.getPasswd()));
            register.setAccount(account.getAccount());
            register.setId(idWorker.nextId());
            accountMapper.insert(register);
            return register;
        }
    }

}
