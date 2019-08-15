package com.ao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("ao_account")
public class Account implements Serializable {

    //自增ID
    @TableId(type = IdType.AUTO)
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
}
