package com.ao.service;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GenrateEncryptData extends BaseTest {

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void generatePass() {
        String url = encryptor.encrypt("数据库连接");
        String name = encryptor.encrypt("用户名");
        String password = encryptor.encrypt("密码");
        System.out.println(url);
        System.out.println(name);
        System.out.println(password);
    }

}
