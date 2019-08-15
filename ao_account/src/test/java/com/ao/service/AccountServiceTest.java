package com.ao.service;

import com.ao.entity.Account;
import entity.ResultEnum;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

import javax.annotation.Resource;

public class AccountServiceTest extends BaseTest{

    @Resource
    private AccountService accountService;

    @Test
    public void testRegisterAndLogin(){
        testRegister();
        testLogin();
    }

    @Test
    public void testRegister() {
        try {
            Account account = new Account();
            account.setAccount("admin");
            account.setUsername("admin");
            account.setPasswd("123456");
            boolean register = accountService.register(account);
            if (register) {
                System.out.println(ResultEnum.SUCCESS);
            } else {
                System.out.println(ResultEnum.FAILED);
            }
        } catch (DuplicateKeyException e) {
            System.out.println(ResultEnum.REGISTER_DUPLICATE);
        }
    }

    @Test
    public void testLogin() {
        String acc = "admin";
        String passwd = "123456";
        Account account = accountService.login(acc, passwd);
        if (account != null) {
            System.out.println(account.getUsername());
        }else{
            System.out.println("passwd error");
        }
    }

}
