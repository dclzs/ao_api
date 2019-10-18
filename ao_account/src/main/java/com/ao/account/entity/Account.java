package com.ao.account.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("ao_account")
public class Account implements Serializable {

    //ID
    private Long id;

    //用户名
    private String username;

    //账号
    private String account;

    //密码
    private String passwd;

    //启用
    private Integer status;

    //创建时间
    private Date tsp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTsp() {
        return tsp;
    }

    public void setTsp(Date tsp) {
        this.tsp = tsp;
    }


    public static final class Field{
        public static final String TABLE_NAME = "ao_account";
        public static final String ACCOUNT = "account";
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWD = "passwd";
        public static final String STATUS = "status";
        public static final String TSP = "tsp";
    }

}
