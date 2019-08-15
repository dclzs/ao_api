package com.ao.service;

import com.ao.entity.Account;
import com.ao.mapper.AccountMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    BCryptPasswordEncoder encoder;

    public boolean register(Account account) throws DuplicateKeyException {
        String encode = encoder.encode(account.getPasswd());
        account.setPasswd(encode);
        return accountMapper.insert(account) > 0;
    }

    public Account login(String acc, String passwd) {
        Account account = accountMapper.selectOne(new QueryWrapper<Account>().eq("account", acc));
        if (account != null && encoder.matches(passwd, account.getPasswd())) {
            return account;
        }
        return null;
    }

}
