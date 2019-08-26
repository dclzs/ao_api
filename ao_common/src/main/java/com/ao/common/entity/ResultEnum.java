package com.ao.common.entity;

public enum ResultEnum {
    SUCCESS(1024, "成功"),
    FAILED(1023, "失败"),
    REGISTER_DUPLICATE(1022, "当前账号已被注册，请勿重复注册"),
    TOKEN_ERROR(1021, "Token无效");

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResultEnum find(String msg) {
        for (ResultEnum resultEnum : ResultEnum.values())
            if (resultEnum.getMsg().equals(msg))
                return resultEnum;
        return null;
    }

    public static ResultEnum find(int code) {
        for (ResultEnum resultEnum : ResultEnum.values())
            if (code == resultEnum.getCode())
                return resultEnum;
        return null;
    }

}
