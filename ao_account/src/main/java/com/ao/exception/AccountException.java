package com.ao.exception;

import entity.ResultEnum;
import exception.AoException;

public class AccountException extends AoException {


    public AccountException() {
    }

    public AccountException(ResultEnum resultEnum, Throwable cause) {
        super(resultEnum, cause);
    }

    public AccountException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountException(Throwable cause) {
        super(cause);
    }

    public AccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
