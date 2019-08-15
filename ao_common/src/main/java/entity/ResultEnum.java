package entity;

public enum ResultEnum {
    SUCCESS(1024,"成功"),
    FAILED(1023,"失败"),
    REGISTER_DUPLICATE(1022,"当前账号已被注册，请勿重复注册");

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
}
