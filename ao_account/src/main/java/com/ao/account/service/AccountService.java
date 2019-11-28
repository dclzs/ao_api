package com.ao.account.service;

import com.ao.account.entity.Account;
import com.ao.account.mapper.AccountMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ao.common.entity.ResultEnum;
import com.ao.common.exception.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ao.common.util.IdWorker;

import javax.annotation.Resource;
import java.util.Objects;

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
        if (Objects.nonNull(login)) {
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
